package org.example.ranking.table;

import java.util.Collection;

public interface RankingTable {

  void addPoints(String team, int points);

  Collection<TeamStandings> getTeamsRanking();
}
