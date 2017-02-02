# diamond kata clojure

Diamond Kata using tdd guided by property based tests.

Inspired by Nat Pryce [Diamond Kata - TDD with only Property-Based Tests](http://natpryce.com/articles/000807.html) article.

## Running the tests

```lein test-refresh```

## Main methods

```(diamond.core [ch])``` produces a diamond

```(diamond.io [diamond]``` prints a diamond

E.g. this is the diamond generated for \H
```
-------A-------
------B-B------
-----C---C-----
----D-----D----
---E-------E---
--F---------F--
-G-----------G-
H-------------H
-G-----------G-
--F---------F--
---E-------E---
----D-----D----
-----C---C-----
------B-B------
-------A-------
```