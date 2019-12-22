This work was done in a group. Maxime Serre & Nicolas Courvoisier in TPC.

We have the following classes:
-Node.java
-Edge.java
-Graf.java
-UndirectedGraf.java
-AppMain.java
-interfaceGraphique.java
and a makefile.

Node.java is a class for creating nodes in a graph and returning all the desired information to a node. This class can also compare nodes.

Edge.java allows you to create edges between two nodes. This class also makes it possible to compare nodes since it implements comparable.

Graf.java is a class that implements all functions specified in the library topic. It is here that the graphs are created and that the various calculations on the graphs are effected. It is also in this class that the random graphs are made.

UndirectedGraph.java is similar to Graph.java since UndirectedGraph.java is extended from Graph.java. In this class, we manage all the non-directed graphs. The functions are the same as in Graph.java except for some functions that can not be performed when the graph is not directed. Or else some functions had to be rewritten so that it works also in the case of an unspecified graph.

AppMain.java is the class that starts the graph library. Once started, you can load a graph from a file respecting the formalize dot (enter only the file, eg 'graph' without quotation marks and without .dot extensions), generate random graphs and perform various operations on all graphs that you will create.

graphicalIndex.java is a class to launch the project with a graphical interface. However, the project was done with intellij and we could not make it work with the makefile and command lines of a terminal. So, for the moment it is not functional.

Makefile is the file that compiles and executes the project. 'make' to compile and 'make run' to execute.