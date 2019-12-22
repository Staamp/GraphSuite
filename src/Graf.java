import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.*;
import java.util.Collections;

import static java.util.Map.Entry.comparingByKey;

/***
 * Graph is the class who create nodes, edges and realize many manipulation on directed graph
 */
public class Graf {

    Map<Node, List<Node>> adjList;
    List<Edge> ledge;

    /***
     * Constructor of a graph
     *
     * @param adjList map with node and list of node
     */
    public Graf(Map<Node, List<Node>> adjList) {
        this.adjList = new HashMap<Node, List<Node>>();
        this.ledge = new ArrayList<Edge>();
    }

    /***
     * Constructor who create a graph with nodes and edges with successor array formalism
     *
     * @param node list of node with successor array formalism
     */
    public Graf(int ... node) {
        this.adjList = new HashMap<Node, List<Node>>();
        this.ledge = new ArrayList<Edge>();
        int nodeNbr = node.length;
        if (nodeNbr == 0) {
            return;
        }

        int nodeFrom = 1;
        for(int i = 0; i < nodeNbr; i++) {
            if (node[i] == 0) {
                nodeFrom++;
                continue;
            }
            addEdge(new Node(nodeFrom), new Node(node[i]));
        }
    }



    /*
     *
     * Nodes and Edges
     *
     */


    /***
     * Function who add a node
     *
     * @param n the node added
     */
    public void addNode(Node n) {
        if (!existsNode(n)) {
            adjList.put(n, new ArrayList<Node>());
        }
    }

    /***
     * Overload of the addNode function
     *
     * @param n the node added
     */
    public void addNode(int n) {
        addNode(new Node(n));
    }

    /***
     * Function who check if the node already exist
     *
     * @param n the node to control
     * @return a boolean who say the edge exist
     */
    public boolean existsNode(Node n) {
        if (adjList.containsKey(n)) {
            return true;
        }
        return false;
    }

    /***
     * Overload of the existsNode function
     *
     * @param n the node to control
     * @return a boolean who say the edge exist
     */
    public boolean existsNode(int n) {
        return existsNode(new Node(n));
    }

    /***
     * Function who check if the edge already exist
     *
     * @param e the edge to control
     * @return a boolean who say the edge exist
     */
    public boolean existsEdge(Edge e) {
        if (!existsNode(e.getFrom())) {
            return false;
        }
        if (!existsNode(e.getTo())) {
            return false;
        }
        for (Map.Entry<Node, List<Node>> entry : adjList.entrySet()) {
            for (int i = 0, c = entry.getValue().size(); i < c; i++) {
                if (entry.getKey().getNumber() == e.getFrom().getNumber() && entry.getValue().get(i).getNumber() == e.getTo().getNumber()) {
                    return true;
                }
            }
        }
        return false;
    }

    /***
     * Function who create a new edge with two node
     *
     * @param n1 the node from of edge
     * @param n2 the node to of edge
     */
    public void addEdge(Node n1, Node n2) {
        if(!existsNode(n1)) {
            //System.out.println("The first node doesn't exists. He is added");
            addNode(n1);
        }
        if(!existsNode(n2)) {
            //System.out.println("The second node doesn't exists. He is added");
            addNode(n2);
        }
        Edge e = new Edge(n1, n2);
        if (!existsEdge(e)) {
            adjList.get(n1).add(n2);
            ledge.add(e);
        } else {
            System.out.print("The node " + e.toString() + " already exist. Please create a new edge with other node.");
        }

    }

    /***
     * Overload of the addEdge function
     *
     * @param n1 the node from of edge
     * @param n2 the node to of edge
     */
    public void addEdge(int n1, int n2) {
        addEdge(new Node(n1), new Node(n2));
    }

