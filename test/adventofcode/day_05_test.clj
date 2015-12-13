(ns adventofcode.day-05-test
  (:require [clojure.test :refer :all]
            [adventofcode.day-05 :refer :all]))

(deftest nice?-test
  (is (true?  (nice? "ugknbfddgicrmopn")))
  (is (true?  (nice? "aaa")))
  (is (false? (nice? "jchzalrnumimnmhp")))
  (is (false? (nice? "haegwjzuvuyypxyu")))
  (is (false? (nice? "dvszwmarrgswjxmb"))))
