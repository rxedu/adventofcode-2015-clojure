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

(deftest transform-grid-test
  (let [grid (init-grid 3 false)]
    (is (= [[true  false false]
            [true  false false]
            [false false false]]
           (transform-grid actions grid [[:on [0 1] [0 0]]])))
    (is (= [[true  true  true]
            [false false false]
            [false false false]]
           (transform-grid actions grid [[:toggle [0 0] [0 2]]]))))
  (let [grid (init-grid 3 0)]
    (is (= [[0 0 0]
            [0 2 0]
            [0 0 0]]
           (transform-grid brightness-actions grid [[:toggle [1 1] [1 1]]])))
    (is (= [[1 1 0]
            [1 3 2]
            [1 1 0]]
           (transform-grid brightness-actions grid [[:on     [0 2] [0 1]]
                                                    [:toggle [1 1] [1 2]]])))))

(deftest lit-lights-test
  (is (= 0 (lit-lights [[false false] [false false]])))
  (is (= 3 (lit-lights [[true  false] [true  true]])))
  (is (= 2 (lit-lights [[true  true]  [false false]]))))

(deftest brightness-test
  (is (= 0  (brightness [[0 0] [0 0]])))
  (is (= 11 (brightness [[2 5] [1 3]])))
  (is (= 4  (brightness [[0 0] [4 0]]))))
