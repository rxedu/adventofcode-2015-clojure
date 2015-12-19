(ns adventofcode.day-15-test
  (:require [clojure.test :refer :all]
            [adventofcode.day-15 :refer :all]))

(deftest integer-partitions-test
  (is (= [[0 0 2] [0 1 1] [0 2 0] [1 0 1] [1 1 0] [2 0 0]]
         (integer-partitions 2 3))))

(deftest parse-ingredient-test
  (is (= {:butterscotch
          {:capacity -1 :durability -2 :flavor 6 :texture 3 :calories 8}}
         (parse-ingredient "Butterscotch: capacity -1, durability -2,
                           flavor 6, texture 3, calories 8"))))

(deftest cookie-test
  (let [ingredients
        {:butterscotch {:calories 8, :flavor  6, :texture  3}
         :cinnamon     {:calories 3, :flavor -2, :texture -1}}]
    (is (= {:calories 16, :flavor 12, :texture 6}
           (cookie ingredients {:butterscotch 2})))
    (is (= {:calories 11, :flavor 4, :texture 2}
           (cookie ingredients {:butterscotch 1 :cinnamon 1})))
    (is (= {:calories 25, :flavor 6, :texture 3}
           (cookie ingredients {:butterscotch 2 :cinnamon 3})))))

(deftest score-test
  (let
   [ingredients
    {:butterscotch
     {:calories 8, :capacity -1, :durability -2, :flavor  6, :texture  3}
     :cinnamon
     {:calories 3, :capacity  2, :durability  3, :flavor -2, :texture -1}}]
    (are [s a] (= s (score (cookie ingredients a)))
      0        {:butterscotch 1}
      0        {:butterscotch 0  :cinnamon 1}
      0        {:butterscotch 10 :cinnamon 1}
      8        {:butterscotch 1  :cinnamon 1}
      62842880 {:butterscotch 44 :cinnamon 56})))
