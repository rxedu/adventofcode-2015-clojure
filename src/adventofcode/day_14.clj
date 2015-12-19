(ns adventofcode.day-14)

(defn parse-reindeer
  "Converts a reindeer string to a map, e.g.,
  {:rudolph {:speed 10 :fly 15 :rest 5}}."
  [string]
  (let [matches
        (rest (re-matches #"(\w+)\D+(\d+)\D+(\d+)\D+(\d+)\D+\." string))]
    (assoc {}
           ((comp keyword clojure.string/lower-case) (first matches))
           (into {} (map hash-map
                         [:speed :fly :rest]
                         (map #(Integer/parseInt %) (rest matches)))))))

(defn flying?
  "Returns if the reindeer is flying after t seconds."
  [reindeer t]
  (< 0 (mod t (apply + (map reindeer [:fly :rest])))
     (inc (:fly reindeer))))

(defn distance
  "Returns to the total distance a reindeer has traveled after t seconds."
  [reindeer t]
  (reduce
   #(if (flying? reindeer %2) (+ %1 (:speed reindeer)) %1)
   0 (range (inc t))))

(defn points
  "Given a map of reindeer, returns a map of reindeer to
  points accumulated after t seconds."
  [reindeer t]
  (let [names (keys reindeer)
        distance-memo (memoize distance)]
    (reduce
     (fn [pts t]
       (if (zero? t)
         pts
         (let [distances
               (reduce #(assoc %1 %2 (distance-memo (%2 reindeer) t))
                       {} names)
               max-distance
               (apply max (vals distances))
               leaders
               (keys (into {} (filter
                               #(= max-distance (second %))
                               (into [] distances))))]
           (into pts (map vector
                          leaders
                          (map inc (map pts leaders)))))))
     (into {} (map vector names (repeat 0)))
     (range (inc t)))))

(defn solve
  "Given the input for the day, returns the solution."
  [input]
  (let [reindeer
        (into {} (map parse-reindeer (clojure.string/split-lines input)))]
    [(apply max (map #(distance % 2503) (vals reindeer)))
     (apply max (vals (points reindeer 2503)))]))
