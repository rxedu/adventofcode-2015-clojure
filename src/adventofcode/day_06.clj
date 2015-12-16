(ns adventofcode.day-06)

(def actions
  {:on (constantly true)
   :off (constantly false)
   :toggle #(not %)})

(def brightness-actions
  {:on #(+ % 1)
   :off #(max 0 (- % 1))
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

(defn form-grid
  "Returns an n-by-n grid where the value at position [x y]
  is determined by the value of the state function on that position."
  [n state-fn]
  (let [grid-size (range n)]
    (reduce
     (fn [row x] (conj row (reduce
                            (fn [column y] (conj column (state-fn [x y])))
                            [] grid-size)))
     [] grid-size)))

(defn transform-grid
  "Applies an instruction to a grid and returns the modified grid."
  [actions grid instruction]
  (let [action ((first instruction) actions)
        [xr yr] (rest instruction)]
    (form-grid
     (count grid)
     (fn [[x y]]
       (let [light (nth (nth grid x) y)]
         (if (and (<= (first xr) x (last xr))
                  (<= (first yr) y (last yr)))
           (action light) light))))))

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
      (reduce (partial transform-grid actions)
              (init-grid 1000 false) instructions))
     (brightness
      (reduce (partial transform-grid brightness-actions)
              (init-grid 1000 0) instructions))]))
