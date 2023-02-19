package org.example.ranking.result;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class GameResultTest {

  @Test
  void fromTestValidTrailingNumbersInTeamName() {
    var gameResult = GameResult.from("Mambo 1 2 3 3, the jackson's 5  4");
    assertNotNull(gameResult);
    var awayTeamScore = gameResult.getAwayTeamScore();
    var homeTeamScore = gameResult.getHomeTeamScore();
    assertNotNull(awayTeamScore);
    assertNotNull(homeTeamScore);
    assertGameTeamScore(awayTeamScore, "the jackson's 5", 4, 3);
    assertGameTeamScore(homeTeamScore,"Mambo 1 2 3", 3, 0);
    System.out.println(gameResult);
  }

  @Test
  void fromTestValidStartingNumbersInTeamName() {
    var gameResult = GameResult.from("3 Pigs 0, 11 Monkeys 2");
    assertNotNull(gameResult);
    var awayTeamScore = gameResult.getAwayTeamScore();
    var homeTeamScore = gameResult.getHomeTeamScore();
    assertNotNull(awayTeamScore);
    assertNotNull(homeTeamScore);
    assertGameTeamScore(homeTeamScore,"3 Pigs", 0, 0);
    assertGameTeamScore(awayTeamScore, "11 Monkeys", 2, 3);
    System.out.println(gameResult);
  }

  @Test
  void fromTestValidExtraSpacesInTeamName() {
    var gameResult = GameResult.from("   The Rats   3   ,  The Mice    3  ");
    assertNotNull(gameResult);
    var awayTeamScore = gameResult.getAwayTeamScore();
    var homeTeamScore = gameResult.getHomeTeamScore();
    assertNotNull(awayTeamScore);
    assertNotNull(homeTeamScore);
    assertGameTeamScore(homeTeamScore,"The Rats", 3, 1);
    assertGameTeamScore(awayTeamScore, "The Mice", 3, 1);
    System.out.println(gameResult);
  }

  void assertGameTeamScore(GameTeamScore teamScore, String teamName, int goals, int points) {
    assertEquals(teamName, teamScore.team());
    assertEquals(goals, teamScore.goals());
    assertEquals(points, teamScore.points());
  }

  @Test
  void fromTestInvalidPatternNotMatching() {
    String expectedErrorMsg = "game result not matching expected pattern";
    var exception = assertThrows(GameResultFormatException.class,
        () -> GameResult.from("3 Pigs 0a, 11 Monkeys 2"));
    assertEquals(expectedErrorMsg, exception.getMessage());
    exception = assertThrows(GameResultFormatException.class,
        () -> GameResult.from("3 Pigs 0, 11 Monkeys"));
    assertEquals(expectedErrorMsg, exception.getMessage());
    exception = assertThrows(GameResultFormatException.class,
        () -> GameResult.from("3 Pigs, 11 Monkeys 1"));
    assertEquals(expectedErrorMsg, exception.getMessage());
    exception = assertThrows(GameResultFormatException.class,
        () -> GameResult.from("3 Pigs -1, 11 Monkeys -5"));
    assertEquals(expectedErrorMsg, exception.getMessage());
    exception = assertThrows(GameResultFormatException.class,
        () -> GameResult.from(""));
    assertEquals(expectedErrorMsg, exception.getMessage());
    exception = assertThrows(GameResultFormatException.class,
        () -> GameResult.from("3 Pigs -1,    5"));
    assertEquals(expectedErrorMsg, exception.getMessage());
  }

  @Test
  void fromTestInvalidSameTeamName() {
    String expectedErrorMsg = "away team and home team cannot have the same name";
    var exception = assertThrows(GameResultFormatException.class,
        () -> GameResult.from("Pigs 0, Pigs 2"));
    assertEquals(expectedErrorMsg, exception.getMessage());
  }

  @Test
  void fromTestInvalidNullValue() {
    String expectedErrorMsg = "game result cannot be null";
    var exception = assertThrows(NullPointerException.class,
        () -> GameResult.from(null));
    assertEquals(expectedErrorMsg, exception.getMessage());
  }

  @Test
  void ofTestInvalidNullHomeTeam() {
    String expectedErrorMsg = "home team cannot be null";
    var exception = assertThrows(NullPointerException.class,
        () -> GameResult.of(null, 1, "test", 2));
    assertEquals(expectedErrorMsg, exception.getMessage());
  }

  @Test
  void ofTestInvalidNullAwayTeam() {
    String expectedErrorMsg = "away team cannot be null";
    var exception = assertThrows(NullPointerException.class,
        () -> GameResult.of("stars", 1, null, 2));
    assertEquals(expectedErrorMsg, exception.getMessage());
  }

  @Test
  void ofTestInvalidEmptyHomeTeam() {
    String expectedErrorMsg = "home team cannot be blank";
    var exception = assertThrows(GameResultFormatException.class,
        () -> GameResult.of("  ", 1, "test", 2));
    assertEquals(expectedErrorMsg, exception.getMessage());
  }

  @Test
  void ofTestInvalidEmptyAwayTeam() {
    String expectedErrorMsg = "away team cannot be blank";
    var exception = assertThrows(GameResultFormatException.class,
        () -> GameResult.of("the doors", 1, "", 2));
    assertEquals(expectedErrorMsg, exception.getMessage());
  }

  @Test
  void ofTestInvalidNegativeAwayTeamGoals() {
    String expectedErrorMsg = "away team goals cannot be negative";
    var exception = assertThrows(GameResultFormatException.class,
        () -> GameResult.of("spikes", 1, "turtles", -1));
    assertEquals(expectedErrorMsg, exception.getMessage());
  }

  @Test
  void ofTestInvalidNegativeHomeTeamGoals() {
    String expectedErrorMsg = "home team goals cannot be negative";
    var exception = assertThrows(GameResultFormatException.class,
        () -> GameResult.of("spikes", -2, "turtles", 0));
    assertEquals(expectedErrorMsg, exception.getMessage());
  }


  @Test
  void ofTestInvalidSameTeamNames() {
    String expectedErrorMsg = "away team and home team cannot have the same name";
    var exception = assertThrows(GameResultFormatException.class,
        () -> GameResult.of("turtles", 2, "turtles", 0));
    assertEquals(expectedErrorMsg, exception.getMessage());
  }
}