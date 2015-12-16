(defproject adventofcode "0.1.0"
  :description "Solutions to the Advent of Code puzzles."
  :url "https://github.com/rxedu/adventofcode"
  :license {:name "MIT"
            :url "https://opensource.org/licenses/MIT"}
  :dependencies [[org.clojure/clojure "1.7.0"]
                 [org.clojure/math.combinatorics "0.1.1"]
                 [digest "1.4.4"]]
  :main ^:skip-aot adventofcode.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
