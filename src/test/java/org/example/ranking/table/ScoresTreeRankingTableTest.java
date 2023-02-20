package org.example.ranking.table;

import org.junit.jupiter.api.Test;

class ScoresTreeRankingTableTest {

  private ScoresTreeRankingTable rankingTable =  new ScoresTreeRankingTable();

  @Test
  void addTeamScore() {
    rankingTable.addTeamPoints("test3", 1);
    rankingTable.addTeamPoints("test3", 3);
    rankingTable.addTeamPoints("test1", 3);
    rankingTable.addTeamPoints("test1", 3);
    rankingTable.addTeamPoints("test2", 1);
    rankingTable.addTeamPoints("test2", 3);
    rankingTable.addTeamPoints("test2", 0);
    rankingTable.addTeamPoints("test2", 1);
    rankingTable.addTeamPoints("test4", 3);
    rankingTable.addTeamPoints("test4", 3);
    rankingTable.getRanking().forEach((k, v)-> System.out.println(k + " " + v));
  }
}