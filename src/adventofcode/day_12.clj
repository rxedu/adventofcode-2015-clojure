(ns adventofcode.day-12
  (:require [clojure.data.json]))

(def parse-int #(Integer/parseInt %))

(def matchers
  [#"\[(-?\d+)\]"
   #",(-?\d+)\]"
   #"(-?\d+),"
   #":(-?\d+)}"])

(defn sum-numbers
  "Sums all numbers in a JSON formatted string."
  [string]
  (reduce + (map
             #(apply + (map (comp parse-int second) (re-seq % string)))
             matchers)))

(defn remove-maps
  "Returns a flattened seq of the given collection.
  Optionally omits any maps that contain the given value."
  ([collection] (remove-maps collection nil))
  ([collection value]
   (if (coll? collection)
     (loop [tree (if (and (map? collection)
                          (some #(= % value) (vals collection)))
                   [] collection)
            elements ()]
       (if (empty? tree)
         ((comp flatten reverse) elements)
         (recur (rest tree)
                (cons (remove-maps (first tree) value) elements))))
     collection)))

(defn solve
  "Given the input for the day, returns the solution."
  [input]
  (let [data (vector (clojure.data.json/read-str input))
        total (sum-numbers input)
        total-fn #(reduce + (filter integer? %))]
    [(and (= total
             ; Alternate solution using the function needed for part 2.
             (total-fn (remove-maps data nil)))
          total)
     (total-fn (remove-maps data "red"))]))
