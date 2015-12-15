(ns adventofcode.day-01-test
  (:require [clojure.test :refer :all]
            [adventofcode.day-01 :refer :all]))

(deftest parse-directions-test
  (is (=  [1 -1 1 1 1 -1] (parse-directions "()((()"))))

(deftest steps-to-basement-test
  (is (= 1 (steps-to-basement [-1])))
  (is (= 9 (steps-to-basement [1 1 1 -1 1 -1 -1 -1 -1 -1 -1 1]))))

(deftest solve-test
  (testing "part 1"
    (is (=  0 (first (solve "(())"))))
    (is (=  0 (first (solve "()()"))))
    (is (=  3 (first (solve "((("))))
    (is (=  3 (first (solve "(()(()("))))
    (is (=  3 (first (solve "))((((("))))
    (is (= -1 (first (solve "())"))))
    (is (= -1 (first (solve "))("))))
    (is (= -3 (first (solve ")))"))))
    (is (= -3 (first (solve ")())())")))))
  (testing "part 2"
    (is (=  1 (second (solve ")"))))
    (is (=  1 (second (solve "))"))))
    (is (=  5 (second (solve "()())"))))
    (is (= 11 (second (solve "(()()()()))))))(("))))))
