

DIR = cpsc2150/sets/
JC = javac
JVM = java
NAMES = $(wildcard ${DIR}/*.java)
CLASSES = $(patsubst %.java, %.class, ${NAMES})

default: ${NAMES}
	$(JC) ${NAMES}

runInt: ${CLASSES}
	$(JVM) cpsc2150.sets.SetApp

runDouble: ${CLASSES}
    $(JVM) cpsc2150.sets.DoubleSetApp

clean:
	$(RM) ${CLASSES}
