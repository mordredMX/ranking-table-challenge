# Ranking table command line application

This application receives game matches lines

## Environment Setup

### Prerequisites
* JDK 17.0.6 (it was developed with Temurin jdk) installed. 
    * **Optional** having installed sdkman ```$ sdk install java 17.0.6-tem``` 
* Apache Maven 3.8.1 installed (should work with other versions of maven whiles is 3.x)

### Steps
* Clone this repository.
* Using the terminal move to the root directory
* **Optional**: Setup the jdk for this specific project by executing sdk command ```$ sdk env``` which uses the .sdkmanrc env file
* Build the project ```$ mvn clean install```
* Move to target directory ```$ cd target```
* Start application by executing the jar ```$ java -jar ranking-table-challenge-1.0-SNAPSHOT.jar```