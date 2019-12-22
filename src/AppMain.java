
import java.util.List;
import java.util.Scanner;


/**
 * AppMain for launch a graph program
 *
 */
public class AppMain {

    /***
     * Function who launch the interface to create or update a graph in command line
     */
    public static void commandLine() {
        System.out.println("Welcome on GraphSoft !");

        while(true) {

            System.out.println("Choose an action :");
            System.out.println("1- Load a graph with dot file.");
            System.out.println("2- Create an empty graph.");
            System.out.println("3- Create a random graph.");
            System.out.println("0- Quit.");
            Scanner sc = new Scanner(System.in);
            String menu1 = sc.nextLine();

            Graf g = new Graf();
            UndirectedGraf gu = new UndirectedGraf();

            boolean isWeighted = false;
            boolean isUndirected = false;

            switch (Integer.parseInt(menu1)) {
                case 1 : {
                    System.out.println("Load a graph with dot file.");
                    System.out.println("Enter the path of the dot file to open.");
                    String path = sc.nextLine();
                    g = Graf.DotFileToGraph(path);
                }
                break;
                case 2 : {
                    System.out.println("Create an empty graph.");
                    System.out.println("1- Empty directed graph.");
                    System.out.println("2- Empty undirected graph.");
                    System.out.println("3- Empty directed weighted graph.");
                    System.out.println("4- Empty undirected weighted graph.");
                    Scanner sc1 = new Scanner(System.in);
                    String menuG = sc1.nextLine();

                    switch(Integer.parseInt(menuG)) {
                        case 1 : {
                            System.out.println("Empty directed graph.");
                        }
                        break;
                        case 2 : {
                            System.out.println("Empty undirected graph.");
                            isUndirected = true;
                        }
                        break;
                        case 3 : {
                            System.out.println("Empty directed weighted graph.");
                            isWeighted = true;
                        }
                        break;
                        case 4 : {
                            System.out.println("Empty undirected weighted graph.");
                            isWeighted = true;
                            isUndirected = true;
                        }
                        break;
                    }
                }
                break;
                case 3 : {
                    System.out.println("Create a random graph.");
                    System.out.println("1- Connected graph.");
                    System.out.println("2- Dense graph.");
                    System.out.println("3- Sparse graph.");
                    System.out.println("4- Parmaterized graph.");
                    System.out.println("5- DAG (Directed Acyclic Graph.");
                    Scanner sc1 = new Scanner(System.in);
                    String menuRandom = sc1.nextLine();

                    switch(Integer.parseInt(menuRandom)) {
                        case 1 : {
                            System.out.println("Connected graph :");
                            g = Graf.connectedGraph();
                        }
                        break;
                        case 2 : {
                            System.out.println("Dense graph :");
                            g = Graf.denseGraph();
                        }
                        break;
                        case 3 : {
                            System.out.println("Sparse graph :");
                            g = Graf.sparseGraph();
                        }
                        break;
                        case 4 : {
                            System.out.println("Parameterized graph :");
                            Scanner scParam = new Scanner(System.in);
                            System.out.println("Number of node : ");
                            int v1 = Integer.parseInt(scParam.nextLine());
                            System.out.println("Number of edge : ");
                            int v2 = Integer.parseInt(scParam.nextLine());
                            System.out.println("Edge probability distribution : ");
                            int v3 = Integer.parseInt(scParam.nextLine());
                            g = Graf.parameterizedGraph(v1, v2, v3);
                        }
                        break;
                        case 5 : {
                            g = Graf.DAG();
                        }
                        break;
                        case 0: {
                            System.out.println("See you soon !");
                            Runtime.getRuntime().exit(0);
                        }
                        break;
                        default: {
                            System.out.println("Error, this option is not configure");
                        }
                    }

                }
                break;
                case 0: {
                    System.out.println("See you soon !");
                    Runtime.getRuntime().exit(0);
                }
                break;
                default: {
                    System.out.println("Error, this option is not configure");
                }
            }

            while (true) {
                System.out.println("Choose an action :");
                System.out.println("1- Add a node.");
                System.out.println("2- Remove node.");
                System.out.println("3- Add an edge.");
                System.out.println("4- Remove an edge.");
                System.out.println("5- Get successor of one node.");
                System.out.println("6- Get out edges of one node.");
                System.out.println("7- Get in edges of one node.");
                System.out.println("8- Get incident edges of one node.");

                System.out.println("9- Show the graph in the DOT format.");
                System.out.println("10- Export the graph in the DOT format.");
                System.out.println("11- Reverse the graph.");
                System.out.println("12- Compute the transitive closure of the graph.");
                System.out.println("13- Traverse the graph in DSF.");
                System.out.println("14- Traverse the graph in BSF.");
                System.out.println("15- Show the nodes of graph.");
                System.out.println("16- Show the edges of graph.");
                System.out.println("17- Generate a PDF image of the graph with dot file.");
                System.out.println("18- Get the adjacency matrix of the graph.");
                System.out.println("19- Return in main menu");
                System.out.println("0- Quit.");
                String menu2 = sc.nextLine();
                String value1 = "";
                String value2 = "";
                boolean retMMenu = false;
                switch (Integer.parseInt(menu2)) {
                    case 1: {
                        System.out.println("Add a node.");
                        System.out.println("Number of node :");
                        value1 = sc.nextLine();
                        System.out.println("Name of node. (empty, if unamed)");
                        value2 = sc.nextLine();
                        Node n = new Node(value2, Integer.parseInt(value1));
                        if (isUndirected) {
                            gu.addNode(n);
                        } else {
                            g.addNode(n);
                        }
                        System.out.println("Node (" + value1 + ", " + value2 + ") is added");
                    }
                    break;
                    case 2: {
                        System.out.println("Remove node.");
                        System.out.println("Number of node : ");
                        value1 = sc.nextLine();
                        if (isUndirected) {
                            gu.removeNode(new Node(Integer.parseInt(value1)));
                        } else {
                            g.removeNode(new Node(Integer.parseInt(value1)));
                        }
                    }
                    break;
                    case 3: {
                        System.out.println("Add an edge.");
                        System.out.println("Number of node from : ");
                        int v1 = Integer.parseInt(sc.nextLine());
                        System.out.println("Number of node to : ");
                        int v2 = Integer.parseInt(sc.nextLine());
                        if(isUndirected) {
                            System.out.println("The weighted of this edge : ");
                            int w = Integer.parseInt(sc.nextLine());
                            gu.addEdge(new Node(v1), new Node(v2), w);
                        } else {
                            gu.addEdge(new Node(v1), new Node(v2));
                        }
                        if(isWeighted) {
                            System.out.println("The weighted of this edge : ");
                            int w = Integer.parseInt(sc.nextLine());
                            g.addEdge(new Node(v1), new Node(v2), w);
                        } else {
                            g.addEdge(new Node(v1), new Node(v2));
                        }
                    }
                    break;
                    case 4: {
                        System.out.println("Remove an edge.");
                        System.out.println("Number of node from :");
                        value1 = sc.nextLine();
                        System.out.println("Number of node to :");
                        value2 = sc.nextLine();
                        g.removeEdge(new Node(Integer.parseInt(value1)), new Node(Integer.parseInt(value2)));
                        if (isUndirected) {
                            gu.removeEdge(new Node(Integer.parseInt(value1)), new Node(Integer.parseInt(value2)));
                        } else {
                            g.removeEdge(new Node(Integer.parseInt(value1)), new Node(Integer.parseInt(value2)));
                        }
                    }
                    break;
                    case 5: {
                        System.out.println("Get successor of one node.");
                        System.out.println("The successor of node :");
                        value1 = sc.nextLine();
                        if (isUndirected) {
                            printList(gu.getSuccessors(new Node(Integer.parseInt(value1))));
                        } else {
                            printList(g.getSuccessors(new Node(Integer.parseInt(value1))));
                        }
                    }
                    break;
                    case 6: {
                        System.out.println("Get out edges of one node.");
                        System.out.println("The out edges of node :");
                        value1 = sc.nextLine();
                        if (isUndirected) {
                            printList(gu.getOutEdge(new Node(Integer.parseInt(value1))));
                        } else {
                            printList(g.getOutEdge(new Node(Integer.parseInt(value1))));
                        }
                    }
                    break;
                    case 7: {
                        System.out.println("Get in edges of one node.");
                        System.out.println("The in edges of node :");
                        value1 = sc.nextLine();
                        if (isUndirected) {
                            printList(gu.getInEdge(new Node(Integer.parseInt(value1))));
                        } else {
                            printList(g.getInEdge(new Node(Integer.parseInt(value1))));
                        }
                    }
                    break;
                    case 8: {
                        System.out.println("Get incident edges of one node.");
                        System.out.println("The incident edges of node :");
                        value1 = sc.nextLine();
                        if (isUndirected) {
                            printList(gu.getIncidentEdges(new Node(Integer.parseInt(value1))));
                        } else {
                            printList(g.getIncidentEdges(new Node(Integer.parseInt(value1))));
                        }
                    }
                    break;
                    case 9: {
                        System.out.println("Show the graph in the DOT format.");
                        if (isUndirected) {
                            System.out.println(gu.toDotString());
                        } else {
                            System.out.println(g.toDotString());
                        }
                    }
                    break;
                    case 10: {
                        System.out.println("Export the graph to a DOT file.");
                        if (isUndirected) {
                            gu.toDotFile();
                        } else {
                            g.toDotFile();
                        }
                    }
                    break;
                    case 11: {
                        System.out.println("Reverse the graph.");
                        if (!isUndirected) {
                            g = g.getReverseGraph();
                        }
                    }
                    break;
                    case 12: {
                        System.out.println("Compute the transitive closure of the graph.");
                        if (!isUndirected) {
                            g = g.getReverseGraph();
                        }
                    }
                    break;
                    case 13: {
                        System.out.println("Traverse the graph in DSF.");
                        System.out.println("Node to start the DSF : ");
                        value1 = sc.nextLine();
                        if (isUndirected) {
                            List<Node> dfs = gu.getDFS(new Node(Integer.parseInt(value1)));
                            printList(dfs);
                        } else {
                            List<Node> dfs = g.getDFS(new Node(Integer.parseInt(value1)));
                            printList(dfs);
                        }
                    }
                    break;
                    case 14: {
                        System.out.println("Traverse the graph in BSF.");
                        System.out.println("Node to start the BSF : ");
                        value1 = sc.nextLine();
                        if (isUndirected) {
                            List<Node> bfs = gu.getBFS(new Node(Integer.parseInt(value1)));
                            printList(bfs);
                        } else {
                            List<Node> bfs = g.getBFS(new Node(Integer.parseInt(value1)));
                            printList(bfs);
                        }
                    }
                    break;
                    case 15: {
                        System.out.println("Show the nodes of graph.");
                        if (isUndirected) {
                            printList(gu.getAllNodes());
                        } else {
                            printList(g.getAllNodes());
                        }
                    }
                    break;
                    case 16: {
                        System.out.println("Show the edges of graph.");
                        if (isUndirected) {
                            printList(gu.getAllEdges());
                        } else {
                            printList(g.getAllEdges());
                        }
                    }
                    break;
                    case 17: {
                        System.out.println("Generate a PDF image of the graph with dot file.");
                        if (isUndirected) {
                            gu.DotFileToPDFImage();
                        } else {
                            g.DotFileToPDFImage();
                        }
                    }
                    break;
                    case 18: {
                        System.out.println("The adjacency matrix of the graph.");
                        if (isUndirected) {
                            int[][] adjMatrix = gu.getAdjMatrix();
                            int lgtTabSA = adjMatrix.length;
                            for (int i = 0; i < lgtTabSA; i++) {
                                for (int j = 0; j < lgtTabSA; j++) {
                                    System.out.print(adjMatrix[i][j] + " ");
                                }
                                System.out.println("");
                            }
                        } else {
                            int[][] adjMatrix = g.getAdjMatrix();
                            int lgtTabSA = adjMatrix.length;
                            for (int i = 0; i < lgtTabSA; i++) {
                                for (int j = 0; j < lgtTabSA; j++) {
                                    System.out.print(adjMatrix[i][j] + " ");
                                }
                                System.out.println("");
                            }
                        }
                    }
                    break;
                    case 19: {
                        System.out.println("Return in main menu.");
                        retMMenu = true;
                    }
                    break;
                    case 0: {
                        System.out.println("See you soon !");
                        Runtime.getRuntime().exit(0);
                    }
                    break;
                    default: {
                        System.out.println("Error, this option is not configure");
                    }
                }
                if (retMMenu) {
                    break;
                }
            }
        }
    }

    /***
     * Function who print a list
     *
     * @param l list printed
     */
    public static void printList(List l) {
        int lsize = l.size();
        for (int i = 0; i < lsize; i++) {
            String list = l.get(i).toString();
            System.out.println(list);
        }
    }



    public static void main( String[] args ) {
        commandLine();
    }


}
