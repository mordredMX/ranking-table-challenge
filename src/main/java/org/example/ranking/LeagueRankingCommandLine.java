package org.example.ranking;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;
import org.example.ranking.manager.LeagueRankingFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LeagueRankingCommandLine {

  private static final Logger LOG = LoggerFactory.getLogger(LeagueRankingCommandLine.class);

  private final LeagueRankingFactory factory;

  public LeagueRankingCommandLine() {
    factory = new LeagueRankingFactory();
  }

  public static void main(String[] args) {
    LOG.info("application start");
    LeagueRankingCommandLine app = new LeagueRankingCommandLine();
    app.runCommandLine(System.out, System.err, System.in);
  }

  public boolean isBlank(String str) {
    return str == null || str.isBlank();
  }


  void runCommandLine(PrintStream output, PrintStream outputError, InputStream inputStream) {
    try (Scanner sc = new Scanner(inputStream)) {
      var manager = factory.createManager();
      output.println("""
            ***** League Ranking Application *****
            
            Instructions:
              * Type league game results in format:
                "{home team} {goals home team}, {away team} {goals away team}" i.e. Lions 3, Snakes 3.
              * To retrieve the ranking table type enter.
            """);
      while (sc.hasNextLine()) {
        String str = sc.nextLine();
        if (isBlank(str)) {
          manager.printRankingTable(output);
          manager = factory.createManager();
        } else {
          try {
            manager.computeMatchScoreLine(str);
          } catch (Exception e) {
            outputError.printf("Error while processing operation %s", e.getMessage());
            LOG.error("error", e);

          }
        }
      }
      output.println("Application is terminated");
    }
  }

}
