(ns adventofcode.day-15
  (:require [clojure.math.combinatorics]))

(defn integer-partitions
  "Returns a seq of all the ways to write the integer x
  as a sum of n non-negative integers."
  [x n]
  (filter #(= x (apply + %))
          (clojure.math.combinatorics/selections (range (inc x)) n)))

(defn parse-ingredient
  "Converts an ingredient string into a map,
  e.g., {:butterscotch {:capacity 5, :durability 2}}."
  [string]
  (let [ingredient ((comp second first)
                    (re-seq #"^(\w+):" string))
        properties (map (comp #(take 2 %) rest)
                        (re-seq #"(\w+) (-?\d+)" string))
        format-fn #(vector (keyword (first %)) (Integer. (second %)))]
    {((comp keyword clojure.string/lower-case) ingredient)
     (apply array-map (flatten (map format-fn properties)))}))

(defn cookie
  "Given a list of ingredients and the amounts of each,
  returns the totaled values of all properties."
  [ingredients amounts]
  ((comp #(apply (partial merge-with +) %) vals)
   (reduce
    (fn [scores amount]
      (let [ingredient (first amount)
            quantity (second amount)
            properties (ingredient ingredients)]
        (assoc scores
               ingredient
               (zipmap (keys properties)
                       (map #(* % quantity) (vals properties))))))
    {} amounts)))

(defn score
  "Given a list of a cookie's properties, returns the total cookie score."
  [cookie]
  (reduce #(* %1 (max 0 %2)) 1 (vals (dissoc cookie :calories))))

(defn solve
  "Given the input for the day, returns the solution."
  [input]
  (let [ingredients
        (into {} (map parse-ingredient (clojure.string/split-lines input)))
        types (keys ingredients)
        cookie-fn #(cookie ingredients (zipmap types %))
        partitions (integer-partitions 100 (count types))]
    [(reduce #(max %1 (score (cookie-fn %2))) 0 partitions)
     (reduce (fn [best amounts]
               (let [cookie (cookie-fn amounts)]
                 (max best (if (= 500 (:calories cookie)) (score cookie) 0))))
             0 partitions)]))
