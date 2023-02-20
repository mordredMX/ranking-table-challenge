package org.example.ranking.result;

/**
 * @author obarenque
 * the state of a team in a game
 * @param team name of the team
 * @param goals scored goals
 * @param points the points assigned to the team as result of the game
 */
public record GameTeamScore(String team, int goals, int points) {

  @Override
  public String toString() {
    return String.format("team [ %s ] goals [ %d ] points [ %d ]",
        team, goals, points);
  }
}