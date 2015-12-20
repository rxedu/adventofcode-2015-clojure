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

(defn compare-real-sue
  "Given the required properties for the target Sue,
  returns whether the input Sue could be a real match."
  [target sue]
  (let [gt [:cats :trees]
        lt [:pomeranians :goldfish]
        fn-gt #(select-keys % gt)
        fn-lt #(select-keys % lt)
        fn-eq #(apply (partial dissoc %) (concat gt lt))]
    ((comp (partial not-any? false?) flatten)
     (map vals [(merge-with = (fn-eq target) (fn-eq sue))
                (merge-with < (fn-gt target) (fn-gt sue))
                (merge-with > (fn-lt target) (fn-lt sue))]))))

(defn solve
  "Given the input for the day, returns the solution."
  [input]
  (let [aunts (into {} (map parse-sue (clojure.string/split-lines input)))
        finder-fn #((comp first keys) (filter % aunts))]
    [(finder-fn #(true? (compare-sue suspect (second %))))
     (finder-fn #(true? (compare-real-sue suspect (second %))))]))
