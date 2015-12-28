(ns adventofcode.day-25)

(defn parse-message
  "Converts the code request message to a grid point."
  [string]
  (reverse (map #(Integer. %)
                (rest (re-find #"(\d+),\D+(\d+)" string)))))

(defn triangular
  "Generates lazy seq of triangular numbers."
  ([] (triangular 0 1))
  ([sum n]
   (let [sum (+ sum n)]
     (cons sum (lazy-seq (triangular sum (inc n)))))))

(def triangular-memo (memoize triangular))

(defn column
  "Generates a lazy seq of the code indexes for column x."
  ([x] (column x (nth (triangular-memo) (dec x)) 0))
  ([x sum n]
   (let [sum (+ sum n (if (zero? n) 0 (dec x)))]
     (cons sum (lazy-seq (column x sum (inc n)))))))

(def column-memo (memoize column))

(defn code-index
  "Given a grid point [column row], returns the code index for that point."
  [[x y]]
  (nth (column-memo x) (dec y)))

(defn codes
  "Returns a lazy seq of the codes."
  ([] (codes 20151125 0))
  ([sum n]
   (let [new-sum (if (zero? n)
                   sum
                   (rem (* sum 252533) 33554393))]
     (cons new-sum (lazy-seq (codes new-sum (inc n)))))))

(defn code-at
  "Returns the code at the given point."
  [point]
  (nth (codes) (dec (code-index point))))

(defn solve
  "Given the input for the day, returns the solution."
  [input]
  (let [point (parse-message (clojure.string/trim-newline input))]
    [(code-at point)]))
