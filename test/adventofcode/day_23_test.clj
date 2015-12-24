(ns adventofcode.day-23-test
  (:require [clojure.test :refer :all]
            [adventofcode.day-23 :refer :all]))

(deftest parse-instruction-test
  (are [ins s] (= ins (parse-instruction s))
    {:hlf [:a]}    "hlf a"
    {:tpl [:b]}    "tpl b"
    {:inc [:a]}    "inc a"
    {:jmp [4]}     "jmp +4"
    {:jmp [-10]}   "jmp -10"
    {:jie [:a  2]} "jie a, +2"
    {:jio [:b -4]} "jio b, -4"))

(deftest run-program-test
  (is (= {:idx 2 :a 1 :b 1}
         (run-program [{:inc [:a]}
                       {:inc [:b]}])))
  (is (= {:idx 7 :a 2 :b 3}
         (run-program [{:inc [:a]}
                       {:inc [:b]}
                       {:jie [:b 2]}
                       {:jmp [-3]}
                       {:tpl [:b]}
                       {:hlf [:b]}
                       {:jio [:a -2]}]))))
