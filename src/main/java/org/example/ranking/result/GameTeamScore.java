package org.example.ranking.result;

/**
 * the state of a team in a game
 * @param team name of the team
 * @param goals scored goals
 * @param points the points that were assigned in the game
 */
public record GameTeamScore(String team, int goals, int points) {

  @Override
  public String toString() {
    return String.format("team [ %s ] goals [ %d ] points [ %d ]",
        team, goals, points);
  }
}