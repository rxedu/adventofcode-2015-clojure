(ns adventofcode.day-04-test
  (:require [clojure.test :refer :all]
            [adventofcode.day-04 :refer :all]))

(deftest positive-numbers-test
  (is (= [1]         (take 1 (positive-numbers))))
  (is (= [1 2]       (take 2 (positive-numbers))))
  (is (= [1 2 3 4 5] (take 5 (positive-numbers)))))

(deftest count-leading-zeros-test
  (is (= 0 (count-leading-zeros "83nskd83hsl")))
  (is (= 0 (count-leading-zeros "f03kajslkdo")))
  (is (= 0 (count-leading-zeros "k1993ns8300")))
  (is (= 1 (count-leading-zeros "03nskd83hsl")))
  (is (= 2 (count-leading-zeros "00nskd83hsl")))
  (is (= 3 (count-leading-zeros "000skd83hsl"))))

(deftest zero-salted-hash-search-test
  (is (= "609043"  (zero-salted-hash-search
                    ["828228" "609043" "909099"] "abcdef" 5)))
  (is (= "1048970" (zero-salted-hash-search
                    ["202020" "424242" "1048970"] "pqrstuv" 5))))
