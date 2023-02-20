package org.example.ranking;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;
import org.example.ranking.manager.LeagueRankingFactory;
import org.example.ranking.result.GameResultFormatException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author obarenque
 * Command line presentation logic that handles input, output and errors
 * to fill and present the  league ranking table by the application users.
 *
 */
public class LeagueRankingCommandLine {

  private static final Logger LOG = LoggerFactory.getLogger(LeagueRankingCommandLine.class);

  private final LeagueRankingFactory factory;

  public LeagueRankingCommandLine(LeagueRankingFactory factory) {
    this.factory = factory;
  }

  /**
   * starts the command line application.
   * @param args arguments
   */
  public static void main(String[] args) {
    LOG.info("Application starting");
    LeagueRankingCommandLine app = new LeagueRankingCommandLine(new LeagueRankingFactory());
    try {
      app.runCommandLine(System.out, System.err, System.in);
    } catch(Exception e) {
      LOG.error("unexpected error", e);
    }
  }

  /**
   *
   * @param output output print stream of the information to be displayed to the user
   * @param outputError output error print stream for errors presented to the user
   * @param inputStream input stream to handle the information the user enters
   */
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
        if (str == null || str.isBlank()) {
          manager.printRankingTable(output);
          manager = factory.createManager();
        } else {
          try {
            manager.computeGameResultLine(str);
          } catch (GameResultFormatException e) {
            outputError.printf("Ignoring line %s - format error: %s%n",
                str, e.getMessage());
            LOG.warn("Format error processing line {}", str, e);
          } catch (Exception e) {
            outputError.printf("Ignoring line %s - unexpected error: %s%n",
                str, e.getMessage());
            LOG.error("Unexpected error processing line {}", str, e);
          }
        }
      }
      output.println("Application is terminated");
    }
  }

}
