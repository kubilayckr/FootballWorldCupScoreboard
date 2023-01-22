# Live Football World Cup Scoreboard

This repository contains working code for a new Live Football World Cup Scoreboard that displays matches and scores.

## Fundamental Requirements

* Start a game. When a game starts, it should capture. (being initial score 0-0)
  * a. Home team
  * b. Away Team
* Finish a game. It will remove a match from the scoreboard.
* Update score. Receiving the pair score; home team score and away team score updates a game score.
* Get a summary of games by total score. Those games with the same total score will be returned ordered by the most recently added to our system.

## Prerequisites

* Have a JDK installed (JDK 17)
* Have Maven installed and available on your PATH or IDE

##  Source Code Review

In this project, TreeMap was used as in-memory data storage solution. The main reasons for this;

* Lets you define a custom sort order. (The match start time is used to indicate the order of adding to the system.)
* The retrieval speed of an element out of a TreeMap is fast, even in a TreeMap with a large number of elements.

```java
private static final SortedMap<ScoreboardIndex, Match> TABLE_SCOREBOARD = new TreeMap<>(new ScoreboardCustomComparator());
private static final Map<UUID, Match> TABLE_MATCH = new TreeMap<>();
```

We have two singleton TreeMaps. TABLE_SCOREBOARD map stores the live matches, while TABLE_MATCH map stores all matches.


1. Add new match to the system.
```java
UUID addMatch(AddMatchRequest addMatchRequest);
```
This method is used when we want to add a new match to the system. It adds a record to the Match table with home team name, away team name and the match date.

2. Start match.
```java
MatchDTO startMatch(UUID id);
```
The match starts with a 0-0 initial score. The match is added to the Scoreboard table.

3. Finish match.
```java
void finishMatch(FinishMatchRequest request);
```
Finishes the match. Updates the record in the Match table with the latest scores and deletes the record from the scoreboard table.

4. Updates team scores.
```java
MatchDTO updateScore(UpdateScoreRequest request);
```
Updates team scores.

5. Updates match status.
```java
MatchDTO updateMatchStatus(UpdateMatchStatusRequest request);
```
Updates the match status with statuses such as first half, finished or cancelled...

6. Retrieves all match records.
```java
List<MatchDTO> getAllMatches();
```

7. Retrieves live match records (Retrieves scoreboard).
```java
List<MatchDTO> getScoreboard();
```