    /***
     * Overload on the addEdge function
     *
     * @param n1 the node from of edge
     * @param n2 the node to of edge
     * @param w the weighted of edge
     */
    public void addEdge(Node n1, Node n2, int w) {
        if(!existsNode(n1)) {
            //System.out.println("The first node doesn't exists. He is added");
            addNode(n1);
        }
        if(!existsNode(n2)) {
            //System.out.println("The second node doesn't exists. He is added");
            addNode(n2);
        }
        Edge e = new Edge(n1, n2, w);
        if (!existsEdge(e)) {
            adjList.get(n1).add(n2);
            ledge.add(e);
        } else {
            System.out.print("The edge " + e.toString() + " already exist. Please create a new edge with other node.");
        }

    }

    /***
     * Overload on the addEdge function
     *
     * @param n1 the node from of edge
     * @param n2 the node to of edge
     * @param w the weighted of edge
     */
    public void addEdge(int n1, int n2, int w) {
        addEdge(new Node(n1), new Node(n2), w);
    }

    /***
     * Function who remove one node with one node given in parameters and check if the node exist
     *
     * @param n node who where remove
     */
    public void removeNode(Node n) {
        if (existsNode(n)) {
            this.adjList.remove(n);
            for (Map.Entry<Node, List<Node>> entry : adjList.entrySet()) {

                for (int i = 0, c = entry.getValue().size(); i < c; i++) {
                    Node nodeTo = entry.getValue().get(i);
                    if (n.getNumber() == nodeTo.getNumber()) {
                        entry.getValue().remove(i);
                    }
                }
            }
        }
    }

    /***
     * Overload on the removeNode function
     *
     * @param n node who where remove
     */
    public void removeNode(int n) {
        removeNode(new Node(n));
    }

    /***
     *  Function who remove one edge with two nodes given in parameters and check if the edge exist
     *
     * @param n1 node from of edge
     * @param n2 node to of edge
     */
    public void removeEdge(Node n1, Node n2) {
        if (existsNode(n1) && existsNode(n2)) {
            for (Map.Entry<Node, List<Node>> entry : adjList.entrySet()) {
                Node nodeFrom = entry.getKey();
                for (int i = 0, c = entry.getValue().size(); i < c; i++) {
                    Node nodeTo = entry.getValue().get(i);
                    if (n1.getNumber() == nodeFrom.getNumber() && n2.getNumber() == nodeTo.getNumber()) {
                        entry.getValue().remove(i);
                    }
                }
            }
        }
    }

    /***
     *  Overload of the removeEdge function
     *
     * @param n1 node from of edge
     * @param n2 node to of edge
     */
    public void removeEdge(int n1, int n2) {
        removeEdge(new Node(n1), new Node(n2));
    }

    /***
     * Function who where we want to know the successors
     *
     * @param n the node where we want to know the successors
     * @return the list of successors of node n
     */
    public List<Node> getSuccessors(Node n) {
        System.out.println("List of nodes who is successor of node " + n.getNumber() + " :");
        List<Node> successors = new ArrayList<>();
        List<Node> lnode = adjList.get(n);
        int lnodeSize = lnode.size();
        for (int i = 0; i < lnodeSize; i++) {
            successors.add(lnode.get(i));
        }
        for (Map.Entry<Node, List<Node>> entry : adjList.entrySet()) {
            int nodeNumber = entry.getKey().getNumber();
            for (int i = 0, c = entry.getValue().size(); i < c; i++) {
                if (entry.getValue().get(i).getNumber() == n.getNumber()) {
                    successors.add(entry.getKey());
                }
            }
        }
        return successors;
    }

    /***
     * Overload of the getSuccessor function
     *
     * @param n the node where we want to know the successors
     * @return the list of successors of node n
     */
    public List<Node> getSuccessors(int n) {
        return getSuccessors(new Node(n));
    }

