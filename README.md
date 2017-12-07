# Advent of Code 2015 Solutions (in Clojure)

[![GitHub release](https://img.shields.io/github/release/rxedu/adventofcode-2015.svg)](https://github.com/rxedu/adventofcode-2015/releases)
[![GitHub license](https://img.shields.io/github/license/rxedu/adventofcode-2015.svg)](./LICENSE.txt)
[![Travis](https://img.shields.io/travis/rxedu/adventofcode-2015.svg)](https://travis-ci.org/rxedu/adventofcode-2015)

My solutions to the [Advent of Code 2015] puzzles.

## Write your own!

Solutions will be posted as I write them,
but you can use this project to make your own.

Simply clone or fork this, then create a new branch from
the `new-solutions` tag with

```
$ git branch my-solutions new-solutions
$ git checkout my-solutions
```

or reset your master branch to the `new-solutions` tag with

```
$ git reset --hard new-solutions
```

Save your input to `input/01.txt`, `input/02.txt`, etc.
Then, write your solution and tests for each day
in the corresponding `.clj` files.
See [Development](#development) below for more.

[Advent of Code 2015]: http://adventofcode.com/2015

## Usage

If you just want to generate solutions you will need Java.

1. Download `adventofcode-1.0.0-standalone.jar`
   from the latest [release].
2. In the same folder, save your input to an `input` directory
   (just like the `input` directory included in the project).
3. Generate the solutions to `solutions` with

   ```
   $ java -jar adventofcode-1.0.0-standalone.jar
   ```

   Or compute the solution for a particular day,
   e.g., day 3, with

   ```
   $ java -jar adventofcode-1.0.0-standalone.jar 3
   ```

[release]: https://github.com/rxedu/adventofcode-2015/releases

## Development

### Build Environment

Leiningen 2.5.3 on Java 1.8.0_66 OpenJDK 64-Bit Server VM.

### Leiningen

Generate, display, and save all solutions with

```
$ lein run
```

Compute the solutions for a single day, e.g., day 1, with

```
$ lein run 1
```

or in the REPL (`$ lein repl`) with

```clojure
(solve-day 1)
```

## License

This software is licensed under the MIT license.

## Warranty

This work is provided "as is" and without any express or
implied warranties, including, without limitation, the implied
warranties of merchantibility and fitness for a particular
purpose.
