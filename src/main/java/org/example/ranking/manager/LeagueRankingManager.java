package org.example.ranking.manager;

import java.io.PrintStream;
import org.example.ranking.result.GameResult;
import org.example.ranking.table.RankingTable;
import org.example.ranking.table.TeamStandings;

public class LeagueRankingManager {

  private final RankingTable rankingTable;

  private static final String POINT = "pt";

  private static final String POINTS = "pts";

  public LeagueRankingManager(RankingTable rankingTable) {
    this.rankingTable = rankingTable;
  }

  /**
   * computes a match score:
   * @param matchScoreValue line
   */
  public void computeMatchScoreLine(String matchScoreValue) {
    GameResult matchScore = GameResult.from(matchScoreValue);
    var homeTeamScore = matchScore.getAwayTeamScore();
    var awayTeamScore = matchScore.getHomeTeamScore();
    rankingTable.addPoints(homeTeamScore.team(), homeTeamScore.points());
    rankingTable.addPoints(awayTeamScore.team(), awayTeamScore.points());
  }

  /**
   * prints the ranking table in the give print stream.
   * @param printStream PrintStream the sprint stream where the ranking table will be
   */
  public void printRankingTable(PrintStream printStream) {
    int rank = 1;
    int prevPoints = -1;
    int prevRank = -1;
    for (TeamStandings teamScore: rankingTable.getTeamsRanking()) {
      int currentScore = teamScore.getPoints();
      String pointLbl = teamScore.getPoints() == 1 ? POINT :  POINTS;
      int rankLbl;
      if (prevPoints == currentScore) {
        rankLbl = prevRank;
      } else {
        rankLbl = rank;
        prevRank = rank;
      }
      printStream.printf("%d. %s, %d %s%n",
          rankLbl, teamScore.getName(), currentScore, pointLbl);
      rank++;
      prevPoints = teamScore.getPoints();
    }
  }

}
