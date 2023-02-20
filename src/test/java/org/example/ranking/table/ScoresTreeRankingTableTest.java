package org.example.ranking.table;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Iterator;
import java.util.Map.Entry;
import java.util.SortedSet;
import org.junit.jupiter.api.Test;

class ScoresTreeRankingTableTest {

  private final ScoresTreeRankingTable rankingTable =  new ScoresTreeRankingTable();

  @Test
  void addTeamPointsTestNullTeamName() {
    var exception = assertThrows(NullPointerException.class, () ->
        rankingTable.addTeamPoints(null, 1));
    assertEquals("team name cannot be null", exception.getMessage());
  }

  @Test
  void addTeamPointsTestEmptyTeamName() {
    var exception = assertThrows(RankingTableException.class, () ->
        rankingTable.addTeamPoints("", 1));
    assertEquals("team name cannot be blank", exception.getMessage());
  }

  @Test
  void addTeamPointsTestNegativePoints() {
    var exception = assertThrows(RankingTableException.class, () ->
        rankingTable.addTeamPoints("supers", -1));
    assertEquals("team points cannot be negative", exception.getMessage());
  }


  @Test
  void rankingTableTestOneTeamWithZero() {
    rankingTable.addTeamPoints("test 2", 0);
    var iter = rankingTable.getRanking().entrySet().iterator();
    assertPointsAndTeams(iter, 0, "test 2");
    assertFalse(iter.hasNext());
  }

  @Test
  void rankingTableTestTeamTrim() {
    rankingTable.addTeamPoints(" goats 04", 1);
    rankingTable.addTeamPoints("  goats 04  ", 3);
    var iter = rankingTable.getRanking().entrySet().iterator();
    assertPointsAndTeams(iter, 4, "goats 04");
    assertFalse(iter.hasNext());
  }

  @Test
  void rankingTableTestMultipleTeamsWithSameScore() {
    rankingTable.addTeamPoints("test3", 1);
    rankingTable.addTeamPoints("test2", 0);
    rankingTable.addTeamPoints("test1", 3);
    rankingTable.addTeamPoints("test1", 3);
    rankingTable.addTeamPoints("test2", 1);
    rankingTable.addTeamPoints("test3", 3);
    rankingTable.addTeamPoints("test5", 3);
    rankingTable.addTeamPoints("test5", 1);
    rankingTable.addTeamPoints("test2", 3);
    rankingTable.addTeamPoints("test2", 1);
    rankingTable.addTeamPoints("test4", 3);
    rankingTable.addTeamPoints("test4", 3);
    var iter = rankingTable.getRanking().entrySet().iterator();
    assertPointsAndTeams(iter, 6, "test1", "test4");
    assertPointsAndTeams(iter, 5, "test2");
    assertPointsAndTeams(iter, 4, "test3", "test5");
    assertFalse(iter.hasNext());
  }

  private void assertPointsAndTeams(Iterator<Entry<Integer, SortedSet<String>>> iter,
      int expectedPoints, String ... expectedTeams) {
    assertTrue(iter.hasNext());
    var entry = iter.next();
    var points = entry.getKey();
    var teams =  entry.getValue();
    assertEquals(expectedPoints, points);
    var teamsIter = teams.iterator();
    for(String expectedTeam : expectedTeams) {
      assertEquals(expectedTeam, teamsIter.next());
    }
    assertFalse(teamsIter.hasNext());
  }

}