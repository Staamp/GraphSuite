import java.util.HashMap;
import java.util.List;
import java.util.Map;

/***
 * Graph is the class who create nodes, edges and realize many manipulation on undirected graph
 */
public class UndirectedGraf extends Graf {

    public UndirectedGraf(Map<Node, List<Node>> adjList) {
        super(adjList);
    }

    public UndirectedGraf(int ... node) {
        this.adjList = new HashMap<Node, List<Node>>();
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

    @Override
    public void addNode(Node n) {
        super.addNode(n);
    }

    @Override
    public boolean existsNode(Node n) {
        return super.existsNode(n);
    }

    @Override
    public boolean existsEdge(Edge e) {
        return super.existsEdge(e);
    }

    @Override
    public void addEdge(Node n1, Node n2) {
        super.addEdge(n1, n2);
        super.addEdge(n2, n1);
    }

    @Override
    public void removeNode(Node n) {
        super.removeNode(n);
    }

    @Override
    public void removeEdge(Node n1, Node n2) {
        super.removeEdge(n1, n2);
    }

    @Override
    public List<Node> getSuccessors(Node n) {
        return super.getSuccessors(n);
    }

    @Override
    public List<Edge> getInEdge(Node n) {
        return super.getIncidentEdges(n);
    }

    @Override
    public List<Edge> getOutEdge(Node n) {
        return super.getIncidentEdges(n);
    }

    @Override
    public List<Edge> getIncidentEdges(Node n) {
        return super.getIncidentEdges(n);
    }

    @Override
    public int numberOfNode() {
        return super.numberOfNode();
    }

    @Override
    public int numberOfEdge() {
        return super.numberOfEdge();
    }

    @Override
    public List<Node> getAllNodes() {
        return super.getAllNodes();
    }

    @Override
    public List<Edge> getAllEdges() {
        return super.getAllEdges();
    }

    @Override
    public void printList(List l) {
        super.printList(l);
    }

    @Override
    public int[] getSuccessorArray() {
        return super.getSuccessorArray();
    }

    @Override
    public List<Edge> sortListOfEdge() {
        return super.sortListOfEdge();
    }

    @Override
    public int[][] getAdjMatrix() {
        return super.getAdjMatrix();
    }

    @Override
    public List<Node> getDFS(Node n) {
        return super.getDFS(n);
    }

    @Override
    public List<Node> getBFS(Node n) {
        return super.getBFS(n);
    }

    @Override
    public void toDotFile() {
        super.toDotFile();
    }

    @Override
    public void DotFileToPDFImage() {
        super.DotFileToPDFImage();
    }

    /***
     * Function who create a dot format of a graph given
     *
     * @return a string of a dot representation
     */
    public String toDotString() {
        sortMapNodeByKey();
        String dotStringGraph = "graph g {\n";

        for (Map.Entry<Node, List<Node>> entry : adjList.entrySet()) {
            int n = entry.getKey().getNumber();
            dotStringGraph += " " + n + ";\n";
        }

        for (Map.Entry<Node, List<Node>> entry : adjList.entrySet()) {
            int nFrom = entry.getKey().getNumber();
            for (Node nod : entry.getValue()) {
                int nto = nod.getNumber();
                dotStringGraph += " " + nFrom + " -- " + nto + ";\n";
            }
        }

        dotStringGraph += "}";
        return  dotStringGraph;
    }
}
