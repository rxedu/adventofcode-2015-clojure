(ns adventofcode.day-13
  (:require [clojure.math.combinatorics]))

(defn parse-relation
  "Converts a happiness relation from a string
  to the form {{:alice :bob} happiness},
  e.g., Alice would gain 100 happiness units by sitting next to Bob
  becomes {{:alice :bob} 100}."
  [string]
  (let [matcher
        #"(\w+) would (\w+) (\d+) happiness units by sitting next to (\w+)\."
        [a sign value b]
        (rest (re-matches matcher string))
        signed-value
        (if (= sign "gain") (Integer. value) (- 0 (Integer. value)))]
    (assoc {} (apply array-map
                     (map (comp keyword clojure.string/lower-case) [a b]))
           signed-value)))

(defn guests
  "Given a map of relations, returns a set of guests."
  [relations]
  ((comp set flatten) (apply concat (keys relations))))

(defn happiness
  "Given a map of relations,
  returns the total happiness for a seating arrangement"
  [relations seating]
  (let [table (concat seating [(first seating)] ((comp reverse) seating))]
    (first (reduce (fn [[dist pre] nxt]
                     [(+ dist (relations (apply array-map [pre nxt]))) nxt])
                   [0 (first table)] (rest table)))))

(defn potential-happiness
  "Given a seq of relations, returns the set of potential happiness
  for all seating arrangements."
  [relations]
  (set (map (partial happiness relations)
            (clojure.math.combinatorics/permutations
             (guests relations)))))

(defn self-relations
  "Given a list of guests, returns a seq of relations
  for the completely neutral (zero happiness) guest :self."
  [guests]
  (reduce #(into %1 {{:self %2} 0 {%2 :self} 0}) {} guests))

(defn solve
  "Given the input for the day, returns the solution."
  [input]
  (let [relations
        (into {} (map parse-relation
                      (clojure.string/split-lines input)))]
    [(apply max (potential-happiness relations))
     (apply max (potential-happiness
                 (into relations (self-relations (guests relations)))))]))
