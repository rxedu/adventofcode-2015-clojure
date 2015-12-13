(ns adventofcode.day-01)

(defn final-floor
  "Finds the floor Santa ends on."
  [directions]
  (let [[ups downs]
        (split-with (partial = "(") (map str (seq (sort directions))))]
    (- (count ups) (count downs))))

(defn solve
  "Given the input for the day, returns the solution."
  [input]
  (let [directions (clojure.string/trim-newline input)]
    (str [(final-floor directions)])))
