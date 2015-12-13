(ns adventofcode.day-02)

(defn input-to-dimensions
  "Converts a input string of the form 4x2x6
  to a vector [4 2 6]"
  [input-string]
  (map #(Integer. %) (clojure.string/split input-string #"x")))

(defn paper
  "Computes the total paper needed to wrap a present."
  [dimensions]
  (let [[x y z] (sort dimensions)]
    (+ (* 3 x y) (* 2 x z) (* 2 y z))))

(defn ribbon
  "Computes the total ribbon needed to wrap a present."
  [dimensions]
  (let [[x y z] (sort dimensions)]
    (+ (* 2 x) (* 2 y) (* x y z))))

(defn solve
  "Given the input for the day, returns the solution."
  [input]
  (let [all-dimensions
        (map input-to-dimensions (clojure.string/split-lines input))]
    [(reduce + (map paper all-dimensions))
     (reduce + (map ribbon all-dimensions))]))
