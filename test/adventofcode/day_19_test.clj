(ns adventofcode.day-19-test
  (:require [clojure.test :refer :all]
            [adventofcode.day-19 :refer :all]))

(deftest parse-replacements-test
  (is (= ["H" "HO"]   (parse-replacements "H => HO")))
  (is (= ["Al" "ThF"] (parse-replacements "Al => ThF"))))

(deftest replace-nth-test
  (is (= "HOH"  (replace-nth "HOH" "S" "H" 0)))
  (is (= "HOH"  (replace-nth "HOH" "H" "H" 0)))
  (is (= "HOH"  (replace-nth "HOH" "O" "O" 0)))
  (is (= "HOH"  (replace-nth "HOH" "H" "H" 1)))
  (is (= "HOOH" (replace-nth "HOH" "H" "HO" 0)))
  (is (= "HOHO" (replace-nth "HOH" "H" "HO" 1)))
  (is (= "OHOH" (replace-nth "HOH" "H" "OH" 0)))
  (is (= "HOOH" (replace-nth "HOH" "H" "OH" 1)))
  (is (= "HHHH" (replace-nth "HOH" "O" "HH" 0)))
  (is (= "HHHHOHO" (replace-nth "HOHOHO" "O" "HH" 0)))
  (is (= "HOHHHHO" (replace-nth "HOHOHO" "O" "HH" 1)))
  (is (= "HOOHTOHTHHOH" (replace-nth "HOHTOHTHHOH" "H" "HO" 0)))
  (is (= "HOHTOHTHOHOH" (replace-nth "HOHTOHTHHOH" "H" "HO" 3)))
  (is (= "HOHTOHTHHOHO" (replace-nth "HOHTOHTHHOH" "H" "HO" 5))))

(deftest distinct-replacements-test
  (is (= #{"HOOH" "HOHO"}
         (distinct-replacements "HOH" "H" "HO")))
  (is (= #{"OHOH" "HOOH"}
         (distinct-replacements "HOH" "H" "OH")))
  (is (= #{"HHHH"}
         (distinct-replacements "HOH" "O" "HH")))
  (is (= #{"HOOHOHO" "HOHOOHO" "HOHOHOO"}
         (distinct-replacements "HOHOHO" "H" "HO")))
  (is (= #{"OHOHOHO" "HOOHOHO" "HOHOOHO"}
         (distinct-replacements "HOHOHO" "H" "OH")))
  (is (= #{"HHHHOHO" "HOHHHHO" "HOHOHHH"}
         (distinct-replacements "HOHOHO" "O" "HH"))))

(deftest fabrication-steps-test
  (let [replacements
        [["e" "H"] ["e" "O"] ["H" "HO"] ["H" "OH"] ["O" "HH"] ["P" "EEA"]]]
    (is (= 3 (fabrication-steps replacements "HOH")))
    (is (= 6 (fabrication-steps replacements "HOHOHO")))))
