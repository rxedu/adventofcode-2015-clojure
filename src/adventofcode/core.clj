(ns adventofcode.core
  (:require [clojure.java.io :as io])
  (:gen-class))

(def days 25)
(def input-path "input")
(def output-path "solutions")
(def pday #(format "%02d" %))

(defn read-input
  "Reads the input file for a given day.
  Not a pure function."
  [day]
  (slurp (io/file input-path (str (pday day) ".txt"))))

(defn save-solution
  "Saves the solution for a given day and then returns it.
  Not a pure function."
  [day solution]
  (let [path (io/file output-path (str (pday day) ".txt"))]
    (io/make-parents path)
    (spit path solution))
  solution)

(defn solve
  "Returns the solution for the given day."
  [day input]
  (let [day-ns (str "adventofcode.day-" (pday day))]
    (require [(symbol day-ns)])
    ((resolve (symbol (str day-ns "/solve"))) input)))

(defn print-solution
  "Prints the solution for a given day and then returns it.
  Only side effect is to print solution."
  [day solution]
  (println (str "Day " (pday day) " => " solution))
  solution)

(defn solve-day
  "Gets, prints, and then returns the solution for the given day."
  [day]
  (print-solution day (solve day (read-input day))))

(defn solve-all-days
  "Reads the input, generates, saves, and prints solutions for all days.
  Skips any days without an input file.
  May optionally specify the input and output path"
  ([] (solve-all-days input-path output-path))
  ([input-path output-path]
   (for [day (range 1 (inc days))]
     (let [pipeline (reverse [read-input solve print-solution save-solution])
           operations (mapv #(partial % day) (drop-last pipeline))]
       (try (let [input ((last pipeline) day)]
              ((apply comp operations) input))
            (catch java.io.FileNotFoundException e
              (println (str  "Day " (pday day) " => (no input)"))))))))

(defn -main
  "If no arguments are given: runs solve-all-days.
  If one argument given: run solve-day for that day only.
  If more than one argument given: run solve-all-days with those arguments.
  Note that only solve-all-days saves solutions to a file."
  [& args]
  (if (= 1 (count args))
    (solve-day (Integer. (first args)))
    (dorun (apply solve-all-days args))))
