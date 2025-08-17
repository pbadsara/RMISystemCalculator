# Java RMI Calculator System

## Overview

This project is a distributed calculator implemented using Java RMI (Remote Method Invocation). The server hosts a stack and provides clients with remote methods to push integer values and operations (`min`, `max`, `gcd`, `lcm`) to the stack, as well as `pop`, `isEmpty`, and `delayPop`.  
Automated tests are provided using JUnit to ensure correctness under single and multiple client scenarios.

---

## File Structure

| File                          | Purpose                                            |
|-------------------------------|----------------------------------------------------|
| `Calculator.java`             | RMI interface defining remote methods              |
| `CalculatorImplementation.java`| Implementation of RMI methods and stack logic      |
| `CalculatorServer.java`       | Bootstraps server and binds service to registry    |
| `CalculatorClient.java`       | Example client for manual testing (optional)       |
| `CalculatorSingleClientTest.java` | JUnit tests for single-client usage            |
| `CalculatorMultiClientTest.java`  | JUnit tests for simulated multi-client usage   |
| `README.md`                   | This documentation                                |
| `lib/junit-4.13.2.jar`        | JUnit 4 library (downloaded separately)            |
| `lib/hamcrest-core-1.3.jar`   | Hamcrest core (downloaded separately)             |

---

## Prerequisites

- Java JDK 8 or above
- IntelliJ IDEA (recommended) or any Java IDE
- JUnit 4 and Hamcrest JARs (`junit-4.13.2.jar`, `hamcrest-core-1.3.jar`)

---

## Compilation and Setup (without Maven)

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

- **Find your compiled classes directory.**
  - In IntelliJ: typically `out/production/YourProjectName`
- **Open Terminal in that directory and run:**
rmiregistry


*(Leave this terminal open.)*

### 2. **Start the Server**

- In IntelliJ: Right-click `CalculatorServer.java` > Run

- OR from terminal in the same directory:
java CalculatorServer

- Wait for `Calculator server ready.` output.

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

## Customization and Notes

- You may add more JUnit test cases for additional edge cases.
- Manual client (`CalculatorClient.java`) can be run multiple times from separate terminals to simulate many users.
- For **per-client stack** bonus, supply a unique identifier for each client and modify the implementation accordingly.

---

## Contact

For setup issues or project questions, contact your instructor or lab assistant, or refer to your institution's help resources.

---
