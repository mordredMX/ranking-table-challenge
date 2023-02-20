package org.example.ranking.table;

import java.util.SortedMap;
import java.util.SortedSet;

/**
 * @author obarenque
 * defines the specification of a ranking table
 */
public interface RankingTable {

  /**
   * adds points to a team in the ranking table.
   * @param team team name
   * @param points points to be added
   */
  void addTeamPoints(String team, int points);

  /**
   * get the ranking table representation
   * @return ranking table in format SortedMap [score, SortedSet [teams]]
   */
  SortedMap<Integer, SortedSet<String>> getRanking();
}
