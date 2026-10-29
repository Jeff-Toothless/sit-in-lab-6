# In-Lab Practical 6

## Preliminary

The practical uses the following files:

- `task1.md`: question file
- `ParkingSession.java`: provided, do not modify
- `ParkingService.java`: starter file; edit this file only

Java files marked “do not modify” are replaced with the original version during
course testing.

## Using `InfiniteList`

An `InfiniteList` represents a sequence of elements that can be processed using
operations such as filtering, mapping, and reducing.

```java
InfiniteList.fromList(list);
InfiniteList.of(elem1, elem2, elem3);
```

Common methods used in this practical are:

| Method | Description |
| --- | --- |
| `filter(pred)` | Keep elements satisfying a condition |
| `map(func)` | Transform each element |
| `count()` | Count the elements |
| `reduce(id, acc)` | Combine the elements into one result |
| `toList()` | Collect the elements into a list |

Useful `Maybe<T>` methods include `Maybe.of`, `filter`, `map`, `flatMap`,
`ifPresent`, and `orElse`.

## Functional style

Where functional style is required, the answer consists of one statement in
the method body.  Do not introduce helper methods, extra classes, conditional
or ternary expressions, or blocks inside lambda expressions.

Compile with:

```sh
javac -Xlint:unchecked -Xlint:rawtypes cs2030s/fp/*.java *.java
```

## Description

This practical focuses on functional programming with `InfiniteList<T>`.
`ParkingSession` records a vehicle plate, parking spot, and duration in minutes.
`ParkingService` stores the sessions in chronological order.  Only
`ParkingService.java` is intended to be modified for the exercise.

## Question 1

Convert these methods to functional style:

- `ParkingService::sessionsOf`
- `ParkingService::spotsVisited`
- `ParkingService::totalDuration`

Requirements:

- `sessionsOf(String plate)` returns the sessions for the plate in their
  original order.
- `spotsVisited(String plate)` returns the visited spots in their original
  order.
- `totalDuration(String plate)` returns the total duration in minutes.
- Each method body must be in functional style.

Example data:

```java
List<ParkingSession> sessions = List.of(
    new ParkingSession("SGX 1234", "A01", 60),
    new ParkingSession("XYZ 5678", "B02", 30),
    new ParkingSession("SGX 1234", "A03", 90),
    new ParkingSession("SGX 1234", "A01", 45),
    new ParkingSession("TUA 9999", "B02", 20)
);
ParkingService p = new ParkingService(sessions);
```

Expected results include:

```text
p.spotsVisited("SGX 1234")  -> [A01, A03, A01]
p.spotsVisited("XYZ 5678")  -> [B02]
p.spotsVisited("ZZZ 0000")  -> []
p.totalDuration("SGX 1234") -> 195
p.totalDuration("ZZZ 0000") -> 0
```

## Question 2

Continue editing `ParkingService.java` and implement:

- `ParkingService::countSpot`
- `ParkingService::mostVisitedSpot`

Requirements:

- `countSpot(String plate, String spot)` returns the number of occurrences of
  `spot` in that plate’s sessions.
- `mostVisitedSpot(String plate)` returns the most frequent spot.
- On a tie, return the spot occurring first in the plate’s original session
  order.
- If the plate has no sessions, return `""`.
- Functional style is required.

For the sample in `Lab6Test.java`, expected results include:

```text
p.countSpot("SGX 1234", "A01")    -> 3
p.countSpot("SGX 1234", "A03")    -> 2
p.countSpot("XYZ 5678", "B02")    -> 2
p.mostVisitedSpot("SGX 1234")      -> "A01"
p.mostVisitedSpot("XYZ 5678")      -> "B02"
p.mostVisitedSpot("ZZZ 0000")      -> ""
```
