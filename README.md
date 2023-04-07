
# scoreboard

 For the Implementation of the code of the exercise of the live scoreboard of the football world cup, the following guidelines have been followed:

- One of the main requirements is that it must be a simple library which he tried to reduce the dependencies to as little as possible. For this reason, the use of frameworks that manage the context and the life cycle of objects such as the Spring Framework has been avoided, which would greatly facilitate the design of the solution but would increase the consumption of resources and memory.
For this reason, third-party libraries are barely used to make the solution simple.

- As the solution is requested to focus on the quality of the code, the focus has been placed on the use of TDD, SOLID, Clean Code, design patterns, code coverage, elimination of code smells,... It will be detailed in the following leftover sections in practical cases introduced in the code.

- The jacoco  plugin (coverage code) and sonar plugin have been used to ensure the quality of the code. The design of the classes has been tested with these tools and the code has been refactored so that the code has 100% code coverage in the tests and the elimination of code smells.

  
## TDD

The TDD part is the hardest to show in the code. I have tried to make use of the classic TDD technique Red, Green, Refactor, leaving evidence in GIT commits to demonstrate the process. These are the following considerations:

  

- For the Red step, it covers the necessary requirements required for the implementation of the API. The design covers the successful case, the exceptions, and the edges cases. Once the necessary methods have been carried out to cover the test coverage, I implement the failed tests.

- Then, in the Green phase, the tests have been implemented to give successful results.

- Finally, the tests are refactored and the code is improved.

  
## Clean Code

 In the implementation, an attempt has been made to follow the clean code principles. These are the considerations that have been followed in the implementation:

- The name of the variables, methods and classes are significant, revealing their intention of use, in such a way that you can easily get an idea of ​​what they do.

- An attempt has been made to make simple functions that return exceptions instead of error codes.

- Reduced code repetition as much as possible (DRY). The Sonar tool has helped locate those places where the code might be redundant.

- The code has been formatted establishing the UTF-8 encoding

- The tests are independent of each other, easy to understand, focusing on future maintenance. For an easy understanding of what each test does, the given-then-when naming technique has been chosen.

## SOLID

I have tried to keep each object as simple as possible, applying the single responsibility principle (SRP).

For the open/close principle (OCP) you can locate an example in the code where the **FootballWorldCupScoreboard** class has a relationship to the **IScoreBoardRepository** (interface). For our example we have an implementation of persistence in memory **ScoreBoardMemoryRepository** but if in the future we have another type of persistence (eg database) we can create a new implementation of **IScoreBoardRepository** without having to modify the **FootballWorldCupScoreboard** class.

Because the exercise is "simple", it has not been necessary to apply the interface segregation principle (ISP) since the idea is to make a simple API and it was not necessary to divide it into different interfaces, doing so would complicate the code unnecessarily.

Finally, The Dependency Inversion principle (DIP) is easy to apply with Spring frameworks but in our case it has been discarded due to the design of a simple API. To apply the dependency inversion, an implementation based on the factory Pattern has been chosen, which can be seen in the code in the following example:

- The **FootballWorldCupScoreBoard** class has a memory persistence through the **ScoreBoardMemoryRepository** class. To avoid instantiating this class directly, an IScoreBoardRepositoryFactory factory has been created where each instance implementation of the IScoreBoardRepository type, in this way I ask the factory what type of persistence is going to be instantiated without having to have it directly in the **FootballWorldCupScoreBoard** class.


## Design patterns

 I've used a few design patterns, but I'd like to talk about one and why I've used it. To implement The Dependency inversion principle (DIP), the Factory Pattern has been used, where the FootballWorldCupScoreBoard class itself performs the DI of the IScoreBoardRepository implementation through the Factory Pattern (IScoreBoardRepositoryFactory).

  
## Validations

As the exercise is very simple to carry out the validations, the **Always-Valid Domain Model** guideline has been applied where the Game class itself is in charge of validating its attributes.

  
## code quality

 To control that the code makes use of quality standards, the **jacoco** and **sonarQube** tools have been used. Two plugins have been included in the pom.xml to be able to interact with them:

  

-- ***Jacoco***

	mvn clean package jacoco:report

-- ***Sonar*** (it is necessary to have the Sonar tool installed (1))

	mvn sonar:sonar -Dsonar.token=<TOKEN_SONAR>

The delivered code is tested with 100% code coverage, 0 bugs, 0 Vulnerabilities, 0 Hostspots, 0 Code Smells and 0% duplications.

  

(1) Installing and running sonar in docker

	docker run -d --name sonarqube -e SONAR_ES_BOOTSTRAP_CHECKS_DISABLE=true -p 9000:9000
