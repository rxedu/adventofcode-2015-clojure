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

(deftest remove-maps-test
  (is (= 42 (remove-maps 42)))
  (is (= [:a :b] (remove-maps {:a :b})))
  (is (= [1 2 3] (remove-maps [1 2 3])))
  (is (= [1 :a 1 :b 2 3] (remove-maps [1 {:a 1 :b 2} 3])))
  (is (= [1 3] (remove-maps [1 {:a 1 :b "red"} 3] "red"))))

(deftest solve-test
  (testing "part 1"
    (is (= 8 (first (solve "{\"a\":2,\"b\":4,\"c\":[1,-3,{\"d\":4}]}")))))
  (testing "part 2"
    (is (= 6 (second (solve "[1,2,3]"))))
    (is (= 4 (second (solve "[1,{\"c\":\"red\",\"b\":2},3]"))))
    (is (= 0 (second (solve "{\"d\":\"red\",\"e\":[1,2,3,4],\"f\":5}"))))
    (is (= 6 (second (solve "[1,\"red\",5]"))))))
