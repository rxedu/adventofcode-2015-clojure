(ns adventofcode.day-05)

(defn nice?
  "Checks if a string is nice."
  [string]
  (if (and
       (re-find #"(\w)\1" string)
       (< 2 (count (re-seq #"[aeiou]" string)))
       (nil? (re-find #"(ab|cd|pq|xy)" string)))
    true false))

(defn really-nice?
  "Checks if a string is really nice."
  [string]
  (if (and
       (re-find #"(\w)\w\1" string)
       (or
        (re-find #"(\w{2})\1" string)
        (re-find #"(\w{2})\w+\1" string)))
    true false))

(defn solve
  "Given the input for the day, returns the solution."
  [input]
  (let [strings (clojure.string/split-lines input)]
    [(get (frequencies (map nice? strings)) true)
     (get (frequencies (map really-nice? strings)) true)]))
