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
