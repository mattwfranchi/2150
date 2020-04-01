

DIR = cpsc2150/Mortgages/
JC = javac
JVM = java
NAMES = $(wildcard ${DIR}/*.java)
CLASSES = $(patsubst %.java, %.class, ${NAMES})

default: ${NAMES}
	$(JC) ${NAMES}

run: ${CLASSES}
	$(JVM) cpsc2150.myQueue.MortgageApp

clean:
	$(RM) ${CLASSES}
