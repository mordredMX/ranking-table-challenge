package org.example.ranking;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import org.example.ranking.manager.LeagueRankingFactory;
import org.junit.jupiter.api.Test;

class LeagueRankingCommandLineTest {

  LeagueRankingCommandLine commandLine = new LeagueRankingCommandLine();

  private static final String INPUT = """
        Lions 3, Snakes 3
        Tarantulas 1, FC Awesome 0
        Lions 1, FC Awesome 1
        Tarantulas 3, Snakes 1
        Lions 4, Grouches 0
        
        testerror
   
        """;

  @Test
  void runCommandLineTest() {
    final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    PrintStream output = new PrintStream(outputStream);

    final ByteArrayOutputStream errStream = new ByteArrayOutputStream();
    PrintStream error = new PrintStream(errStream);

    InputStream targetStream = new ByteArrayInputStream(INPUT.getBytes());

    commandLine.runCommandLine(output, error, targetStream);
    System.out.println(outputStream.toString());
    System.out.println(errStream.toString());
  }
}