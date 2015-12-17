(ns adventofcode.day-03-test
  (:require [clojure.test :refer :all]
            [adventofcode.day-03 :refer :all]))

(deftest parse-moves-test
  (is (= [[1 0] [-1 0] [0 1] [0 -1]]
         (parse-moves "><^v")))
  (is (= [[0 1] [0 1] [1 0] [1 0]]
         (parse-moves "^^>>"))))

(deftest track-houses-test
  (is (= #{[0 0] [1 0] [2 0] [1 -1]}
         (track-houses [[1 0] [1 0] [-1 0] [0 -1]]))))

(deftest solve-test
  (testing "part 1"
    (is (= 2 (first (solve ">"))))
    (is (= 4 (first (solve "^>v<"))))
    (is (= 2 (first (solve "^v^v^v^v^v")))))
  (testing "part 2"
    (is (= 3  (second (solve "^v"))))
    (is (= 3  (second (solve "^>v<"))))
    (is (= 11 (second (solve "^v^v^v^v^v"))))))
