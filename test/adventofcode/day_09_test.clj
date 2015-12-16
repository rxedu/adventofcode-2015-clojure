(ns adventofcode.day-09-test
  (:require [clojure.test :refer :all]
            [adventofcode.day-09 :refer :all]))

(deftest parse-connection-test
  (is (= {#{:alpha :beta} 100}
         (parse-connection "Alpha to Beta = 100")))
  (is (= {#{:alpha :beta} 120}
         (parse-connection "Beta to Alpha = 120"))))

(deftest locations-test
  (is (= #{:a :b :c}
         (locations {#{:a :b} 10 #{:a :c} 12 #{:b :c} 15}))))

(deftest distance-test
  (is (= 22 (distance
             {#{:a :b} 2 #{:a :c} 7 #{:b :c} 9 #{:c :d} 11}
             [:a :b :c :d]))))

(deftest distances-test
  (is (= #{982 605 659}
         (distances {#{:a :b} 464 #{:a :c} 518 #{:b :c} 141}))))
