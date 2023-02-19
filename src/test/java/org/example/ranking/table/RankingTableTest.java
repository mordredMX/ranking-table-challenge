package org.example.ranking.table;

import org.junit.jupiter.api.Test;

class RankingTableTest {

  private RankingTable rankingTable =  new UpsertSortedRankingTable();

  @Test
  void addTeamScore() {
    rankingTable.addPoints("test3", 1);
    rankingTable.addPoints("test3", 3);
    rankingTable.addPoints("test1", 3);
    rankingTable.addPoints("test1", 3);
    rankingTable.addPoints("test2", 1);
    rankingTable.addPoints("test2", 3);
    rankingTable.addPoints("test2", 6);
    rankingTable.addPoints("test2", 1);
    rankingTable.addPoints("test4", 3);
    rankingTable.addPoints("test4", 3);
    rankingTable.getTeamsRanking().forEach(System.out::println);
  }
}