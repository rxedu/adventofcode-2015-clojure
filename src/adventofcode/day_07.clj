(ns adventofcode.day-07)

(def matchers
  {:sig #"(\d+) -> (\w+)"
   :con #"(\w+) -> (\w+)"
   :not #"NOT (\w+) -> (\w+)"
   :and #"(\w+) AND (\w+) -> (\w+)"
   :1nd #"1 AND (\w+) -> (\w+)"
   :or  #"(\w+) OR (\w+) -> (\w+)"
   :lsh #"(\w+) LSHIFT (\d+) -> (\w+)"
   :rsh #"(\w+) RSHIFT (\d+) -> (\w+)"})

(def elements
  {:sig #(hash-map (keyword %2) (vector (Integer. %1)))
   :con #(hash-map (keyword %2) (vector (keyword %1)))
   :not #(hash-map (keyword %2) (vector (keyword %1)))
   :and #(hash-map (keyword %3) (vector (keyword %1) (keyword %2)))
   :1nd #(hash-map (keyword %2) (vector 1 (keyword %1)))
   :or  #(hash-map (keyword %3) (vector (keyword %1) (keyword %2)))
   :lsh #(hash-map (keyword %3) (vector (keyword %1) (Integer. %2)))
   :rsh #(hash-map (keyword %3) (vector (keyword %1) (Integer. %2)))})

(def gates
  {:sig identity
   :con identity
   :not bit-not
   :and bit-and
   :1nd bit-and
   :or  bit-or
   :lsh bit-shift-left
   :rsh bit-shift-right})

(defn parse-connection
  "Converts a connection string to a vector of the general form:
  [:type {:output [input(s)]}]."
  [string]
  (some (fn [[el matches]]
          (and matches [el (apply (el elements) (rest matches))]))
        (reduce
         (fn [new-map [k v]]
           (assoc new-map k (re-matches v string)))
         {} matchers)))

(defn connection-inputs
  "Given a connection, returns the required inputs as seq of keywords."
  [connection]
  (filter keyword? ((comp flatten vals second) connection)))

(defn missing-inputs?
  "Checks if the provided wire values are missing
  any inputs required for the given connection."
  [wires connection]
  (not-every? identity
              (map (partial contains? wires) (connection-inputs connection))))

(defn connect
  "Uses the provided wire values as input to the given connection
  and returns the new wire values.
  If any required values are missing, returns the wire values unmodified."
  [wires connection]
  (if (missing-inputs? wires connection) wires
      (let [gate ((first connection) gates)
            output ((comp first keys second) connection)
            inputs ((comp first vals second) connection)
            input-vals (map #(if (keyword? %) (% wires) %) inputs)]
        (assoc wires output (apply gate input-vals)))))

(defn solve-circuit
  "Given a seq of connections, returns the stable values of all wires."
  ([connections]
   (solve-circuit connections (reduce connect {} connections)))
  ([connections wires]
   (let [new-wires (reduce connect wires connections)]
     (if (= wires new-wires)
       wires
       (solve-circuit connections new-wires)))))

(defn solve
  "Given the input for the day, returns the solution."
  [input]
  (let [connections (map parse-connection (clojure.string/split-lines input))]
    [(:a (solve-circuit connections))]))
