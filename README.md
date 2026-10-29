# In-Lab Practical 6

This reconstruction follows the supplied screenshots.  It contains the two
lab classes and a local copy of the `cs2030s.fp` dependencies required to run
the solution without the course server.

Compile and test:

```sh
javac -Xlint:unchecked -Xlint:rawtypes cs2030s/fp/*.java *.java
java Lab6Test
```

The five `ParkingService` methods are written as a single functional pipeline,
matching the solution shown in the screenshots.  `ParkingSession.java` is the
provided class and is not intended to be modified.

The original `test.jar`, `submit.sh`, and `pristine/` contents are course-server
artifacts and were not present in the screenshots, so they are not fabricated
here.  `Lab6Test.java` covers the visible Question 1 and Question 2 examples.