    /***
     * Function who return the list of in edge of node n
     *
     * @param n the node where we want to know the in edge
     * @return the list of in edge of node n
     */
    public List<Edge> getInEdge(Node n) {
        //System.out.println("List of edges who entrance of node " + n.getNumber() + " :");
        int numberEdge = numberOfEdge();
        List<Edge> inEdge = new ArrayList<>();
        for (Map.Entry<Node, List<Node>> entry : adjList.entrySet()) {
            int nodeNumber = entry.getKey().getNumber();
            for (int i = 0, c = entry.getValue().size(); i < c; i++) {
                if (entry.getValue().get(i).getNumber() == n.getNumber()) {
                    //System.out.println(" n " + entry.getKey().getNumber());
                    inEdge.add(new Edge(entry.getKey(), n));
                }
            }
        }
        return  inEdge;
    }

    /***
     * Overload of the getInEdge function
     *
     * @param n the node where we want to know the in edge
     * @return the list of in edge of node n
     */
    public List<Edge> getInEdge(int n) {
        return getInEdge(new Node(n));
    }

    /***
     * Function who return the list of out edge of node n
     *
     * @param n the node where we want to know the out edge
     * @return the list of out edge of node n
     */
    public List<Edge> getOutEdge(Node n) {
        //System.out.println("List of edges who out of node " + n.getNumber() + " :");
        int numberEdge = numberOfEdge();
        List<Edge> outEdge = new ArrayList<>();
        List<Node> lnode = adjList.get(n);
        int lnodeSize = lnode.size();
        for (int i = 0; i < lnodeSize; i++) {
            outEdge.add(new Edge(n, lnode.get(i)));
        }
        return  outEdge;
    }

    /***
     * Overload of the getOutEdge function
     *
     * @param n the node where we want to know the in edge
     * @return the list of in edge of node n
     */
    public List<Edge> getOutEdge(int n) {
        return getOutEdge(new Node(n));
    }

    /***
     * Function who return the list of incident edge of node n
     *
     * @param n the node where we want to know the incident edge
     * @return the list of incident edge of node n
     */
    public List<Edge> getIncidentEdges(Node n) {
        List<Edge> listIncident = new ArrayList();
        listIncident.addAll(getOutEdge(n));
        listIncident.addAll(getInEdge(n));
        return listIncident;
    }

    /***
     * Overload of the getIncidentEdges function
     *
     * @param n the node where we want to know the in edge
     * @return the list of in edge of node n
     */
    public List<Edge> getIncidentEdges(int n) {
        return getIncidentEdges(new Node(n));
    }


    /***
     * Function who return the number of node in graph
     *
     * @return the number of node in graph
     */
    public int numberOfNode() {
        return adjList.size();
    }

    /***
     * Function who return the number of edge in graph
     *
     * @return the number of edge in graph
     */
    public int numberOfEdge() {
        int numberEdge = 0;
        for (Map.Entry<Node, List<Node>> entry : adjList.entrySet()) {
            int nodeNumber = entry.getKey().getNumber();
            int nodeNumberEdgeSize = entry.getValue().size();
            numberEdge += nodeNumberEdgeSize;
        }
        return numberEdge;
    }

    /***
     * Function who return a list of all nodes in graph
     *
     * @return a list of all nodes in graph
     */
    public List<Node> getAllNodes() {
        int numberNode = numberOfNode();
        List listNode = new ArrayList<Node>();
        System.out.println("List of nodes (number of nodes : " + numberNode + ") : ");
        for (Map.Entry<Node, List<Node>> entry : adjList.entrySet()) {
            int nodeNumber = entry.getKey().getNumber();
            listNode.add(entry.getKey());
            //System.out.println(nodeNumber);
        }
        sortListNode(listNode);
        return listNode;
    }

    /***
     * Function who return a list of all edges in graph
     *
     * @return a list of all edges in graph
     */
    public List<Edge> getAllEdges() {
        sortMapNodeByKey();
        //System.out.println("List of edges : ");
        List<Edge> listEdge = new ArrayList<Edge>();
        int numberEdge = numberOfEdge();
        for (Map.Entry<Node, List<Node>> entry : adjList.entrySet()) {
            int nodeNumber = entry.getKey().getNumber();
            for (int i = 0, c = entry.getValue().size(); i < c; i++) {
                //System.out.println(nodeNumber + " -> " + entry.getValue().get((i)));
                listEdge.add(new Edge(new Node(nodeNumber), new Node(entry.getValue().get(i).getNumber())));
            }
        }
        //System.out.println("\n");
        return listEdge;
    }

