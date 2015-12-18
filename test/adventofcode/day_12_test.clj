(ns adventofcode.day-12-test
  (:require [clojure.test :refer :all]
            [adventofcode.day-12 :refer :all]))

(deftest sum-numbers-test
  (is (= 6 (sum-numbers "[1,2,3]")))
  (is (= 6 (sum-numbers "{\"a\":2,\"b\":4}")))
  (is (= 3 (sum-numbers "[[[3]]]")))
  (is (= 3 (sum-numbers "{\"a\":{\"b\":4},\"c\":-1}")))
  (is (= 0 (sum-numbers "{\"a\":[-1,1]}")))
  (is (= 0 (sum-numbers "[-1,{\"a\":1}]")))
  (is (= 0 (sum-numbers "[]")))
  (is (= 0 (sum-numbers "{}")))
  (is (= 608
         (sum-numbers
          "{\"e\":57,\"c\":\"red\",\"a\":167,\"b\":[-42,147,166,74,-32,
          \"orange\",\"violet\",\"yellow\"],\"d\":\"green\",\"f\":71}}"))))
