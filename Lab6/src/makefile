DIR = cpsc2150/MyQueue/
JC = javac
JVM = java
NAMES = IQueue.java ArrayQueue.java ListQueue.java QueueApp.java AbsQueue.java StringQueueApp.java
CLASSES = IQueue.class ArrayQueue.class ListQueue.class QueueApp.class AbsQueue.class StringQueueApp.class
default: $(addprefix ${DIR}, ${NAMES})
	$(JC) -Xlint:unchecked $(addprefix ${DIR}, ${NAMES})

runInt: $(addpredix ${DIR}, ${CLASSES})
	$(JVM) cpsc2150.MyQueue.QueueApp

runString: $(addpredix ${DIR}, ${CLASSES})
	$(JVM) cpsc2150.MyQueue.StringQueueApp

clean:
	$(RM) $(addprefix ${DIR}, ${CLASSES})