    /***
     * Function who print a list
     *
     * @param l list printed
     */
    public void printList(List l) {
        int lsize = l.size();
        for (int i = 0; i < lsize; i++) {
            String list = l.get(i).toString();
            System.out.println(list);
        }
    }



    /*
     *
     * Graph Representation
     *
     */


    /***
     * Function who realize a successor array
     *
     * @return an array for obtaining a representation of the graph in the successor array formalism
     */
    public int[] getSuccessorArray() {
        sortMapNodeByKey();
        int sizeEdge = this.numberOfEdge();
        int sizeTab = sizeEdge + this.numberOfNode();
        int[] successorArray = new int[sizeTab];

        int j = 0;
        for (Map.Entry<Node, List<Node>> entry : adjList.entrySet()) {
            Node nodeFrom = entry.getKey();
            for (int i = 0, c = entry.getValue().size(); i < c; i++) {
                successorArray[j] = entry.getValue().get(i).getNumber();
                j++;
            }
            successorArray[j] = 0;
            j++;
        }
        return  successorArray;
    }

    /***
     * Function who sort one list by number
     */
    public void sortListNode(List<Node> n) {
        Collections.sort(n, new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                if (o1.getNumber() > o2.getNumber()) {
                    return 1;
                }
                if (o1.getNumber() < o2.getNumber()) {
                    return -1;
                }
                return 0;
            }
        });
    }

    /***
     * Function who sort the map adjList by key and sort the list of the associated value
     */
    public void sortMapNodeByKey() {
        Map<Node, List<Node>> current = adjList;
        Map<Node, List<Node>> lmap = new TreeMap<Node, List<Node>>(
                new Comparator<Node>() {
                    @Override
                    public int compare(Node o1, Node o2) {
                        if (o1.getNumber() > o2.getNumber()) {
                            return 1;
                        }
                        if (o1.getNumber() < o2.getNumber()) {
                            return -1;
                        }
                        return 0;
                    }
                }
        );
        lmap.putAll(current);
        for (Map.Entry<Node, List<Node>> entry : adjList.entrySet()) {
            sortListNode(entry.getValue());
        }
        adjList = lmap;
    }

    /***
     * Sort the list of edge
     * @return the list sorted
     */
    public List<Edge> sortListOfEdge() {
        List<Edge> listEdge = getAllEdges();
        Collections.sort(listEdge, new Comparator<Edge>() {
            @Override
            public int compare(Edge o1, Edge o2) {
                return o1.compareTo(o2);
            }
        });
        return listEdge;
    }

    /***
     * Function who create an adjacency matrix with the graph given
     *
     * @return the matrix of the given graph
     */
    public int[][] getAdjMatrix() {
        sortMapNodeByKey();
        int numberNode = numberOfNode();
        int[][] adjMatrix = new int[numberNode][numberNode];
        int [] SA = getSuccessorArray();
        int SALength = SA.length;
        for (int i = 0; i < numberNode-1; i++) {
            for (int j = 0; j < numberNode-1; j++) {
                adjMatrix[i][j] = 0;
            }
        }
        int numberStart = 1;
        for (int i = 0; i < SALength; i++) {
            if (SA[i] == 0) {
                numberStart++;
                continue;
            }
            adjMatrix[numberStart-1][SA[i]-1] = 1;
        }
        return adjMatrix;
    }



    /*
     *
     * Graph Transformation
     *
     */


    /***
     *  Function who realize a new reversed graph with one graph given
     *
     * @return the reverse graph given
     */
    public Graf getReverseGraph() {
        Graf g = new Graf();

        int nbEdge = ledge.size();
        for (int i = 0; i < nbEdge; i++) {
            //System.out.println("(" + ledge.get(i).getTo() + ", " + ledge.get(i).getFrom() + ") : " + ledge.get(i).getWeighted() + ", ");
            g.addEdge( ledge.get(i).getTo(), ledge.get(i).getFrom(), ledge.get(i).getWeighted());
        }
        return g;
//        int numberEdge = numberOfEdge();
//        for (Map.Entry<Node, List<Node>> entry : adjList.entrySet()) {
//            Node nodeFrom = entry.getKey();
//            for (int i = 0, c = entry.getValue().size(); i < c; i++) {
//                Node nodeTo = entry.getValue().get(i);
//                g.addEdge(nodeTo, nodeFrom, 1);
//            }
//        }
//        return g;
    }

    /***
     * Function who realize transitive closure with one graph
     *
     * @return the graph transformed by the transitive closure
     */
    public Graf getTransitiveClosure() {
        int[][] adjMatrix = getAdjMatrix();
        int numberNode = numberOfNode();
        int[][] result = new int[numberNode][numberNode];

        for (int i = 0; i < numberNode; i++) {
            for (int j = 0; j < numberNode; j++) {
                result[i][j] = adjMatrix[i][j];
            }
        }

        for (int k = 0; k < numberNode; k++) {
            for (int i = 0; i < numberNode; i++) {
                for (int j = 0; j <numberNode; j++) {
                    result[i][j] = (result[i][j] != 0) || ((result[i][k] != 0) && (result[k][j] != 0)) ? 1 : 0;
                }
            }
        }

        return adjMatrixToGraf(result);
    }

    /***
     * Function who realize a graph with adjacency matrix
     *
     * @param adjMatrix
     * @return a graph with one adjacency matrix given in parameters
     */
    public Graf adjMatrixToGraf(int[][] adjMatrix) {
        Graf g = new Graf();
        int lgtAdjMatrix = adjMatrix[0].length;
        for (int i = 0; i < lgtAdjMatrix; i++) {
            for (int j = 0; j < lgtAdjMatrix; j++) {
                if (adjMatrix[i][j] == 1) {
                    Node n1 = new Node(i+1);
                    Node n2 = new Node(j+1);
                    g.addEdge(n1, n2);
                }
            }
        }
        return g;
    }



    /*
     *
     * Graph Traversal
     *
     */

    /***
     * Function who realize a dfs starting by the node given in parameters
     *
     * @param n the node by which to start to realize a dfs
     * @return a list of node of the course
     */
    public List<Node> getDFS(Node n) {
        Node nParam = n;
        int numberNode = numberOfNode();
        List<Node> DFS = new ArrayList<>();
        Vector<Boolean> visited = new Vector<Boolean>(numberNode);
        for (int i = 0; i < numberNode+1; i++) {
            visited.add(false);
        }

        Stack<Integer> stack = new Stack<>();
        stack.push(nParam.getNumber());

        int[] tab = new int[numberNode];
        int cpt = 0;

        while (!stack.empty()) {
            nParam.setNumber(stack.peek());
            stack.pop();
            if (!visited.get(nParam.getNumber())){
                tab[cpt] = nParam.getNumber();
                cpt++;
                visited.set(nParam.getNumber(), true);
            }
            List<Edge> outEdge = getOutEdge(nParam);
            Iterator<Edge> itr = outEdge.iterator();
            while (itr.hasNext()) {
                int v = itr.next().getTo().getNumber();
                if(!visited.get(v)){
                    stack.push(v);
                }
            }
        }

        for (int i = 0, c = tab.length; i < c; i++) {
            DFS.add(new Node(tab[i]));
        }

        return DFS;
    }

    /***
     * Overload of the getDFS function
     *
     * @param n the node by which to start to realize a dfs
     * @return a list of node of the course
     */
    public List<Node> getDFS(int n) {
        return getDFS(new Node(n));
    }

    /***
     * Function who realize a bfs starting by the node given in parameters
     *
     * @param n the node by which to start to realize a bfs
     * @return a list of node of the course
     */
    public List<Node> getBFS(Node n) {
        Node nParam = n;
        int numberNode = numberOfNode();
        List<Node> BFS = new ArrayList<>();

        int[] tab = new int[numberNode];
        int cpt = 0;

        Vector<Boolean> visited = new Vector<Boolean>(numberNode);
        for (int i = 0; i < numberNode+1; i++) {
            visited.add(false);
        }
        LinkedList<Integer> queue = new LinkedList<Integer>();

        visited.set(nParam.getNumber(), true);
        queue.add(nParam.getNumber());

        while (queue.size() != 0) {
            nParam.setNumber(queue.poll());
            tab[cpt] = nParam.getNumber();
            cpt++;

            List<Edge> outEdge = getOutEdge(nParam);
            Iterator<Edge> itr = outEdge.iterator();

            while (itr.hasNext()) {
                int v = itr.next().getTo().getNumber();
                if(!visited.get(v)){
                    visited.set(v, true);
                    queue.add(v);
                }
            }
        }

        for (int i = 0, c = tab.length; i < c; i++) {
            BFS.add(new Node(tab[i]));
        }

        return BFS;
    }

    /***
     * Overload of the getBFS function
     *
     * @param n the node by which to start to realize a dfs
     * @return a list of node of the course
     */
    public List<Node> getBFS(int n) {
        return getBFS(new Node(n));
    }



    /*
     *
     * Graph Export
     *
     */


    /***
     * Function who create a dot format of a graph given
     *
     * @return a string of a dot representation
     */
    public String toDotString() {
        sortMapNodeByKey();
        String dotStringGraph = "digraph g {\n";
        int numberEdge = numberOfEdge();
        int numberNode = numberOfNode();

        for (Map.Entry<Node, List<Node>> entry : adjList.entrySet()) {
            int n = entry.getKey().getNumber();
            dotStringGraph += " " + n + ";\n";
        }

        for (Map.Entry<Node, List<Node>> entry : adjList.entrySet()) {
            int nFrom = entry.getKey().getNumber();
            for (Node nod : entry.getValue()) {
                int nto = nod.getNumber();
                dotStringGraph += " " + nFrom + " -> " + nto + ";\n";
            }
        }

        dotStringGraph += "}";
        return  dotStringGraph;
    }

    /***
     * Function who exporting the graph as a file in the DOT syntax
     */
    public void toDotFile() {
        //String pathOfFileOutput = "D:/Github/file.dot";
        String pathOfFileOutput = System.getProperty("user.dir") + "/" + "graph.dot"; //Current directory
        try {
            File ff = new File(pathOfFileOutput);
            ff.createNewFile();
            FileWriter ffw = new FileWriter(ff);
            try {
                ffw.write(toDotString());
            } finally {
                ffw.close();
            }
        } catch (Exception e) {
            System.out.println("Error. Could not create file");
        }
    }

    /***
     * Create a PDF Image of a given graph with the DOT file
     */
    public void DotFileToPDFImage() {
        toDotFile();
        try {
            Runtime.getRuntime().exec("dot -Tpdf graph.dot -o graph.pdf");
        } catch (Exception e) {
            System.out.println("Error. Could not create the pdf image of the graph.");
        }
    }

    /***
     * Function who read a file with dot formalism and create a graph
     *
     * @param p of the dot file
     * @return a graph
     */
    public static Graf DotFileToGraph(String p) {
        String path = p + ".dot";
        List<String> listDot = new  ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            String line;
            while ((line = br.readLine()) != null) {
                //System.out.println(line);
                listDot.add(line);
            }
            br.close();
        } catch (Exception e) {
            System.out.println("Error. Could not open the dot file.");
        }

        int nbLineDotFile = listDot.size();
        Graf g = null;


        for (int i = 0; i < nbLineDotFile; i++) {
            if (listDot.get(i).equals("\n")) {
                continue;
            }
            if (i == 0) {
                String[] line1 = listDot.get(i).split(" ");
                if (line1[0].equals("digraph")) {
                    g = new Graf();
                }
                if (line1[0].equals("graph")) {
                    g = new UndirectedGraf();
                }
            }
            String[] arrOfStr = listDot.get(i).split(" ");
            if (i != 0 && i != nbLineDotFile-1) {
                if (arrOfStr.length < 3) {
                    String [] arrOfStrNode = arrOfStr[1].split(";");
                    Node n1 = new Node(Integer.parseInt(arrOfStrNode[0]));
                    g.addNode(n1);
                }
                if (arrOfStr.length >= 3) {
                    String [] arrOfStrEdge = arrOfStr[3].split(";");
                    Node n1 = new Node(Integer.parseInt(arrOfStr[1]));
                    Node n2 = new Node(Integer.parseInt(arrOfStrEdge[0]));
                    g.addNode(n1);
                    g.addNode(n2);
                    g.addEdge(n1, n2);
                }
            }
        }

        return g;
    }


    public static void toDotFile(String str) {
        //String pathOfFileOutput = "D:/Github/file.dot";
        String pathOfFileOutput = System.getProperty("user.dir") + "/" + "graph2.dot"; //Current directory
        try {
            File ff = new File(pathOfFileOutput);
            ff.createNewFile();
            FileWriter ffw = new FileWriter(ff);
            try {
                ffw.write(str);
            } finally {
                ffw.close();
            }
        } catch (Exception e) {
            System.out.println("Error. Could not create file");
        }
    }

    /***
     * Function who read a file with dot formalism and create a graph
     *
     * @param str of the dot file
     * @return a graph
     */
    public static Graf StringToGraph(String str) {
        toDotFile(str);
        String pathOfFileOutput = System.getProperty("user.dir") + "/" + "graph2.dot"; //Current directory
        return DotFileToGraph(pathOfFileOutput);
    }

    /***
     * Function who explore graph
     *
     * @param n the node start
     * @param visited array of node explore
     */
    public void DFSUtil(Node n, boolean[] visited) {
        visited[n.getNumber()] = true;
        for (Map.Entry<Node, List<Node>> entry : adjList.entrySet()) {
            if (!visited[entry.getKey().getNumber()]) DFSUtil(entry.getKey(), visited);
        }
    }

    /***
     * Function who say if the graph is connected or not
     *
     * @return a boolean value
     */
    public boolean isConnected() {
        int nbnode = numberOfNode();
        System.out.println(nbnode);
        boolean[] visited = new boolean[nbnode + 1];
        int iter = 0;
        for (Map.Entry<Node, List<Node>> entry : adjList.entrySet()) {
            if(!visited[entry.getKey().getNumber()]) {
                DFSUtil(entry.getKey(), visited);
                iter++;
            }
        }
        if (iter > 1) {
            return false;
        }
        return true;
    }

    /***
     * Function who create a connected graph
     *
     * @return a connected graph
     */
    public static Graf connectedGraph() {
        Graf cg = new Graf();
        int low = 5;
        int high = 50;
        int nodes = (int)(Math.random()*((high - low) + 1) + low);
        //int nodes = 10;
        //System.out.println("Number of nodes with math random " + nodes);

        for (int i = 1; i < nodes; i++) {
            cg.addNode(new Node(i));
        }
        int lowEdge = 1;
        int maxEdge = nodes * nodes;
        int edges = (int)(Math.random()*((maxEdge - lowEdge) + 1) + lowEdge);
        //System.out.println("Number of edges with math random " + edges);
        int n1 = 0;
        int n2 = 0;
        for (int i = 0; i < edges; i++) {
            do {
                n1 = (int) ((Math.random() * nodes) + 1);
                n2 = (int) ((Math.random() * nodes) + 1);
            } while (cg.existsEdge(new Edge(new Node(n1), new Node(n2))));
            cg.addEdge(new Node(n1), new Node(n2));
        }
        cg.sortListOfEdge();

        for (int i = 1; i < nodes; i++) {
            if (cg.getIncidentEdges(new Node(i)).size() == 0) {
                cg.removeNode(new Node(i));
            }
        }

        if (!cg.isConnected()) {
            connectedGraph();
        }

        return cg;
    }

    /***
     * Function who create a dense graph (with density drawing near to 1)
     *
     * @return a dense graph
     */
    public static Graf denseGraph() {
        Random r = new Random();
        int low = 5;
        int high = 50;
        int nodes = r.nextInt(high - low) + low;
        //System.out.println("Number of nodes with random : " + nodes);

        Graf dg = new Graf();

        for (int i = 1; i < nodes; i++) {
            dg.addNode(new Node(i));
        }

        for (int i = 1; i < nodes; i++) {
            for (int j = 1; j < nodes; j++) {
                if (!dg.existsEdge(new Edge(new Node(i), new Node(j)))) {
                    if (i == j) {
                        continue;
                    }
                    dg.addEdge(new Node(i), new Node(j));
                }
            }
        }
        return dg;
    }

    /***
     * Function who create a sparse graph (with density drawing near to 0)
     *
     * @return a sparse graph
     */
    public static Graf sparseGraph() {
        Random r = new Random();
        int low = 5;
        int high = 50;
        int nodes = r.nextInt(high - low) + low;

        Graf sg = new Graf();

        for (int i = 1; i < nodes; i++) {
            sg.addNode(new Node(i));
        }

        return sg;
    }

    /***
     * Function who create a parameterized graph
     *
     * @param nodes number of node
     * @param edges number of edge
     * @param probability edge probability distribution
     * @return a parameterized graph
     */
    public static Graf parameterizedGraph(int nodes, int edges, int probability) {
        Graf pg = new Graf();
        for (int i = 1; i < nodes + 1; i++) {
            pg.addNode(new Node(i));
        }

        int n1 = 0;
        int n2 = 0;
        for (int i = 0; i < edges; i++) {
            do {
                n1 = (int) ((Math.random() * nodes) + 1);
                n2 = (int) ((Math.random() * nodes) + 1);
            } while (pg.existsEdge(new Edge(new Node(n1), new Node(n2))));
            pg.addEdge(new Node(n1), new Node(n2));
        }
        pg.sortListOfEdge();

        return pg;
    }

    /***
     * Recursive function who search if the graph is cyclic or not
     *
     * @param n the node start
     * @param visited array of node for save the node visited
     * @param recStack array of node for save the node in stack
     * @return a boolean value
     */
    public boolean isCyclicUtil(Node n, boolean[] visited, boolean[] recStack) {
        if (recStack[n.getNumber()]) { return true; }
        if (visited[n.getNumber()]) { return false; }
        visited[n.getNumber()] = true;
        recStack[n.getNumber()] = true;
        List<Node> children = adjList.get(n);
        for(Node c: children) {
            if (isCyclicUtil(c, visited, recStack)) {
                return true;
            }
        }
        recStack[n.getNumber()] = false;
        return false;
    }

    /***
     * Function who compute a graph for discover is cyclic or not
     *
     * @return if the graph is cyclic or not
     */
    public boolean isCyclic() {
        int nbNode = numberOfNode();
        //System.out.println(nbNode);
        boolean[] visited = new boolean[nbNode * nbNode];
        boolean[] recStack = new boolean[nbNode * nbNode];

        for (Map.Entry<Node, List<Node>> entry : adjList.entrySet()) {
            if (isCyclicUtil(entry.getKey(), visited,recStack)) {
                return true;
            }
        }
        return false;
    }

    /***
     * Function who create a DAG (Directed acyclic graph)
     *
     * @return a DAG
     */
    public static Graf DAG() {
        Graf g = null;
        do {
            g = connectedGraph();
        } while (g.isCyclic());
        return g;
    }
}
