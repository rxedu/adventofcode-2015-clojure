(ns adventofcode.day-04
  (:require digest))

(def md5-memo (memoize digest/md5))

(defn positive-numbers
  "Returns a lazy seq of positive numbers: 1, 2, 3,..."
  ([] (positive-numbers 1))
  ([n] (lazy-seq (cons n (positive-numbers (inc n))))))

(defn count-leading-zeros
  "Returns the number of leading zeros in a string."
  [string]
  (count (re-find #"^[0]+" string)))

(defn zero-salted-hash-search
  "Returns the first element whose MD5 hash has n leading zeros.
  Each element is prepended with salt before computing the hash."
  [elements salt n]
  (some
   #(if (= n (count-leading-zeros (md5-memo (str salt %)))) %)
   elements))

(defn solve
  "Given the input for the day, returns the solution."
  [input]
  (let [elements (positive-numbers)
        salt (clojure.string/trim-newline input)]
    [(zero-salted-hash-search elements salt 5)
     (zero-salted-hash-search elements salt 6)]))
