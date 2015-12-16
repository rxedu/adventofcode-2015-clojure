(ns adventofcode.day-09
  (:require [clojure.math.combinatorics]))

(defn parse-connection
  "Converts a connection from a string to the form {#{:a :b} distance},
  e.g., Alpha to Beta = 100 becomes {#{:alpha :beta} 100}."
  [string]
  (let [matches
        ((comp reverse rest) (re-matches #"(\w+) to (\w+) = (\d+)" string))]
    (assoc {}
           (set (map (comp keyword clojure.string/lower-case) (rest matches)))
           (Integer. (first matches)))))

(defn locations
  "Given a map of connections, returns a set of locations."
  [connections]
  (set (apply concat (keys connections))))

(defn distance
  "Given a map of connections, returns the total distance for a route."
  [connections route]
  (first (reduce (fn [[dist pre] nxt]
                   [(+ dist (connections (set [pre nxt]))) nxt])
                 [0 (first route)] (rest route))))

(defn distances
  "Given a seq of connections, returns the set of distances
  for all routes that visit each location exactly once."
  [connections]
  (set (map (partial distance connections)
            (clojure.math.combinatorics/permutations
             (locations connections)))))

(defn solve
  "Given the input for the day, returns the solution."
  [input]
  (let [connections
        (into {} (map parse-connection
                      (clojure.string/split-lines input)))
        all-distances (distances connections)]
    [(apply min all-distances) (apply max all-distances)]))
