(ns adventofcode.day-01-test
  (:require [clojure.test :refer :all]
            [adventofcode.day-01 :refer :all]))

(deftest final-floor-test
  (is (= 0 (final-floor "(())")))
  (is (= 0 (final-floor "()()")))
  (is (= 3 (final-floor "(((")))
  (is (= 3 (final-floor "(()(()(")))
  (is (= 3 (final-floor "))(((((")))
  (is (= -1 (final-floor "())")))
  (is (= -1 (final-floor "))(")))
  (is (= -3 (final-floor ")))")))
  (is (= -3 (final-floor ")())())"))))
