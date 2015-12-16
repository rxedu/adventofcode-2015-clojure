(ns adventofcode.day-08-test
  (:require [clojure.test :refer :all]
            [adventofcode.day-08 :refer :all]))

(deftest matchers-test
  (is (= "\\\""  (re-find (:quote matchers) "abc\\\"def")))
  (is (= "\\\\"  (re-find (:slash matchers) "abc\\\\def")))
  (is (= "\\x89" (re-find (:xcode matchers) "abc\\x89def")))
  (is (= "\\x7b" (re-find (:xcode matchers) "abc\\x7bdef")))
  (is (= "\\xaa" (re-find (:xcode matchers) "abc\\xaadef")))
  (is (= "\\xd6" (re-find (:xcode matchers) "abc\\xd6def"))))

(deftest in-memory-characters-test
  (is (= 0  (in-memory-characters "\"\"")))
  (is (= 3  (in-memory-characters "\"abc\"")))
  (is (= 7  (in-memory-characters "\"aaa\\\"aaa\"")))
  (is (= 1  (in-memory-characters "\"\\x27\"")))
  (is (= 1  (in-memory-characters "\"\\\\\"")))
  (is (= 5  (in-memory-characters "\"\\\"\\\\z9\\x27\"")))
  (is (= 30 (in-memory-characters
             "\"\\\"vhzlbwq\\\"xeimjt\\\\xe\\x85umho\\\"m\\\"\\\"bmy\""))))

(deftest encoded-characters-test
  (is (= 6  (encoded-characters "\"\"")))
  (is (= 9  (encoded-characters "\"abc\"")))
  (is (= 16 (encoded-characters "\"aaa\\\"aaa\"")))
  (is (= 11 (encoded-characters "\"\\x27\""))))
