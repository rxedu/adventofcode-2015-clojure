(ns adventofcode.day-11-test
  (:require [clojure.test :refer :all]
            [adventofcode.day-11 :refer :all]))

(deftest alph-test
  (is (= \a (first alph)))
  (is (= [\a \b \c] (take 3 alph)))
  (is (= \z (last alph)))
  (is (= 26 (count alph))))

(deftest alph-triples-test
  (is (= "abc" (first (alph-triples))))
  (is (= ["abc" "bcd" "cde"] (take 3 (alph-triples))))
  (is (= "xyz" (last (alph-triples)))))

(deftest valid-password?-test
  (is (false? (valid-password? "hijklmmn")))
  (is (false? (valid-password? "abbceffg")))
  (is (false? (valid-password? "abbcegjk")))
  (is (false? (valid-password? "ghijklmn")))
  (is (false? (valid-password? "abcdefgh")))
  (is (false? (valid-password? "ghijklmn")))
  (is (false? (valid-password? "affceffg")))
  (is (true?  (valid-password? "abcdffaa")))
  (is (true?  (valid-password? "ghjaabcc"))))

(deftest int->str-test
  (is (= "a"        (int->str 0)))
  (is (= "b"        (int->str 1)))
  (is (= "z"        (int->str 25)))
  (is (= "ba"       (int->str 26)))
  (is (= "zz"       (int->str 675)))
  (is (= "baa"      (int->str 676)))
  (is (= "sle"      (int->str 12458)))
  (is (= "lsii"     (int->str 205720)))
  (is (= "sjeughrm" (int->str 147409600818))))

(deftest str->int-test
  (is (= 0            (str->int "a")))
  (is (= 1            (str->int "b")))
  (is (= 25           (str->int "z")))
  (is (= 26           (str->int "ba")))
  (is (= 675          (str->int "zz")))
  (is (= 676          (str->int "baa")))
  (is (= 12458        (str->int "sle")))
  (is (= 205720       (str->int "lsii")))
  (is (= 147409600818 (str->int "sjeughrm"))))

(deftest inc-string-test
  (is (= "b"      (inc-string "a")))
  (is (= "z"      (inc-string "y")))
  (is (= "ba"     (inc-string "z")))
  (is (= "xy"     (inc-string "xx")))
  (is (= "ya"     (inc-string "xz")))
  (is (= "bjskek" (inc-string "bjskej")))
  (is (= "kdlsja" (inc-string "kdlsiz")))
  (is (= "sjeaa"  (inc-string "sjdzz")))
  (is (= "baaaa"  (inc-string "zzzz"))))

(deftest pad-string-test
  (is (= "a"          (pad-string 1 "a")))
  (is (= "b"          (pad-string 1 "b")))
  (is (= "aaa"        (pad-string 3 "a")))
  (is (= "aab"        (pad-string 3 "b")))
  (is (= "aaadusjdh"  (pad-string 9 "dusjdh")))
  (is (= "hytgf"      (pad-string 5 "hytgf"))))

(deftest inc-password-test
  (is (= "bbcdd"    (inc-password "bbcbb")))
  (is (= "abcdffaa" (inc-password "abcdfeaa")))
  (is (= "ghjaabcc" (inc-password "ghjaabbb"))))
