(ns adventofcode.day-20)

(set! *unchecked-math* true)

(defn house-presents
  "Returns the number of presents delivered to the nth house."
  [n]
  (let [presents-fn #(if (zero? ^int (mod n %2))
                       (+ ^int %1 ^int %2) %1)]
    (* 10 ^int (reduce presents-fn 0 (range 1 (inc ^int n))))))

(defn house-presents-fifty
  "Returns the number of presents delivered to the nth house,
  but elf only delivers to fifty houses."
  [n]
  (let [presents-fn #(if (and (zero? ^int (mod n %2))
                              (>= 50 (/ ^int n %2)))
                       (+ ^int %1 ^int %2) %1)]
    (* 11 ^int (reduce presents-fn 0 (range 1 (inc ^int n))))))

(defn solve
  "Given the input for the day, returns the solution."
  [input]
  (let [presents (Integer. (clojure.string/trim-newline input))]
    [(loop [n 1]
       (let [total-house-presents (house-presents n)]
         (if (< ^int total-house-presents ^int presents)
           (recur (inc n))
           n)))
     (loop [n 1]
       (let [total-house-presents (house-presents-fifty n)]
         (if (< ^int total-house-presents ^int presents)
           (recur (inc n))
           n)))]))
