package org.example.ranking.result;

/**
 * @author obarenque
 * Exception thrown on formatting error of the GameResult input.
 */
public class GameResultFormatException extends RuntimeException {

  public GameResultFormatException(String message) {
    super(message);
  }

}
