(ns adventofcode.day-17
  (:require [clojure.math.combinatorics]))

(defn container-combinations
  "Given a seq of available container sizes,
  returns the total number of ways to store the quantity x."
  [sizes x]
  (count (filter
          #(= x (reduce + (map second %)))
          (clojure.math.combinatorics/subsets
           (map-indexed #(vector %1 %2) sizes)))))

(defn solve
  "Given the input for the day, returns the solution."
  [input]
  (let [sizes (map #(Integer/parseInt %)
                   (clojure.string/split-lines input))]
    [(container-combinations sizes 150)]))
