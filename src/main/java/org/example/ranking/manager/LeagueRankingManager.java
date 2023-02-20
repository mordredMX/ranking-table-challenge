package org.example.ranking.manager;

import java.io.PrintStream;
import java.util.SortedSet;
import org.example.ranking.result.GameResult;
import org.example.ranking.table.RankingTable;

/**
 * @author obarenque
 *  masks business logic related to adding game results
 *  and print the ranking table
 */
public class LeagueRankingManager {

  private final RankingTable rankingTable;

  private static final String POINT = "pt";

  private static final String POINTS = "pts";

  public LeagueRankingManager(RankingTable rankingTable) {
    this.rankingTable = rankingTable;
  }

  /**
   * computes a game result in text representation,
   * will be added to the ranking table.
   * @param gameResultLine text line of a game result
   */
  public void computeGameResultLine(String gameResultLine) {
    GameResult matchScore = GameResult.from(gameResultLine);
    var homeTeamScore = matchScore.getAwayTeamScore();
    var awayTeamScore = matchScore.getHomeTeamScore();
    rankingTable.addTeamPoints(homeTeamScore.team(), homeTeamScore.points());
    rankingTable.addTeamPoints(awayTeamScore.team(), awayTeamScore.points());
  }

  /**
   * prints the ranking table in the give print stream.
   * @param printStream PrintStream the sprint stream where the ranking table will be
   */
  public void printRankingTable(PrintStream printStream) {
    int rank = 1;
    int prevPoints = -1;
    int prevRank = -1;
    for (var scoreEntry: rankingTable.getRanking().entrySet()) {
      int currentScore = scoreEntry.getKey();
      SortedSet<String> teams = scoreEntry.getValue();
      String pointLbl = currentScore == 1 ? POINT : POINTS;
      int rankLbl;
      if (prevPoints == currentScore) {
        rankLbl = prevRank;
      } else {
        rankLbl = rank;
        prevRank = rank;
      }
      teams.forEach(team -> printStream.printf("%d. %s, %d %s%n",
          rankLbl, team, currentScore, pointLbl));
      rank = rank + teams.size();
      prevPoints = currentScore;
    }
  }

}
