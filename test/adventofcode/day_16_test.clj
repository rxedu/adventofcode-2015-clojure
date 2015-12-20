(ns adventofcode.day-16-test
  (:require [clojure.test :refer :all]
            [adventofcode.day-16 :refer :all]))

(deftest parse-sue-test
  (is (= {1 {:cars 9 :akitas 3 :goldfish 0}}
         (parse-sue "Sue 1: cars: 9, akitas: 3, goldfish: 0"))))

(deftest compare-sue-test
  (is (false? (compare-sue {:a 1 :b 2 :c 3} {:a 1 :b 2 :c 5})))
  (is (false? (compare-sue {:a 1 :b 2 :c 3} {:a 1 :b 1 :c 5})))
  (is (true?  (compare-sue {:a 1 :b 2 :c 3} {:a 1 :b 2 :c 3})))
  (is (true?  (compare-sue {:a 1 :b 2 :c 3} {:a 1 :b 2 :d 3})))
  (is (true?  (compare-sue {:a 1 :b 2 :d 3} {:a 1 :b 2 :c 3}))))
