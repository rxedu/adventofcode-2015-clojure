(ns adventofcode.day-03-test
  (:require [clojure.test :refer :all]
            [adventofcode.day-03 :refer :all]))

(deftest parse-moves-test
  (is (= '([1 0] [-1 0] [0 1] [0 -1])
         (parse-moves "><^v")))
  (is (= '([0 1] [0 1] [1 0] [1 0])
         (parse-moves "^^>>"))))

(deftest track-houses-test
  (is (= #{[0 0] [1 0] [2 0] [1 -1]}
         (track-houses [[1 0] [1 0] [-1 0] [0 -1]]))))
