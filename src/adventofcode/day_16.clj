(ns adventofcode.day-16)

(def suspect
  {:children 3
   :cats 7
   :samoyeds 2
   :pomeranians 3
   :akitas 0
   :vizslas 0
   :goldfish 5
   :trees 3
   :cars 2
   :perfumes 1})

(defn parse-sue
  "Converts a string for an Aunt Sue to a map,
  e.g., {1 {:cats 5, :cars 3, :trees 5}}."
  [string]
  (let [sue ((comp second first)
             (re-seq #"^Sue (\d+):" string))
        attributes (map (comp #(take 2 %) rest)
                        (re-seq #"(\w+): (\d+)" string))
        format-fn #(vector (keyword (first %)) (Integer. (second %)))]
    {(Integer. sue)
     (apply array-map (flatten (map format-fn attributes)))}))

(defn compare-sue
  "Given the required properties for the target Sue,
  returns whether the input Sue could be a match."
  [target sue]
  (not-any? false? (vals (merge-with = target sue))))

(defn solve
  "Given the input for the day, returns the solution."
  [input]
  (let [aunts
        (into {} (map parse-sue (clojure.string/split-lines input)))]
    [((comp first keys)
      (filter
       #(true? (compare-sue suspect (second %)))
       aunts))]))
