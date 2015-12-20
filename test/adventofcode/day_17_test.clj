(ns adventofcode.day-17-test
  (:require [clojure.test :refer :all]
            [adventofcode.day-17 :refer :all]))

(deftest container-combinations-test
  (is (= 4 (count (container-combinations [20 15 10 5 5] 25)))))

(deftest min-container-combinations-test
  (is (= 3 (min-container-combinations
            (container-combinations [20 15 10 5 5] 25)))))
