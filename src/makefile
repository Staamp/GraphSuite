JFLAGS = -g

JC = javac

JVM = java

FILE=

.SUFFIXES: .java .class

.java.class:
	$(JC) $(JFLAGS) $*.java 

CLASSES = Node.java \
	Edge.java \
	Graf.java \
	UndirectedGraf.java \
	nodePert.java \
	workers.java \
	pertPW.java \
	AppMainPW4.java


MAIN = AppMainPW4

default: classes

classes: $(CLASSES:.java=.class)

run: classes
	$(JVM) $(MAIN)

clean:
	rm -rf *.class
