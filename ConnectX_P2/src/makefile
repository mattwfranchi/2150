DIR = cpsc2150/connectX
JC = javac
JVM = java
NAMES = $(wildcard ${DIR}/*.java)
CLASSES = $(patsubst %.java, %.class, ${NAMES})

default: ${NAMES}
	$(JC) ${NAMES}

run: ${CLASSES}
	$(JVM) cpsc2150.connectX.GameScreen



clean:
	$(RM) ${CLASSES}
