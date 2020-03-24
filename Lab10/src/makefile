DIR = cpsc2150/listDec
JC = javac
JVM = java
NAMES = $(wildcard ${DIR}/*.java)
CLASSES = $(patsubst %.java, %.class, ${NAMES})

default: ${NAMES}
	$(JC) ${NAMES}

run: ${CLASSES}
	$(JVM) cpsc2150.listDec.ShuffleApp

clean:
	$(RM) ${CLASSES}
