package org.example.ranking.result;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author obarenque
 * a game result representation which is form by two teams: home team and away team
 * plus the goals each team scored, internally it calculates the points that must be
 *  assigned to the team based on the outcome of the game.
 */
public class GameResult {

  private final GameTeamScore homeTeamScore;

  private final GameTeamScore awayTeamScore;

  private static final Pattern GAME_RESULT_REGEX = Pattern
      .compile("^\\s*(.*)\\s+(\\d+)\\s*,\\s+(.*)\\s+(\\d+)\\s*$");

  /**
   * get MatchResult from String that must be in format
   * "{home team} {goals home team}, {away team} {goals away team}".
   * @param value String value
   * @return MatchResult
   */
  public static GameResult from(String value) throws GameResultFormatException {
    Objects.requireNonNull(value, "game result cannot be null");
    final Matcher matcher = GAME_RESULT_REGEX.matcher(value);
    if (matcher.matches()) {
      String rightTeamName = matcher.group(1);
      int rightTeamGoals = Integer.parseInt(matcher.group(2));
      String leftTeamName = matcher.group(3);
      int leftTeamGoals = Integer.parseInt(matcher.group(4));
      return
          of(rightTeamName, rightTeamGoals,
              leftTeamName, leftTeamGoals);
    }
    throw new GameResultFormatException("game result not matching expected pattern");
  }

  /**
   * creates MatchResult instance of parameters related to
   * team names and goals each team scored .
   * @param homeTeam right team name
   * @param homeTeamGoals right team points
   * @param awayTeam left team name
   * @param awayTeamGoals left team points
   * @return MatchResult
   */
  public static GameResult of(String homeTeam, int homeTeamGoals,
      String awayTeam, int awayTeamGoals) throws GameResultFormatException {
    Objects.requireNonNull(homeTeam, "home team cannot be null");
    Objects.requireNonNull(awayTeam, "away team cannot be null");
    if (homeTeam.isBlank()) {
      throw new GameResultFormatException("home team cannot be blank");
    }
    if (awayTeam.isBlank()) {
      throw new GameResultFormatException("away team cannot be blank");
    }
    awayTeam = awayTeam.trim();
    homeTeam = homeTeam.trim();
    if (awayTeam.equals(homeTeam)) {
      throw new GameResultFormatException("away team and home team cannot have the same name");
    }
    if (homeTeamGoals < 0) {
      throw new GameResultFormatException("home team goals cannot be negative");
    }
    if (awayTeamGoals < 0) {
      throw new GameResultFormatException("away team goals cannot be negative");
    }
    return new GameResult(homeTeam.trim(), homeTeamGoals, awayTeam.trim(), awayTeamGoals);
  }

  private GameResult(String homeTeam, int homeGoals,
      String awayTeam, int awayGoals) {
    var outcome = GameOutcomes.calculate(homeGoals, awayGoals);
    homeTeamScore = new GameTeamScore(homeTeam, homeGoals, outcome.getHomePoints());
    awayTeamScore = new GameTeamScore(awayTeam, awayGoals, outcome.getAwayPoints());
  }

  @Override
  public String toString() {
    return String.format("home [ %s ], away: [ %s ]",
        homeTeamScore, awayTeamScore);
  }

  /**
   * get away team score.
   * @return TeamScore
   */
  public GameTeamScore getAwayTeamScore() {
    return awayTeamScore;
  }

  /**
   * get home team score.
   * @return TeamScore
   */
  public GameTeamScore getHomeTeamScore() {
    return homeTeamScore;
  }

}
