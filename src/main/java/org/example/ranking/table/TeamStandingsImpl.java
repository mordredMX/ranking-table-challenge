package org.example.ranking.table;

class TeamStandingsImpl implements TeamStandings {
  private final String name;

  private int points;

  public TeamStandingsImpl(String name, int points) {
    this.name = name;
    this.points = points;
  }

  public String getName() {
    return name;
  }

  public int getPoints() {
    return points;
  }

  public void incrementScore(int increment) {
    points =  points + increment;
  }

  @Override
  public String toString() {
    return String.format("%s - %d", name, points);
  }
}
