DIR = cpsc2150/connectX
JC = javac
JVM = java
JT = javac -cp .:/usr/share/java/junit4.jar
JRT = java -cp .:/usr/share/java/junit4.jar org.junit.runner.JUnitCore
NAMES = $(wildcard ${DIR}/*.java)
NAMES := $(filter-out ${DIR}/TestGameBoard.java, $(NAMES))
NAMES := $(filter-out ${DIR}/TestGameBoardMem.java, $(NAMES))
TESTNAMES = $(wildcard ${DIR}/Test*.java)

CLASSES = $(patsubst %.java, %.class, ${NAMES})
TESTCLASSES = $(patsubst %.java, %.class, ${TESTNAMES})

default: ${NAMES}
	$(JC) ${NAMES}

run: ${CLASSES}
	$(JVM) cpsc2150.connectX.GameScreen

test: ${TESTNAMES}
	$(JT) $(TESTNAMES)

testGB: ${TESTCLASSES}
	$(JRT) cpsc2150.connectX.TestGameBoard

testGBMem: ${TESTCLASSES}
	$(JRT) cpsc2150.connectX.TestGameBoardMem

clean:
	$(RM) ${CLASSES}
