(ns adventofcode.day-05)

(defn nice?
  "Checks if a string is nice."
  [string]
  (boolean (and
            (re-seq #"(\w)\1" string)
            (< 2 (count (re-seq #"[aeiou]" string)))
            (nil? (re-seq #"(ab|cd|pq|xy)" string)))))

(defn really-nice?
  "Checks if a string is really nice."
  [string]
  (boolean (and
            (re-seq #"(\w)\w\1" string)
            (or
             (re-seq #"(\w{2})\1" string)
             (re-seq #"(\w{2})\w+\1" string)))))

(defn solve
  "Given the input for the day, returns the solution."
  [input]
  (let [strings (clojure.string/split-lines input)]
    [(get (frequencies (map nice? strings)) true)
     (get (frequencies (map really-nice? strings)) true)]))
