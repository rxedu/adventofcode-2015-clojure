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

(deftest init-grid-test
  (is (= [[false false] [false false]]
         (init-grid 2 false)))
  (is (= [[0 0 0] [0 0 0] [0 0 0]]
         (init-grid 3 0))))

(deftest form-grid-test
  (is (= [[true  true]
          [false false]]
         (form-grid 2 (fn [[x y]] (if (= x 0) true false)))))
  (is (= [[false true]
          [false true]]
         (form-grid 2 (fn [[x y]] (if (= y 1) true false)))))
  (is (= [[false false false]
          [false false true]
          [false false false]]
         (form-grid 3 (fn [[x y]] (if (and (= x 1) (= y 2)) true false))))))

(deftest modify-grid-test
  (let [grid (init-grid 3 false)]
    (is (= [[true  false false]
            [true  false false]
            [false false false]]
           (modify-grid grid [:on [0 1] [0 0]] actions)))
    (is (= [[true  true  true]
            [false false false]
            [false false false]]
           (modify-grid grid [:toggle [0 0] [0 2]] actions)))))
