(ns adventofcode.day-02-test
  (:require [clojure.test :refer :all]
            [adventofcode.day-02 :refer :all]))

(deftest input-to-dimensions-test
  (is (= [4 2 6] (input-to-dimensions "4x2x6"))))

(deftest paper-test
  (is (= 58 (paper [2 3 4])))
  (is (= 43 (paper [1 1 10])))
  (is (= 7 (paper [1 1 1])))
  (is (= 58 (paper [4 2 3]))))
