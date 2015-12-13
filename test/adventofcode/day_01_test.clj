(ns adventofcode.day-01-test
  (:require [clojure.test :refer :all]
            [adventofcode.day-01 :refer :all]))

(deftest final-floor-test
  (is (=  0 (final-floor "(())")))
  (is (=  0 (final-floor "()()")))
  (is (=  3 (final-floor "(((")))
  (is (=  3 (final-floor "(()(()(")))
  (is (=  3 (final-floor "))(((((")))
  (is (= -1 (final-floor "())")))
  (is (= -1 (final-floor "))(")))
  (is (= -3 (final-floor ")))")))
  (is (= -3 (final-floor ")())())"))))

(deftest paren-to-int-test
  (is (=  1 (paren-to-int "(")))
  (is (= -1 (paren-to-int ")")))
  (is (=  0 (paren-to-int "a"))))

(deftest steps-to-basement-test
  (is (=  1 (steps-to-basement ")")))
  (is (=  1 (steps-to-basement "))")))
  (is (=  5 (steps-to-basement "()())")))
  (is (= 11 (steps-to-basement "(()()()()))))))(("))))
