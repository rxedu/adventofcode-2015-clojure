(ns adventofcode.day-03-test
  (:require [clojure.test :refer :all]
            [adventofcode.day-03 :refer :all]))

(deftest move-to-vector-test
  (is (= [1 0] (move-to-vector ">")))
  (is (= [-1 0] (move-to-vector "<")))
  (is (= [0 1] (move-to-vector "^")))
  (is (= [0 -1] (move-to-vector "v")))
  (is (= [0 0] (move-to-vector "a"))))

(deftest moves-to-seq-test
  (is (= '([1 0] [-1 0] [0 1] [0 -1])
         (moves-to-seq "><^v"))))

(deftest track-houses-test
  (is (= #{[0 0] [1 0] [2 0] [1 -1]}
         (track-houses [[1 0] [1 0] [-1 0] [0 -1]]))))
