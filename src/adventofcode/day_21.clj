(ns adventofcode.day-21
  (:require [clojure.math.combinatorics]))

(def shop
  {:weapons
   {:dagger     {:cost 8  :damage 4 :armor 0}
    :shortsword {:cost 10 :damage 5 :armor 0}
    :warhammer  {:cost 25 :damage 6 :armor 0}
    :longsword  {:cost 40 :damage 7 :armor 0}
    :greataxe   {:cost 74 :damage 8 :armor 0}}
   :armor
   {:leather    {:cost 13  :damage 0 :armor 1}
    :chainmail  {:cost 31  :damage 0 :armor 2}
    :splintmail {:cost 53  :damage 0 :armor 3}
    :bandedmail {:cost 75  :damage 0 :armor 4}
    :platemail  {:cost 102 :damage 0 :armor 5}}
   :rings
   {:damage+1   {:cost 25  :damage 1 :armor 0}
    :damage+2   {:cost 50  :damage 2 :armor 0}
    :damage+3   {:cost 100 :damage 3 :armor 0}
    :defense+1  {:cost 20  :damage 0 :armor 1}
    :defense+2  {:cost 40  :damage 0 :armor 2}
    :defense+3  {:cost 80  :damage 0 :armor 3}}})

(def equipment
  {:weapons (range 1 2) :armor (range 2) :rings (range 3)})

(def characters
  (let [sel-fn #(clojure.math.combinatorics/combinations (vals (%1 shop)) %2)
        buys (map #(map (partial sel-fn %) (% equipment)) (keys equipment))
        char-fn #(apply (partial merge-with +
                                 {:cost 0 :damage 0 :armor 0}) %)
        flat-chars
        (map (comp flatten (partial map (comp flatten (partial map char-fn))))
             buys)
        single-chars
        (apply clojure.math.combinatorics/cartesian-product flat-chars)]
    (distinct (map (partial apply (partial merge-with + {:hitpoints 100}))
                   single-chars))))

(defn parse-boss
  "Converts boss stats from a string to a map."
  [string]
  (let [stats (clojure.string/split-lines string)
        matches (map #((comp rest first) (re-seq #"(\D+): (\d+)" %)) stats)
        fmt-fn
        (comp clojure.string/lower-case #(clojure.string/replace % #" " ""))]
    (into {}
          (map
           #(vector ((comp keyword fmt-fn) (first %)) (Integer. (second %)))
           matches))))

(defn win?
  "Returns true if you beat the boss, else false.
  Expects a hash of characters with keys :you and :boss."
  [{:keys [you boss]}]
  (let [rounds #(int (Math/ceil
                      (/ (:hitpoints %1)
                         (max 1 (- (:damage %2) (:armor %1))))))]
    (<= (rounds boss you) (rounds you boss))))

(defn solve
  "Given the input for the day, returns the solution."
  [input]
  (let [boss (parse-boss input)
        winners
        (fn [winner?]
          (remove nil? (map
                        #(if (winner? (win? {:you %1 :boss %2})) %1 nil)
                        characters (repeat boss))))]
    [(apply min (map :cost (winners true?)))
     (apply max (map :cost (winners false?)))]))
