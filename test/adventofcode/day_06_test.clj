(ns adventofcode.day-06-test
  (:require [clojure.test :refer :all]
            [adventofcode.day-06 :refer :all]))

(deftest parse-instruction-test
  (is (= [:on [0 999] [0 999]]
         (parse-instruction "turn on 0,0 through 999,999")))
  (is (= [:toggle [0 999] [0 0]]
         (parse-instruction "toggle 0,0 through 999,0")))
  (is (= [:off [499 500] [499 500]]
         (parse-instruction "turn off 499,499 through 500,500"))))

(deftest transform-grid-test
  (is (= [[true  false false]
          [true  false false]
          [false false false]]
         (transform-grid actions
                         3 Boolean/TYPE [[:on [0 1] [0 0]]])))
  (is (= [[true  true  true]
          [false false false]
          [false false false]]
         (transform-grid actions
                         3 Boolean/TYPE [[:toggle [0 0] [0 2]]])))
  (is (= [[0 0 0]
          [0 2 0]
          [0 0 0]]
         (transform-grid brightness-actions
                         3 Integer/TYPE [[:toggle [1 1] [1 1]]])))
  (is (= [[1 1 0]
          [1 3 2]
          [1 1 0]]
         (transform-grid brightness-actions
                         3 Integer/TYPE [[:on     [0 2] [0 1]]
                                         [:toggle [1 1] [1 2]]]))))

(deftest lit-lights-test
  (is (= 0 (lit-lights [[false false] [false false]])))
  (is (= 3 (lit-lights [[true  false] [true  true]])))
  (is (= 2 (lit-lights [[true  true]  [false false]]))))

(deftest brightness-test
  (is (= 0  (brightness [[0 0] [0 0]])))
  (is (= 11 (brightness [[2 5] [1 3]])))
  (is (= 4  (brightness [[0 0] [4 0]]))))
