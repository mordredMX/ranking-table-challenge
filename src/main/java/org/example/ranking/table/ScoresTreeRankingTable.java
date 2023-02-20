package org.example.ranking.table;

import java.util.Collections;
import java.util.HashMap;
import java.util.Objects;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;


/**
 * @author obarenque
 * Ranking table internally implemented with a SortedMap where the key is the total points
 * which have descending order, and the value a SortedSet of the teams that have those points
 * each time the team is updated the team is removed from the previous rank/ position and
 * inserted with the new rank. In addition, a HashMap with the mapping between team and points
 * is maintained in order to find in the sortedMap the points associated to the team.
 */
public class ScoresTreeRankingTable implements RankingTable{

  private final HashMap<String, Integer> teamScores;

  private final SortedMap<Integer, SortedSet<String>> ranking;

  public ScoresTreeRankingTable() {
    teamScores = new HashMap<>();
    ranking = new TreeMap<>(Collections.reverseOrder());
  }

  /**
   * get the ranking SortedMap representation of the ranking table
   * each ranked score/points contains the set of teams that have the
   * scores order alphabetically.
   * @return SortedMap [score, SortedSet [teams]]
   */
  @Override
  public SortedMap<Integer, SortedSet<String>> getRanking() {
    return Collections.unmodifiableSortedMap(ranking);
  }

  /**
   * add points to a team in order to fill the ranking table.
   *  if the points add zero to an existing team score then the
   *  process will be ignored.
   * @param teamName the team name
   * @param points the points to be added to the team
   */
  @Override
  public void addTeamPoints(String teamName, int points) throws  RankingTableException{
    Objects.requireNonNull(teamName, "team name cannot be null");
    if (teamName.isBlank()) {
      throw new RankingTableException("team name cannot be blank");
    }
    teamName = teamName.trim();
    if (points < 0) {
      throw new RankingTableException("team points cannot be negative");
    }
    int totalPoints = points;
    Integer existingPoints = teamScores.get(teamName);
    if (existingPoints != null) {
      if (points == 0) {
        return;
      }
      removeTeamFromRanking(existingPoints, teamName);
      totalPoints = existingPoints + totalPoints;
    }
    teamScores.put(teamName, totalPoints);
    addTeamToRanking(totalPoints, teamName);
  }

  private void removeTeamFromRanking(int points, String teamName) {
    var teamSet = ranking.get(points);
    if (teamSet != null) {
      teamSet.remove(teamName);
      if (teamSet.isEmpty()) {
        ranking.remove(points);
      }
    }
  }

  private void addTeamToRanking(int points, String teamName) {
    var teamSet = ranking.get(points);
    if (teamSet == null) {
      teamSet =  new TreeSet<>();
      teamSet.add(teamName);
      ranking.put(points, teamSet);
    } else  {
      teamSet.add(teamName);
    }
  }

}
