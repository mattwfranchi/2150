

DIR = cpsc2150/MyQueue/
JC = javac
JVM = java
NAMES = $(wildcard ${DIR}/*.java)
CLASSES = $(patsubst %.java, %.class, ${NAMES})

default: ${NAMES}
	$(JC) ${NAMES}

runInt: ${CLASSES}
	$(JVM) cpsc2150.myQueue.QueueApp

runString: ${CLASSES}
    $(JVM) cpsc2150.myQueue.StringQueueApp

test: ${NAMES}
    $(JC) ${NAMES}

testArr: ${CLASSES}
    $(JVM) cpsc2150.myQueue.testArrayQueue

testList: ${CLASSES}
    $(JVM) cpsc2150.myQueue.testListQueue

clean:
	$(RM) ${CLASSES}
