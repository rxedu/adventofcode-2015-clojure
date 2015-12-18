(ns adventofcode.day-12)

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

(defn solve
  "Given the input for the day, returns the solution."
  [input]
  [(sum-numbers input)])
