(ns adventofcode.day-20-test
  (:require [clojure.test :refer :all]
            [adventofcode.day-20 :refer :all]))

(deftest house-presents-test
  (is (= [10 30 40 70 60 120 80 150 130]
         (map house-presents (range 1 10)))))

(deftest house-presents-fifty-test
  (is (= [11 33 44 77 66 132 88 165 143]
         (map house-presents-fifty (range 1 10))))
  (is (= 2376 (house-presents-fifty 100))))

(deftest solve-test
  (testing "part 1"
    (is (= 8 (first (solve "130"))))
    (is (= 48 (first (solve "1000"))))))
