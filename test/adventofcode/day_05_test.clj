(ns adventofcode.day-05-test
  (:require [clojure.test :refer :all]
            [adventofcode.day-05 :refer :all]))

(deftest nice?-test
  (is (true?  (nice? "ugknbfddgicrmopn")))
  (is (true?  (nice? "aaa")))
  (is (false? (nice? "jchzalrnumimnmhp")))
  (is (false? (nice? "haegwjzuvuyypxyu")))
  (is (false? (nice? "dvszwmarrgswjxmb"))))

(deftest really-nice?-test
  (is (true?  (really-nice? "qjhvhtzxzqqjkmpb")))
  (is (true?  (really-nice? "xxyxx")))
  (is (false? (really-nice? "uurcxstgmygtbstg")))
  (is (false? (really-nice? "ieodomkazucvgmuy"))))
