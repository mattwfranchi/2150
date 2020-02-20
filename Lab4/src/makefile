DIR = cpsc2150/MyQueue/
JC = javac
JVM = java
NAMES = IQueue.java ArrayQueue.java ListQueue.java QueueApp.java
CLASSES = IQueue.class ArrayQueue.class ListQueue.class QueueApp.class
default: $(addprefix ${DIR}, ${NAMES})
	$(JC) $(addprefix ${DIR}, ${NAMES})

run: $(addpredix ${DIR}, ${CLASSES})
	$(JVM) cpsc2150.MyQueue.QueueApp

clean:
	$(RM) $(addprefix ${DIR}, ${CLASSES})
