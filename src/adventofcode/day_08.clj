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

(defn encoded-characters
  "Returns the number of characters in a string after encoding."
  [string]
  (+ 2 (count (clojure.string/replace string #"[\"\\]" "ZZ"))))

(defn solve
  "Given the input for the day, returns the solution."
  [input]
  (let [strings (clojure.string/split-lines input)
        characters-of-code (reduce + (map count strings))]
    [(- characters-of-code
        (reduce + (map in-memory-characters strings)))
     (- (reduce + (map encoded-characters strings))
        characters-of-code)]))
