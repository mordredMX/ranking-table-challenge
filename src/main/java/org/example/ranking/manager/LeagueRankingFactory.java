package org.example.ranking.manager;


import org.example.ranking.table.UpsertSortedRankingTable;

public class LeagueRankingFactory {

  /**
   * initialize a leagueManager and dependencies
   * @return LeagueRankManager
   */
  public LeagueRankingManager createManager() {
    return new LeagueRankingManager(new UpsertSortedRankingTable());
  }

}
