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

(defn transform-grid
  "Applies a seq of instructions with corresponding actions
  to an n-by-n grid initialed with the given type
  (either Boolean/TYPE or Integer/TYPE)
  and returns the transformed grid."
  [actions n init-type instructions]
  (let [aset-fn (if (= init-type Integer/TYPE) aset-int aset)
        agrid (make-array init-type n n)]
    (dorun
     (for [instruction instructions
           :let [[action rows columns] instruction]]
       (dorun
        (for [x (range (first rows) (inc (second rows)))]
          (dorun
           (for [y (range (first columns) (inc (second columns)))]
             (aset-fn agrid x y
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
      (transform-grid actions 1000 Boolean/TYPE instructions))
     (brightness
      (transform-grid brightness-actions 1000 Integer/TYPE instructions))]))
