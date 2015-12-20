(ns adventofcode.day-17
  (:require [clojure.math.combinatorics]))

(defn container-combinations
  "Given a seq of available container sizes,
  returns a seq of possible ways to store the quantity x."
  [sizes x]
  (filter #(= x (reduce + (map second %)))
          (clojure.math.combinatorics/subsets
           (map-indexed #(vector %1 %2) sizes))))

(defn min-container-combinations
  "Given a seq of possible container combinations,
  returns the number those combinations which use
  the fewest number of containers."
  [combinations]
  (let [num-ways (frequencies (map count combinations))]
    (get num-ways (apply min (keys num-ways)))))

(defn solve
  "Given the input for the day, returns the solution."
  [input]
  (let [sizes (map #(Integer/parseInt %)
                   (clojure.string/split-lines input))
        combinations (container-combinations sizes 150)]
    [(count combinations)
     (min-container-combinations combinations)]))
