(ns adventofcode.day-14-test
  (:require [clojure.test :refer :all]
            [adventofcode.day-14 :refer :all]))

(deftest parse-reindeer-test
  (is (= {:comet {:speed 14 :fly 10 :rest 127}}
         (parse-reindeer
          "Comet can fly 14 km/s for 10 seconds,
          but then must rest for 127 seconds.")))
  (is (= {:dancer {:speed 16 :fly 11 :rest 162}}
         (parse-reindeer
          "Dancer can fly 16 km/s for 11 seconds,
          but then must rest for 162 seconds."))))

(deftest flying?-test
  (let [comet {:speed 14 :fly 10 :rest 127}
        vixen {:speed 16 :fly 11 :rest 162}]
    (are [x r t] (x (flying? r t))
      false? comet 0
      false? vixen 0
      true?  comet 1
      true?  vixen 1
      true?  comet 10
      true?  vixen 11
      false? comet 11
      false? vixen 12
      false? comet 127
      false? vixen 162
      false? comet 128
      false? vixen 163
      false? comet 137
      false? vixen 173
      true?  comet 138
      true?  vixen 174)))

(deftest distance-test
  (let [comet {:speed 14 :fly 10 :rest 127}
        vixen {:speed 16 :fly 11 :rest 162}]
    (are [x r t] (= x (distance r t))
      0    comet 0
      0    vixen 0
      14   comet 1
      16   vixen 1
      140  comet 10
      160  vixen 10
      140  comet 11
      176  vixen 11
      140  comet 137
      176  vixen 173
      1120 comet 1000
      1056 vixen 1000)))

(deftest points-test
  (let [reindeer {:comet {:speed 14 :fly 10 :rest 127}
                  :vixen {:speed 16 :fly 11 :rest 162}}]
    (are [x r t] (= x (r (points reindeer t)))
      0   :comet 0
      0   :vixen 0
      0   :comet 1
      1   :vixen 1
      1   :comet 140
      139 :vixen 140
      312 :comet 1000
      689 :vixen 1000)))
