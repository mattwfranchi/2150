DIR = cpsc2150/connectX
JC = javac
JVM = java
NAMES = $(wildcard ${DIR}/*.java)
NAMES := $(filter-out {DIR}/Test*.java, $(NAMES))
TESTNAMES = $(wildcard ${DIR}/Test*.java)

CLASSES = $(patsubst %.java, %.class, ${NAMES})
TESTCLASSES = $(patsubst %.java, %.class, ${TESTNAMES})

default: ${NAMES}
	$(JC) ${NAMES}

run: ${CLASSES}
	$(JVM) cpsc2150.connectX.GameScreen

test: ${TESTCLASSES}
    $(JC) $(TESTNAMES)

testGB: ${TESTCLASSES}
    $(JVM) cpsc2150.connectX.TestGameBoard

testGBMem: ${TESTCLASSES}
    $(JVM) cpsc2150.connectX.TestGameBoardMem


clean:
	$(RM) ${CLASSES}
