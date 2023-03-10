package org.example.ranking.result;

/**
 * @author obarenque
 * possible outcomes of a game and the points assigned to home and away teams.
 */
enum GameOutcomes {
  HOME_VICTORY(3, 0),
  AWAY_VICTORY(0, 3),
  DRAW(1, 1);
  private final int homePoints;

  private final int awayPoints;
  GameOutcomes(int homePoints, int awayPoints){
    this.homePoints = homePoints;
    this.awayPoints = awayPoints;
  }

  /**
   * calculate the game outcome based on the goals scored by each team
   * @param homeGoals goals scored by home team
   * @param awayGoals goals scored by away team
   * @return GameOutcomes
   */
  static GameOutcomes calculate(int homeGoals, int awayGoals) {
    if (homeGoals == awayGoals) {
      return DRAW;
    }
    return  homeGoals > awayGoals ? HOME_VICTORY : AWAY_VICTORY;
  }

  /**
   * points assigned to home team based on the outcome of the game
   * @return home points
   */
  int getHomePoints () {
    return homePoints;
  }

  /**
   * points assigned to away team based on the outcome of the game
   * @return home points
   */
  int getAwayPoints () {
    return awayPoints;
  }
}