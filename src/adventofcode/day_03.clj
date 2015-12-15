(ns adventofcode.day-03)

(def move-strings
  {">" [1 0]
   "<" [-1 0]
   "^" [0 1]
   "v" [0 -1]})

(defn parse-moves
  "Converts a string of moves to a seq of vectors
  according to move-strings, e.g.,
  >^V< becomes [[1 0], [0 1], [0 -1], [-1 0]]."
  [moves]
  (replace move-strings (map str (seq moves))))

(defn track-houses
  "Given a seq of moves as vectors, returns a set of all visited houses."
  [moves]
  (last (reduce #(let [next-house (map + (first %1) %2)]
                   [next-house (conj (last %1) next-house)])
                [[0 0] #{[0 0]}] moves)))
(defn solve
  "Given the input for the day, returns the solution."
  [input]
  (let [moves ((comp parse-moves clojure.string/trim-newline) input)]
    [((comp count track-houses) moves)
     ((comp count set)
      (apply concat (map track-houses
                         [(take-nth 2 moves)
                          (take-nth 2 (rest moves))])))]))
