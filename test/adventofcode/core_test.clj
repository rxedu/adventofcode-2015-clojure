(ns adventofcode.core-test
  (:require [clojure.test :refer :all]
            [adventofcode.core :refer :all]))

(deftest solve-day-test
  (are [n out] (= out (time (solve-day n)))
    1  [232 1783]
    2  [1606483 3842356]
    3  [2572 2631]
    4  [254575 1038736]
    5  [255 55]
    6  [377891 14110788]
    7  [16076 2797]
    8  [1342 2074]
    9  [207 804]
    10 [252594 3579328]
    11 ["vzbxxyzz" "vzcaabcc"]
    12 [111754 65402]
    13 [664 640]
    14 [2696 1084]
    15 [222870 117936]
    16 [373 260]
    17 [654 57]
    18 [821 886]
    19 [535 212]
   ;20 [665280 705600] ; OPTIMIZE
    21 [78 148]
    25 [9132360]))
