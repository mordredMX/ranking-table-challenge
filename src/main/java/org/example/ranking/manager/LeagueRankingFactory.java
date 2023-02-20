package org.example.ranking.manager;

import org.example.ranking.table.ScoresTreeRankingTable;

/**
 * @author obarenque
 * factory class to instantiate the objects related to the
 * application business logic.
 */
public class LeagueRankingFactory {

  /**
   * initialize a leagueManager and dependencies, could inject different
   * ranking table implementations in the furure
   * @return LeagueRankManager
   */
  public LeagueRankingManager createManager() {
    return new LeagueRankingManager(new ScoresTreeRankingTable());
  }

}
