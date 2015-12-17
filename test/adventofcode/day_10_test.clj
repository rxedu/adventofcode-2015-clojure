(ns adventofcode.day-10-test
  (:require [clojure.test :refer :all]
            [adventofcode.day-10 :refer :all]))

(deftest look-and-say-test
  (is (= ["1" "11" "21" "1211" "111221" "312211" "13112221" "1113213211"]
         (take 8 (look-and-say))))
  (is (= ["2" "12" "1112" "3112" "132112" "1113122112" "311311222112"]
         (take 7 (look-and-say 2))))
  (is (= ["3" "13" "1113" "3113" "132113" "1113122113" "311311222113"]
         (take 7 (look-and-say 3)))))
