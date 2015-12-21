(ns adventofcode.day-20-test
  (:require [clojure.test :refer :all]
            [adventofcode.day-20 :refer :all]))

(deftest elf-houses-test
  (is (= [1 2 3 4 5]   (take 5 (elf-houses 1))))
  (is (= [2 4 6 8 10]  (take 5 (elf-houses 2))))
  (is (= [3 6 9 12 15] (take 5 (elf-houses 3)))))

(deftest visited?-test
  (are [b i n] (is (b (visited? i n)))
    true?  1  1
    true?  7  1
    false? 1  2
    true?  2  2
    true?  18 2
    false? 7  3
    true?  10 5))

(deftest house-presents-test
  (is (= [1 3 4 7 6 12 8 15 13]
         (map house-presents (range 1 10)))))

(deftest solve-test
  (testing "part 1"
    (is (= 8 (first (solve "125"))))
    (is (= 48 (first (solve "1000"))))))
