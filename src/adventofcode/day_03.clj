(ns adventofcode.day-03)

(defn move-to-vector
  "Converts a move (><^v) to a vector that represents the move,
  e.g., > becomes [1 0] and v becomes [0 -1].
  Anything else is converted to [0 0]."
  [move]
  (case move
    ">" [1 0]
    "<" [-1 0]
    "^" [0 1]
    "v" [0 -1]
    [0 0]))

(defn moves-to-seq
  "Converts a string of moves to a seq of vectors using move-to-vector."
  [moves]
  (map (comp move-to-vector str) (seq moves)))

(defn track-houses
  "Given a seq of moves as vectors, returns a set of all visited houses."
  [moves]
  (last (reduce #(let [next-house (map + (first %1) %2)]
                   [next-house (conj (last %1) next-house)])
                [[0 0] #{[0 0]}] moves)))

(defn solve
  "Given the input for the day, returns the solution."
  [input]
  [((comp count track-houses moves-to-seq) input)])
