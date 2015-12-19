(ns adventofcode.day-13-test
  (:require [clojure.test :refer :all]
            [adventofcode.day-13 :refer :all]))

(deftest parse-relation-test
  (is (= {{:alice :bob} 42}
         (parse-relation
          "Alice would gain 42 happiness units by sitting next to Bob.")))
  (is (= {{:bob :alice} 50}
         (parse-relation
          "Bob would gain 50 happiness units by sitting next to Alice.")))
  (is (= {{:bob :alice} -20}
         (parse-relation
          "Bob would loose 20 happiness units by sitting next to Alice."))))

(deftest guests-test
  (is (= #{:a :b :c}
         (guests
          {{:a :b} 1 {:a :c} -2 {:b :c} 3 {:b :a} 3 {:c :a} 1 {:c :b} 4}))))

(deftest happiness-test
  (is (= -18 (happiness
              {{:a :b} 10 {:a :c} -20 {:b :c} 32 {:b :a} -44 {:c :a} 8 {:c :b} -4}
              [:b :a :c]))))

(deftest potential-happiness-test
  (is (= #{2}
         (potential-happiness
          {{:a :b} 1 {:a :c} -2 {:b :c} 3 {:b :a} 3 {:c :a} 1 {:c :b} -4}))))

(deftest self-relations-test
  (is (= {{:self :a} 0 {:self :b} 0 {:a :self} 0 {:b :self} 0}
         (self-relations [:a :b]))))

(deftest solve-test
  (let [input
        "Alice would gain 54 happiness units by sitting next to Bob.
Alice would lose 79 happiness units by sitting next to Carol.
Alice would lose 2 happiness units by sitting next to David.
Bob would gain 83 happiness units by sitting next to Alice.
Bob would lose 7 happiness units by sitting next to Carol.
Bob would lose 63 happiness units by sitting next to David.
Carol would lose 62 happiness units by sitting next to Alice.
Carol would gain 60 happiness units by sitting next to Bob.
Carol would gain 55 happiness units by sitting next to David.
David would gain 46 happiness units by sitting next to Alice.
David would lose 7 happiness units by sitting next to Bob.
David would gain 41 happiness units by sitting next to Carol."]
    (testing "part 1"
      (is (= 330 (first (solve input)))))))
