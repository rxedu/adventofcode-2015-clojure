(ns adventofcode.day-18-test
  (:require [clojure.test :refer :all]
            [adventofcode.day-18 :refer :all]))

(deftest parse-lights-test
  (is (= [true]   (parse-lights "#")))
  (is (= [false]  (parse-lights ".")))
  (is (= [true false true true true false false]
         (parse-lights "#.###.."))))

(deftest in-corner?-test
  (is (false? (in-corner? 100 [34 52])))
  (is (true?  (in-corner? 100 [0 0])))
  (is (true?  (in-corner? 100 [0 99])))
  (is (true?  (in-corner? 100 [99 0])))
  (is (true?  (in-corner? 100 [99 99]))))

(deftest nearest-neighbors-test
  (is (= [[0 1] [1 0] [1 1]] (nearest-neighbors 10 [0 0])))
  (is (= [[9 8] [8 9] [8 8]] (nearest-neighbors 10 [9 9])))
  (is (= [[5 8] [6 9] [6 8] [4 9] [4 8]]
         (nearest-neighbors 10 [5 9])))
  (is (= [[4 6] [4 4] [5 5] [5 6] [5 4] [3 5] [3 6] [3 4]]
         (nearest-neighbors 10 [4 5]))))

(deftest neighbor-status-test
  (is (= {true 3 false 5}
         (neighbor-status [[true  true  true  true]
                           [false false true  true]
                           [true  false false true]
                           [false true  false true]] [2 1])))
  (is (= {true 2 false 1}
         (neighbor-status [[true  true  true  false]
                           [false false false true]
                           [true  false false true]
                           [false true  false true]] [0 3]))))

(deftest animate-light-test
  (let [grid [[true  true  true  false]
              [false false false true]
              [true  false false false]
              [false true  false true]]]
    (is (true?  (animate-light grid [0 1])))
    (is (true?  (animate-light grid [1 0])))
    (is (false? (animate-light grid [2 1])))
    (is (false? (animate-light grid [1 3])))
    (is (false? (animate-light grid [3 3])))))

(deftest light-corners-test
  (let [grid [[true  true  true  false]
              [false false true  true]
              [true  true  false false]
              [false true  false true]]]
    (is (true?  (light-corners grid [0 0])))
    (is (true?  (light-corners grid [3 0])))
    (is (true?  (light-corners grid [0 3])))
    (is (true?  (light-corners grid [3 3])))
    (is (true?  (light-corners grid [2 0])))
    (is (false? (light-corners grid [3 2])))))

(deftest step-grid-test
  (is (= [[false true  true  false]
          [true  false false true]
          [false false false true]
          [false false true  false]]
         (step-grid animate-light
                    [[true  true  true  false]
                     [false false false true]
                     [true  false false true]
                     [false true  false true]]))))
