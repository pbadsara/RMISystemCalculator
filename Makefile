# Makefile for Java RMI Calculator Project

JAVAC = javac
JAVA = java
RMIREGISTRY = rmiregistry

SOURCES = Calculator.java CalculatorImplementation.java CalculatorServer.java CalculatorClient.java \

CLASSES = $(SOURCES:.java=.class)

# Default: compile all classes
all: $(java)

%.class: %.java
	$(JAVAC) $<

# Start the RMI registry (default port 1099)
registry:
	$(RMIREGISTRY) 1099 &

# Run server
server: all
	$(JAVA) CalculatorServer

# Run client
client: all
	$(JAVA) CalculatorClient

# Run single client test
test: all
	$(JAVA) CalculatorSingleClientTest

# Run multi client test
test: all
	$(JAVA) CalculatorMultiClientTest

clean:
	rm -f *.class
