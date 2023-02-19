package org.example.ranking.result;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class GameOutcomesTest {

  @Test
  void calculateTestDraw() {
    var outcome = GameOutcomes.calculate(5, 5);
    assertEquals(GameOutcomes.DRAW, outcome);
    assertEquals(1, outcome.getAwayPoints());
    assertEquals(1, outcome.getHomePoints());
  }

  @Test
  void calculateTestHomeVictory() {
    var outcome = GameOutcomes.calculate(4, 2);
    assertEquals(GameOutcomes.HOME_VICTORY, outcome);
    assertEquals(0, outcome.getAwayPoints());
    assertEquals(3, outcome.getHomePoints());
  }


  @Test
  void calculateTestAwayVictory() {
    var outcome = GameOutcomes.calculate(0, 1);
    assertEquals(GameOutcomes.AWAY_VICTORY, outcome);
    assertEquals(3, outcome.getAwayPoints());
    assertEquals(0, outcome.getHomePoints());
  }
}