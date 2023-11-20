# Constraints

You should write a class `com.mobiquity.packer.Packer` with a static API method named pack. This method accepts the absolute path to a test file as a String. The test file will be in UTF-8 format. The pack method returns the solution as a String.

Your method should throw an `com.mobiquity.exception.APIException` if incorrect parameters are being passed.  Therefore, your signature should look like 

```java
public static String pack(String filePath) throws APIException
```

#### Plugins and Technologies Used
- Java 18
- Maven
- TDD
- Integration Testing
- JUnit5, Mockito
- Lombok
- Jacoco
- log4J

#### Assumption
This project could be used as external library or mvn plugin.
The API only process List of items in the given format in `src/main/test/resources/example_input`

#### Expected Input/Output
Input: Absolute File Path Name, UTF-8 format
Output: String as mentioned in  path `src/main/test/resources`

file data is empty or invalid, the API call throws FileNotFound Exception
If anything else goes wrong, the API call throws APIException

#### Data Structure
There can be many ways to solve this problem like using dynamic programming,
I chose to solve this problem, with fast as well as keeping in mind of lightweight solution. 
With this intention, I decided to use the mixture of Heap (PriorityQueue) of java and Map I created in this customized data structures into a class name KnapSackQueue which is a class that collects and sort products keeping in mind of knapsack algorithm.

#### Entry point
call static method pack(fileName) with reference to Packer class
`Packer.pack("some_file_name")`

#### Build And Test
- mvn clean install
- mvn clean test
  The detailed test coverage can be seen in target->jacoco->site->index.html