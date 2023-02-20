package org.example.ranking;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import org.example.ranking.manager.LeagueRankingFactory;
import org.example.ranking.manager.LeagueRankingManager;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

@MockitoSettings(strictness = Strictness.WARN)
@ExtendWith(MockitoExtension.class)
class LeagueRankingCommandLineTest {

  @Mock
  private LeagueRankingFactory factory;

  @Mock
  private LeagueRankingManager manager;

  @InjectMocks
  private LeagueRankingCommandLine commandLine;

  private static final String INPUT = """
        unexpected 3, error 3

        """;

  private static final String EXPECTED_OUTPUT = """
      ***** League Ranking Application *****
            
      Instructions:
        * Type league game results in format:
          "{home team} {goals home team}, {away team} {goals away team}" i.e. Lions 3, Snakes 3.
        * To retrieve the ranking table type enter.
        
      Application is terminated
      """;

  private static final String EXPECTED_ERROR_OUTPUT
      = "Ignoring line unexpected 3, error 3 - unexpected error: unexpected error\n";

  @Test
  void runCommandLineTestHandleUnexpectedException() {
    final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    PrintStream output = new PrintStream(outputStream);

    final ByteArrayOutputStream errStream = new ByteArrayOutputStream();
    PrintStream error = new PrintStream(errStream);

    InputStream targetStream = new ByteArrayInputStream(INPUT.getBytes());

    when(factory.createManager()).thenReturn(manager);
    doThrow(new RuntimeException("unexpected error"))
        .when(manager).computeGameResultLine("unexpected 3, error 3");
    commandLine.runCommandLine(output, error, targetStream);
    assertEquals(EXPECTED_OUTPUT ,outputStream.toString());
    assertEquals(EXPECTED_ERROR_OUTPUT ,errStream.toString());
  }
}