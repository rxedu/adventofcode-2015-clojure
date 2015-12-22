(ns adventofcode.day-21)

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

(defn solve
  "Given the input for the day, returns the solution."
  [input]
  [])
