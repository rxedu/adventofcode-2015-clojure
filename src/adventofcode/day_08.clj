(ns adventofcode.day-08)

(def matchers
  {:quote #"\\\""
   :slash #"\\\\"
   :xcode #"\\x[0-9a-f]{2}"})

(def matcher-order [:slash :xcode :quote])

(defn in-memory-characters
  "Returns the number of in-memory characters for a string."
  [string]
  (+ -2 (count
         (reduce #(clojure.string/replace %1 %2 "Z")
                 string (map matchers matcher-order)))))

(defn solve
  "Given the input for the day, returns the solution."
  [input]
  (let [strings (clojure.string/split-lines input)]
    [(- (reduce + (map count strings))
        (reduce + (map in-memory-characters strings)))]))
