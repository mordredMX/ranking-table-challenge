# Ranking table command line application

This command line application captures the games results of a league and generates and print the ranking table (stdin/stdout).

## Environment Setup and application start up

### Prerequisites
* JDK 17.0.6 (it was developed with Temurin jdk) installed. 
    * **Optional** having installed sdkman ```$ sdk install java 17.0.6-tem``` 
* Apache Maven 3.8.1 installed (should work with other versions of maven whiles is 3.x)

### Steps
* Clone this repository.
* Using the terminal move to the root directory
* **Optional**: Setup the jdk for this specific project by executing sdk command ```$ sdk env``` which uses the .sdkmanrc env file
* Build the project ```$ mvn clean install```
* Start application by executing the jar ```$ java -jar target/ranking-table-challenge-1.0-SNAPSHOT.jar```

## User Instructions

* Type league game results line by line in format: "{home team} {goals home team}, {away team} {goals away team}" i.e. Lions 3, Snakes 3.
  * If the line has any formatting errors it will be ignored.
* After that to retrieve the ranking table type enter.