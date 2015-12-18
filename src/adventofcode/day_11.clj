(ns adventofcode.day-11)

(def ** #(reduce * (repeat %2 %1)))
(def alph (map char (range 97 123)))

(defn alph-triples
  "Returns a lazy-seq of strings: (abc, bcd, cde,..., xyz)."
  ([] (alph-triples 0))
  ([n] (lazy-seq
        (if (= n 24)
          nil
          (cons (apply str (map (partial nth alph) (range n (+ n 3))))
                (alph-triples (inc n)))))))

(defn valid-password?
  "Returns true if the string is a valid password, else false."
  [string]
  (boolean (and
            (nil? (re-seq #"[iol]" string))
            (<= 2 (count (set (re-seq #"(\w)\1" string))))
            (some #(re-seq (re-pattern %) string) (alph-triples)))))

(defn str->int
  "Converts a string to its integer representation."
  [string]
  (let [base (count alph)
        idx-alph (into {} (map-indexed #(vector %2 %1) alph))]
    (apply + (map-indexed
              #(* (** base %1) (get idx-alph %2))
              ((comp rseq vec seq) string)))))

(defn int->str
  "Converts an integer to its string representation."
  [integer]
  (if (zero? integer)
    ((comp str first) alph)
    (loop [n integer
           digits ()]
      (let [b (count alph)]
        (if (pos? n)
          (recur (quot n b)
                 (conj digits (nth alph (mod n b))))
          (apply str digits))))))

(defn inc-string
  "Increments a string lexicographically."
  [string]
  ((comp int->str inc str->int) string))

(defn pad-string
  "Pads string to length n by repeatedly prepending a."
  [n string]
  (loop [digits (seq string)]
    (if (<= n (count digits))
      (apply str digits)
      (recur (conj digits (first alph))))))

(defn inc-password
  "Returns the input string if it's a valid password,
  otherwise returns the next valid password."
  [password]
  (loop [string password]
    (if (valid-password? string)
      string
      (let [next-string (inc-string string)]
        (recur (pad-string
                (max (count string) (count next-string))
                next-string))))))

(defn solve
  "Given the input for the day, returns the solution."
  [input]
  (let [password (clojure.string/trim-newline input)
        next-password (inc-password password)]
    [next-password
     (inc-password
      (pad-string (count next-password) (inc-string next-password)))]))
