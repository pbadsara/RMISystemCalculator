#**Distributed Systems Assignment 1**
#**Submitted By: Prateek Badsara a1988366**
# Java RMI System: Calculator

## Overview

This project is a calculator made using Java RMI (Remote Method Invocation). The server hosts a stack and provides clients with remote methods to push integer values and operations (`min`, `max`, `gcd`, `lcm`) to the stack, as well as `pop`, `isEmpty`, and `delayPop`. Where 'min' provides the minimum value in the stack, 'max' provides the maximum value in the stack, 'lcm' gives us the least common multiple of the values pushed in the stack, whereas 'gcd' helps in getting the greatest common divisor. 
Automated tests are provided to ensure correctness under single and multiple client scenarios. The tests are leveraging JUnit to function. 

---

## File Structure

| File                          | Purpose                                            |
|-------------------------------|----------------------------------------------------|
| `Calculator.java`             | This is RMI interface defining all the remote methods              |
| `CalculatorImplementation.java`| This is the Implementation of RMI methods and all stack logics      |
| `CalculatorServer.java`       | Helps initiate the server side and binds service to registry    |
| `CalculatorClient.java`       | Client side code for manual testing       |
| `CalculatorSingleClientTest.java` | JUnit tests for confirming the working of RMI system for a single client            |
| `CalculatorMultiClientTest.java`  | JUnit tests for confirming the working of RMI system for multiple clients   |
| `README.md`                   | This documentation provides details about the whole program and its implementation                |
| `lib/junit-4.13.2.jar`        | JUnit 4 library (downloaded separately)            |
| `lib/hamcrest-core-1.3.jar`   | Hamcrest core (downloaded separately)             |

---

## Prerequisites

- Java JDK 8 or above
- Knowledge of Java and RMI systems
- IntelliJ IDEA (recommended) or any Java IDE
- JUnit 4 and Hamcrest JARs (`junit-4.13.2.jar`, `hamcrest-core-1.3.jar`)

---

## Compilation and Setup (without the use of Maven)

1. **Download JUnit and Hamcrest JARs:**
   - [JUnit 4.13.2](https://repo1.maven.org/maven2/junit/junit/4.13.2/junit-4.13.2.jar)
   - [Hamcrest 1.3](https://repo1.maven.org/maven2/org/hamcrest/hamcrest-core/1.3/hamcrest-core-1.3.jar)
   - Place both files in a `lib` directory at your project root.

2. **Compile Code:**
   - Using IntelliJ: Build Project (`Build > Build Project`)
   - OR from the terminal:
     ```
     javac -cp . src/*.java
     ```

---

## How to Run (IntelliJ Recommended)

### 1. **Start RMI Registry**

- We do not need to start the RMI Registry separately; we will initialise it in the CustomerServer file itself.

### 2. **Start the Server**

- In IntelliJ: Right-click `CalculatorServer.java` > Run

- OR from the terminal in the same directory:
java CalculatorServer

- Wait for `Calculator server ready.` output. This tells us that the server is up and running, and the RMI Registry is also set up. 

### 3. **Run Example Client (optional)**
- To try the client interactively:
java CalculatorClient

- Or use/modify for ad-hoc manual tests.

---

## Running the Automated Tests

### 4. **Add JUnit and Hamcrest JARs to Project**

- In IntelliJ: `File > Project Structure > Modules > Dependencies > + (Add JARs)`
- Add `lib/junit-4.13.2.jar` and `lib/hamcrest-core-1.3.jar`

### 5. **Run Tests (Recommended: IntelliJ UI)**

- Right-click `CalculatorSingleClientTest.java` > Run
- Right-click `CalculatorMultiClientTest.java` > Run
- Or right-click the package folder and select "Run All Tests".

### 6. **Run Tests (Terminal Command Line)**

- Make sure JUnit and Hamcrest are on the classpath:
javac -cp “.:lib/junit-4.13.2.jar:lib/hamcrest-core-1.3.jar” *.java java -cp “.:lib/junit-4.13.2.jar:lib/hamcrest-core-1.3.jar” org.junit.runner.JUnitCore CalculatorSingleClientTest java -cp “.:lib/junit-4.13.2.jar:lib/hamcrest-core-1.3.jar” org.junit.runner.JUnitCore CalculatorMultiClientTest

*(Use `;` as the separator on Windows, `:` on Linux/Mac.)*

---

## Simulating Multiple Clients

- `CalculatorMultiClientTest.java` launches several threads to represent multiple concurrent clients calling remote methods.  
- Adjust the number of threads in the test class if needed to increase concurrency.

---

## Troubleshooting

| Problem                                         | Solution                                                                               |
|-------------------------------------------------|----------------------------------------------------------------------------------------|
| `NotBoundException: CalculatorService`           | Start `rmiregistry` then `CalculatorServer` before running clients or tests            |
| `java.rmi.ConnectException`                      | Ensure server and registry are running, using the correct port and host                |
| Client or test class can't find JUnit/Hamcrest   | Verify JARs are added as dependencies or on the classpath                              |
| Starting `rmiregistry` fails "no such directory" | Start it from the compiled classes directory (`out/production/YourProjectName`)        |


---

## Contact

For setup issues or project questions, me or search online help resources.

---
