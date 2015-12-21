(ns adventofcode.day-19)

(defn parse-replacements
  "Converts a replacement string, e.g., H => HO, to a vector."
  [string]
  (apply vector (rest (re-matches #"(\w+) => (\w+)" string))))

(defn replace-nth
  "Replace the nth match (zero-indexed) in string."
  [string match replacement n]
  (let [split-str (clojure.string/split string (re-pattern match))
        split-seq (split-at (inc n) split-str)
        joined-seq (map (partial clojure.string/join match) split-seq)
        joined-str (clojure.string/join replacement joined-seq)
        num-matches (dec (count split-str))]
    (if (zero? num-matches)
      string
      (if (and (= match (str (last string)))
               (not= n num-matches))
        (str joined-str match)
        joined-str))))

(defn distinct-replacements
  "Returns a set of the possible strings formed
  by making a single replacement."
  [string match replacement]
  (set (map (partial replace-nth string match replacement)
            ((comp range count (partial re-seq (re-pattern match)))
             string))))

(defn solve
  "Given the input for the day, returns the solution."
  [input]
  (let [input (clojure.string/split-lines input)
        replacements (map parse-replacements (drop-last 2 input))
        molecule (last input)]
    [(count
      (reduce
       #(clojure.set/union
         %1 (apply (partial distinct-replacements molecule) %2))
       #{} replacements))]))
