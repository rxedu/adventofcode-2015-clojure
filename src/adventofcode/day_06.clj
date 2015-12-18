(ns adventofcode.day-06)

(def actions
  {:on (constantly true)
   :off (constantly false)
   :toggle #(not %)})

(def brightness-actions
  {:on inc
   :off #(max 0 (dec %))
   :toggle #(+ % 2)})

(defn parse-instruction
  "Converts an instruction from a string to a vector,
  e.g., turn on 0,5 through 2,10 becomes [:on [0 2] [5 10]]."
  [string]
  (let [matches
        (rest (re-matches
               #"^(turn on|turn off|toggle) (\d+),(\d+) through (\d+),(\d+)$"
               string))
        command (keyword (clojure.string/replace (first matches) "turn " ""))
        lights (map #(Integer. %) (rest matches))]
    [command (take-nth 2 lights) (take-nth 2 (rest lights))]))

(defn init-grid
  "Returns a lazy n-by-n grid
  where the value of every element is the given constant."
  [n constant]
  (repeat n (repeat n constant)))

(defn transform-grid
  "Applies a seq of instructions with corresponding actions
  to a grid and returns the transformed grid."
  [actions grid instructions]
  (let [agrid (to-array-2d grid)]
    (dorun
     (for [instruction instructions
           :let [[action rows columns] instruction]]
       (dorun
        (for [x (range (first rows) (inc (second rows)))]
          (dorun
           (for [y (range (first columns) (inc (second columns)))]
             (aset agrid x y
                   ((action actions) (aget agrid x y)))))))))
    (map (partial into []) (into [] agrid))))

(defn lit-lights
  "Counts the total number of lit lights on a grid."
  [grid]
  (or ((comp #(get % true) frequencies flatten) grid) 0))

(defn brightness
  "Returns the total brightness of all lights on a grid."
  [grid]
  (reduce + (flatten grid)))

(defn solve
  "Given the input for the day, returns the solution."
  [input]
  (let [instructions
        (map parse-instruction (clojure.string/split-lines input))]
    [(lit-lights
      (transform-grid actions (init-grid 1000 false) instructions))
     (brightness
      (transform-grid brightness-actions (init-grid 1000 0) instructions))]))
