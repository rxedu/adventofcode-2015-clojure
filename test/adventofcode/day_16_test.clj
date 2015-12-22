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

(deftest compare-real-sue-test
  (is (false? (compare-real-sue {:goldfish 4 :c 3} {:goldfish 5 :c 3})))
  (is (false? (compare-real-sue {:goldfish 4 :c 3} {:goldfish 4 :c 3})))
  (is (true?  (compare-real-sue {:goldfish 4 :c 3} {:goldfish 3 :c 3})))
  (is (true?  (compare-real-sue {:cats 4 :c 3} {:cats 5 :c 3})))
  (is (false? (compare-real-sue {:cats 4 :c 3} {:cats 4 :c 3})))
  (is (false? (compare-real-sue {:cats 4 :c 3} {:cats 3 :c 3})))
  (is (false? (compare-real-sue {:a 1 :b 2 :c 3} {:a 1 :b 1 :c 5})))
  (is (true?  (compare-real-sue {:a 1 :b 2 :c 3} {:a 1 :b 2 :c 3})))
  (is (true?  (compare-real-sue {:cats 4 :goldfish 8 :d 3}
                                {:cats 6 :goldfish 2 :d 3 :e 4}))))
