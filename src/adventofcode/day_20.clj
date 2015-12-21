(ns adventofcode.day-20
  (:require [adventofcode.day-04]))

(set! *unchecked-math* :warn-on-boxed)

; (defn elf-houses
;   "Returns a lazy seq of houses visited by the nth elf."
;   [n]
;   (take-nth n (drop (dec n) (adventofcode.day-04/positive-numbers))))

(defn visited?
  "Returns true if house i is visited by elf n, else false."
  [i n]
  (zero? ^int (mod i n)))

(def visited?-memo (memoize visited?))
(def presents-memo
  (memoize #(if (visited?-memo %1 %3) (+ ^int %2 ^int %3) %2)))

; (defn house-presents
;   "Returns a lazy seq of the number of presents
;   delivered to each house."
;   ([] (house-presents 1))
;   ([n] (lazy-seq
;         (cons (reduce (partial presents-memo n) 0 (range 1 (inc n)))
;               (house-presents (inc n))))))

(defn house-presents
  "Returns the number of presents delivered to the nth house."
  [n]
  (reduce (partial presents-memo n) 0 (range 1 (inc ^int n))))

(defn solve
  "Given the input for the day, returns the solution."
  [input]
  (let [presents
        (/ (Integer. (clojure.string/trim-newline input)) 10)]
    [(loop [n 1]
       (let [total-house-presents (house-presents n)]
         (if (< ^int total-house-presents ^int presents)
           (recur (inc n))
           n)))]))
