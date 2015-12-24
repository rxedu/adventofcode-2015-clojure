(ns adventofcode.day-23)

(def matchers
  {:hlf #"hlf (\w)"
   :tpl #"tpl (\w)"
   :inc #"inc (\w)"
   :jmp #"jmp ([+-]?\d+)"
   :jie #"jie (\w), ([+-]\d+)"
   :jio #"jio (\w), ([+-]\d+)"})

(def parsers
  (let [convert #(if (re-find #"[+-]?\d+" %) (Integer. %) (keyword %))
        single-input #(vector (convert %))
        double-input #(vector (convert %1) (convert %2))]
    {:hlf single-input
     :tpl single-input
     :inc single-input
     :jmp single-input
     :jie double-input
     :jio double-input}))

(def instruction-fns
  {:hlf #(assoc %1 :idx (inc (:idx %1)) (first %2) (/ ((first %2) %1) 2))
   :tpl #(assoc %1 :idx (inc (:idx %1)) (first %2) (* 3 ((first %2) %1)))
   :inc #(assoc %1 :idx (inc (:idx %1)) (first %2) (inc ((first %2) %1)))
   :jmp #(assoc %1 :idx (+ (first %2) (:idx %1)))
   :jie #(assoc %1 :idx (if (even? ((first %2) %1))
                          (+ (second %2) (:idx %1)) (inc (:idx %1))))
   :jio #(assoc %1 :idx (if (= 1 ((first %2) %1))
                          (+ (second %2) (:idx %1)) (inc (:idx %1))))})

(defn parse-instruction
  "Converts an instruction sting to a map."
  [string]
  (some (fn [[ins matches]]
          (and matches (hash-map ins (apply (ins parsers) (rest matches)))))
        (reduce
         (fn [new-map [k v]]
           (assoc new-map k (re-matches v string)))
         {} matchers)))

(defn run-program
  "Runs a program of instructions."
  [instructions]
  (reduce
   (fn [{:keys [idx] :as state} ins]
     (if (not (< -1 idx (count ins)))
       (reduced state)
       (let [instruction (first (vec (nth ins idx)))
             i (first instruction)
             args (second instruction)]
         ((i instruction-fns) state args))))
   {:idx 0 :a 0 :b 0}
   (repeat instructions)))

(defn solve
  "Given the input for the day, returns the solution."
  [input]
  (let [instructions
        (map parse-instruction (clojure.string/split-lines input))]
    (println instructions)
    [(:b (run-program instructions))]))
