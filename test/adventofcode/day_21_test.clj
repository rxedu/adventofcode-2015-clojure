(ns adventofcode.day-21-test
  (:require [clojure.test :refer :all]
            [adventofcode.day-21 :refer :all]))

(deftest parse-boss-test
  (is (= {:hitpoints 120 :damage 5 :armor 2}
         (parse-boss "Hit Points: 120\nDamage: 5\nArmor: 2"))))

(deftest win?-test
  (is (true? (win? {:you  {:hitpoints 8  :damage 5 :armor 5}
                    :boss {:hitpoints 12 :damage 7 :armor 2}})))
  (is (true? (win? {:you  {:hitpoints 8  :damage 5 :armor 5}
                    :boss {:hitpoints 12 :damage 7 :armor 2}}))))
