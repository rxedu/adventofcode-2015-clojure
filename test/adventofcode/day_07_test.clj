(ns adventofcode.day-07-test
  (:require [clojure.test :refer :all]
            [adventofcode.day-07 :refer :all]))

(deftest parse-connection-test
  (is (= [:sig {:xy [42]}]   (parse-connection "42 -> xy")))
  (is (= [:con {:xx [:zz]}]  (parse-connection "zz -> xx")))
  (is (= [:not {:zz [:xx]}]  (parse-connection "NOT xx -> zz")))
  (is (= [:and {:z [:x :y]}] (parse-connection "x AND y -> z")))
  (is (= [:or  {:z [:x :y]}] (parse-connection "x OR y -> z")))
  (is (= [:lsh {:z [:x 2]}]  (parse-connection "x LSHIFT 2 -> z")))
  (is (= [:rsh {:z [:x 2]}]  (parse-connection "x RSHIFT 2 -> z"))))

(deftest connection-inputs-test
  (is (= []       (connection-inputs [:sig {:xy [42]}])))
  (is (= [:xx]    (connection-inputs [:not {:zz [:xx]}])))
  (is (= [:x :yy] (connection-inputs [:and {:z [:x :yy]}])))
  (is (= [:x]     (connection-inputs [:rsh {:z [:x 5]}]))))

(deftest missing-inputs?-test
  (is (false? (missing-inputs? {:x 1 :y 2} [:sig {:z [42]}])))
  (is (false? (missing-inputs? {:x 1 :y 2} [:and {:z [:x :y]}])))
  (is (true?  (missing-inputs? {:x 1} [:and {:z [:x :y]}]))))

(deftest connect-test
  (let [wires {:x 3 :y 5 :z 6}]
    (is (= {:x 3 :y 5 :z 6}        (connect wires [:and {:z [:x :a]}])))
    (is (= {:x 3 :y 5 :z 42}       (connect wires [:sig {:z [42]}])))
    (is (= {:x 3 :y 5 :z 6 :a 42}  (connect wires [:sig {:a [42]}])))
    (is (= {:x 3 :y 5 :z 1}        (connect wires [:and {:z [:x :y]}])))
    (is (= {:x 3 :y 5 :z -4}       (connect wires [:not {:z [:x]}])))
    (is (= {:x 3 :y 5 :z 6 :a 320} (connect wires [:lsh {:a [:y :z]}])))))

(deftest override-wire-test
  (is (= [[:sig {:x [100]}] [:not {:y [:x]}]]
         (override-wire [[:sig {:x [42]}] [:not {:y [:x]}]] :x 100))))

(deftest solve-circuit-test
  (let [simple-connections
        (map parse-connection
             ["123 -> x"
              "456 -> y"
              "x AND y -> a"
              "x OR y -> e"
              "x LSHIFT 2 -> f"
              "y RSHIFT 2 -> g"
              "NOT x -> h"
              "NOT y -> i"])
        two-level-connections
        (map parse-connection
             ["z -> x"
              "NOT z -> aa"
              "456 -> y"
              "x AND y -> a"
              "x OR y -> e"
              "x LSHIFT 2 -> f"
              "y RSHIFT 2 -> g"
              "NOT x -> h"
              "NOT y -> i"
              "123 -> z"])
        simple-wires
        {:a 72, :e 507, :f 492, :g 114, :h -124, :i -457, :x 123, :y 456}
        two-level-wires (assoc simple-wires :aa -124 :z 123)]
    (is (= simple-wires (solve-circuit simple-connections)))
    (is (= two-level-wires (solve-circuit two-level-connections)))))
