(ns adventofcode.day-01)

(def direction-strings
  {"("  1
   ")" -1})

(defn parse-directions
  "Converts a string of parentheses into a seq of 1 and -1
  according to direction-strings."
  [directions]
  (replace direction-strings (map str (seq directions))))

(defn steps-to-basement
  "Finds the first step that takes Santa to the basement."
  [directions]
  (count (take-while (complement nil?)
                     (reductions
                      #(if (= %1 -1) (reduced nil) (+ %1 %2))
                      directions))))

(defn solve
  "Given the input for the day, returns the solution."
  [input]
  (let [directions
        ((comp parse-directions clojure.string/trim-newline) input)]
    [(reduce + directions)
     (steps-to-basement directions)]))
