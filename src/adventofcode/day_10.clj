(ns adventofcode.day-10)

(defn look-and-say
  "Returns the look-and-say lazy-seq starting with n (or 1 by default)."
  ([] (look-and-say 1))
  ([n] (let [n (str n)]
         (cons n (look-and-say n n))))
  ([n, _] (let [digits (seq (str n))
                groups (partition-by identity digits)
                looked-and-said
                (map #(apply str (vector (count %) (first %))) groups)
                nxt (apply str looked-and-said)]
            (lazy-seq (cons nxt (look-and-say nxt _))))))

(defn solve
  "Given the input for the day, returns the solution."
  [input]
  (let [n (Integer. (clojure.string/trim-newline input))
        count-last #((comp count last) (take % (look-and-say n)))]
    [(count-last 41)
     (count-last 51)]))
