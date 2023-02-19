package org.example.ranking.table;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.NavigableSet;
import java.util.TreeSet;

/**
 * Ranking table that sorts the team standing on each update/increment (team points)
 * internally uses a Map to find the team and the current points and a sorted set
 * to keep the expected order by points and name of team; in case of update the team is
 * removed from the set and reinserted with the new order.
 */
public class UpsertSortedRankingTable implements RankingTable {

  private final Map<String, TeamStandings> scores;

  private final NavigableSet<TeamStandings> ranking;

  public UpsertSortedRankingTable() {
    scores =  new HashMap<>();
    ranking = new TreeSet<> (Comparator
        .comparing(TeamStandings::getPoints).reversed()
        .thenComparing(TeamStandings::getName));
  }

  @Override
  public void addPoints(String teamName, int score) {
    TeamStandingsImpl teamScore = (TeamStandingsImpl) scores.get(teamName);
    if (teamScore != null) {
      if (score == 0) {
        return;
      }
      ranking.remove(teamScore);
      teamScore.incrementScore(score);
    } else {
      teamScore = new TeamStandingsImpl(teamName, score);
      scores.put(teamName, teamScore);
    }
    ranking.add(teamScore);
  }

  @Override
  public Collection<TeamStandings> getTeamsRanking() {
    return Collections.unmodifiableNavigableSet(ranking);
  }

}
