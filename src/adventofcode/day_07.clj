(ns adventofcode.day-07)

(def matchers
  {:sig #"(\w+) -> (\w+)"
   :not #"NOT (\w+) -> (\w+)"
   :and #"(\w+) AND (\w+) -> (\w+)"
   :or  #"(\w+) OR (\w+) -> (\w+)"
   :lsh #"(\w+) LSHIFT (\w+) -> (\w+)"
   :rsh #"(\w+) RSHIFT (\w+) -> (\w+)"})

(def elements
  (let [convert
        #(if (re-find #"\d+" %) (Integer. %) (keyword %))
        single-input
        #(hash-map (keyword %2) (vector (convert %1)))
        double-input
        #(hash-map (keyword %3) (vector (convert %1) (convert %2)))]
    {:sig single-input
     :not single-input
     :and double-input
     :or  double-input
     :lsh double-input
     :rsh double-input}))

(def gates
  {:sig identity
   :not bit-not
   :and bit-and
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

(defn override-wire
  "Removes any inputs to the given wire from connections
  and adds a connection for that wire with the specified input."
  [connections wire input]
  (cons [:sig (assoc {} wire [input])]
        (remove (comp some? wire second) connections)))

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
  (let [connections (map parse-connection (clojure.string/split-lines input))
        a-signal (:a (solve-circuit connections))]
    [a-signal
     (:a (solve-circuit (override-wire connections :b a-signal)))]))
