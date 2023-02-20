package org.example.ranking;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import org.example.ranking.manager.LeagueRankingFactory;
import org.junit.jupiter.api.Test;

class LeagueRankingCommandLineIntTest {

  private final LeagueRankingCommandLine commandLine =
      new LeagueRankingCommandLine(new LeagueRankingFactory());

  private static final String INPUT = """
        Lions 3, Snakes 3
        Tarantulas 1, FC Awesome 0
        Lions 1, FC Awesome 1
        Tarantulas 3, Snakes 1
        Lions 4, Grouches 0
        
        testerror
   
        """;

  private static final String EXPECTED_OUTPUT = """
      ***** League Ranking Application *****
            
      Instructions:
        * Type league game results in format:
          "{home team} {goals home team}, {away team} {goals away team}" i.e. Lions 3, Snakes 3.
        * To retrieve the ranking table type enter.
            
      1. Tarantulas, 6 pts
      2. Lions, 5 pts
      3. FC Awesome, 1 pt
      3. Snakes, 1 pt
      5. Grouches, 0 pts
      Application is terminated
      """;

  private static final String EXPECTED_ERROR_OUTPUT
      = "Ignoring line testerror - format error: game result not matching expected pattern\n";

  @Test
  void runCommandLineTest() {
    final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    PrintStream output = new PrintStream(outputStream);

    final ByteArrayOutputStream errStream = new ByteArrayOutputStream();
    PrintStream error = new PrintStream(errStream);

    InputStream targetStream = new ByteArrayInputStream(INPUT.getBytes());

    commandLine.runCommandLine(output, error, targetStream);
    assertEquals(EXPECTED_OUTPUT ,outputStream.toString());
    assertEquals(EXPECTED_ERROR_OUTPUT ,errStream.toString());
  }
}