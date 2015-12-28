(ns adventofcode.day-25-test
  (:require [clojure.test :refer :all]
            [adventofcode.day-25 :refer :all]))

(deftest parse-message-test
  (is (= [300 42]
         (parse-message
          "To continue, please consult the code grid in the manual.
          Enter the code at row 42, column 300"))))

(deftest triangular-test
  (is (= [1 3 6 10 15 21] (take 6 (triangular)))))

(deftest column-test
  (is (= [1 2 4 7 11 16] (take 6 (column 1))))
  (is (= [3 5 8 12 17]   (take 5 (column 2))))
  (is (= [6 9 13 18]     (take 4 (column 3))))
  (is (= [10 14 19]      (take 3 (column 4))))
  (is (= [15 20]         (take 2 (column 5))))
  (is (= [21]            (take 1 (column 6)))))

(deftest code-index-test
  (are [n p] (= n (code-index p))
    1  [1 1]
    2  [1 2]
    3  [2 1]
    4  [1 3]
    16 [1 6]
    19 [4 3]
    20 [5 2]
    21 [6 1]))

(deftest codes-test
  (is (= [20151125 31916031 18749137 16080970 21629792]
         (take 5 (codes)))))

(deftest code-at-test
  (are [c x y] (= c (code-at [x y]))
    20151125 1 1
    18749137 2 1
    17289845 3 1
    31916031 1 2
    21629792 2 2
    8057251  2 3))
