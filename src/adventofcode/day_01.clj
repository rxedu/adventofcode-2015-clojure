(ns adventofcode.day-01)

(defn final-floor
  "Finds the floor Santa ends on."
  [directions]
  (let [[ups downs]
        (split-with (partial = "(") (map str (seq (sort directions))))]
    (- (count ups) (count downs))))

(defn paren-to-int
  "Converts ( to 1, ) to -1, and anything else to zero."
  [paren]
  (case paren "(" 1 ")" -1 0))

(defn steps-to-basement
  "Finds the first step that takes Santa to the basement."
  [directions]
  (let [direction-vector (map (comp paren-to-int str) (seq directions))]
    (count (take-while (complement nil?)
                       (reductions
                        #(if (= %1 -1) (reduced nil) (+ %1 %2))
                        direction-vector)))))

(defn solve
  "Given the input for the day, returns the solution."
  [input]
  (let [directions (clojure.string/trim-newline input)]
    (str [(final-floor directions)
          (steps-to-basement directions)])))
