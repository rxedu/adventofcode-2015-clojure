(ns adventofcode.core-test
  (:require [clojure.test :refer :all]
            [adventofcode.core :refer :all]))

(deftest solve-day-test
  (are [out n] (= out (solve-day n))
    [232 1783] 1
    [1606483 3842356] 2
    [2572 2631] 3
    [254575 1038736] 4
    [255 55] 5
    ; [377891 14110788] 6
    [16076 2797] 7
    [1342 2074] 8
    [207 804] 9
    [252594 3579328] 10
    ["vzbxxyzz" "vzcaabcc"] 11
    [111754 65402] 12
    [664 640] 13
    [2696 1084] 14
    [222870 117936] 15
    [373 260] 16
    [654 57] 17
    [821 886] 18))
