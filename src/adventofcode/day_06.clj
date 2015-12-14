(ns adventofcode.day-06)

(def actions
  {:on (constantly true) :off (constantly false) :toggle #(not %)})

(defn string-to-instruction
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
  "Returns a lazy n-by-n grid where every element is a the given constant."
  [n constant]
  (repeat n (repeat n constant)))

(defn form-grid
  "Returns an n-by-n grid where the state of the light at position [x y]
  is determined by the value of the state function on that location."
  [n state-fn]
  (let [grid-size (range n)]
    (reduce
     (fn [row x] (conj row (reduce
                            (fn [column y] (conj column (state-fn [x y])))
                            [] grid-size)))
     [] grid-size)))

(defn modify-grid
  "Applies an instruction to a grid of lights and returns the modified grid."
  [grid instruction actions]
  (let [action ((first instruction) actions)
        [xr yr] (rest instruction)]
    (form-grid
     (count grid)
     (fn [[x y]]
       (let [light (nth (nth grid x) y)]
         (if (and (<= (first xr) x (last xr))
                  (<= (first yr) y (last yr)))
           (action light) light))))))

(defn solve
  "Given the input for the day, returns the solution."
  [input]
  (let [instructions (map string-to-instruction
                          (clojure.string/split-lines input))]
    [((comp #(get % true) frequencies flatten)
      (reduce #(modify-grid %1 %2 actions)
              (init-grid 1000 false) instructions))]))
