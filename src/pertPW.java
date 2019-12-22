import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.*;

/***
 * This class has various functions to perform operations on pert graph
 */
public class pertPW {

    private List<nodePert> listNode;
    List<Edge> ledge;
    Graf gReverse;
    Graf gNormal;

    /***
     * Constructor of class pertPW
     *
     * @param listNode one list of pert node
     */
    public pertPW(List<nodePert> listNode) {
        this.listNode = listNode;
        this.ledge = new ArrayList<Edge>();
        this.gReverse = new Graf();
        this.gNormal = new Graf();
    }

    /***
     * Constructor of class pertPW
     */
    public pertPW() {
        this.listNode = new ArrayList<>();
        this.ledge = new ArrayList<Edge>();
        this.gReverse = new Graf();
        this.gNormal = new Graf();
    }

    /***
     * Getter of the list node
     *
     * @return the list node
     */
    public List<nodePert> getListNode() {
        return listNode;
    }

    /***
     * Setter of the list node
     *
     * @param listNode : the list node
     */
    public void setListNode(List<nodePert> listNode) {
        this.listNode = listNode;
    }


    /***
     * Return the first worker available
     *
     * @param w : the list of worker
     * @return the worker available
     */
    public workers workersAvailable(List<workers> w) {
        int nbWorkers = w.size();
        for (int i = 0; i < nbWorkers; i++) {
            if(w.get(i).getDisponible()) {
                return w.get(i);
            }
        }
        return null;
    }

    /***
     * Return if the task is free
     *
     * @param n : the task for checking
     * @return if the task is free
     */
    public Boolean taskIsFree(Node n) {
        return n.getRealized();
    }

    /***
     * Check is the dependencies of one task is all free
     *
     * @param n : the task for checking her depencies
     * @return if the all task is free
     */
    public Boolean dependenciesIsFree(Node n) {
        List<Node> lnode = gNormal.adjList.get(n);
        int lnodeSize = lnode.size();
        for (int i = 0; i < lnodeSize; i++) {
            if (!lnode.get(i).getRealized()) {
                return false;
            }
        }
        return true;
    }

    /***
     * Return the all task available for working
     *
     * @param n : the current node for search the new task available
     * @return a list of task available
     */
    public List<Node> taskIsAvailable(Node n) {
        int lSize = ledge.size();
        List<Node> ret = new ArrayList<>();
        for (int i = 0; i < lSize; i++) {
            if (ledge.get(i).getFrom().getNumber() == n.getNumber()) {
                ret.add(ledge.get(i).getTo());
            }
        }
        return ret;
    }

    /***
     * Sort a list of edge
     * @param edges : the list to sort
     * @return the result of sort
     */
    public List<Edge> sortListOfEdge(List<Edge> edges) {
        List<Edge> listEdge = edges;
        Collections.sort(listEdge, new Comparator<Edge>() {
            @Override
            public int compare(Edge o1, Edge o2) {
                return o1.compareTo(o2);
            }
        });
        return listEdge;
    }

    /***
     * Strategy who explore graph and make works the workers in sort task available (1 to x)
     *
     * @param w : the number of workers
     */
    public void strategyNodToNode(List<workers> w) {
        List<Edge> e = sortListOfEdge(ledge);
        List<Node> n = new ArrayList<>();

        for (int i = 0; i < e.size(); i++) {
            //System.out.println(e.get(i));
            e.get(i).getFrom().setRealized(false);
            n.add(e.get(i).getFrom());
        }

        List<workers> workingWorkers = new ArrayList<>();
        List<Node> nodeRemove = new ArrayList<>();
        List<Node> workingNode = new ArrayList<>();
        n.get(0).setTimeWork(0);
        workingNode.add(n.get(0));
        workingWorkers.add(w.get(0));
        int duration = 0;
        int durationWork = 0;
        int currentNode = 0;
        while(true) {
            System.out.println("----------------------------------------------------------------------------------------------------" + workingNode.get(0).getDuration());
            if (n.get(n.size()-1).getRealized()) {
                System.out.println("THE END");
                break;
            }
            for (int i = 0; i < workingNode.size(); i++) {
                System.out.println("The task is working : " + workingNode.get(i) + ", with the workers : " + workingWorkers.get(i));
            }
            int wns = workingNode.size();
            for (int i = 0; i < wns; i++) {
                System.out.println(n.get(i));
                if (n.get(i).getTimeWork() <= 0) {
                    n.get(i).setRealized(true);
                    System.out.println("The task " + n.get(i) + "is now ended. " + workingWorkers.get(i) + " is now available.");
                    //workingNode.remove(n.get(i));
//                    i--;
                    wns--;
                    workingWorkers.get(i).setDisponible(true);
                }
            }

            List<Node> lnode =  taskAvailable(n, e);
            for (int i = 0; i < lnode.size(); i++) {
                workers ww = workersAvailable(w);
                if (ww != null) {
                    workingWorkers.add(ww);
                    ww.setDisponible(false);
                    lnode.get(i).setTimeWork(getTimeWorkTask(e, lnode.get(i)));
                    //System.out.println("Dura " + lnode.get(i).getDuration());
                    workingNode.add(lnode.get(i));
                } /*else {
                    System.out.println("Any workers is available.");
                }*/
            }
            duration++;
        }

    }


    public int getTimeWorkTask(List<Edge> l, Node n) {
        for (int i = 0; i < l.size(); i++) {
            if (l.get(i).getFrom().getNumber() == n.getNumber()) {
                return l.get(i).getWeighted();
            }
        }
        return 0;
    }

    public List<Node> taskAvailable(List<Node> n, List<Edge> e) {
        List<Node> lnode = new ArrayList<>();
        List<Edge> ledge = e;
        for (int i = 0; i < n.size(); i++) {
            if (n.get(i).getRealized()) {
                for (int j = 0; j < ledge.size(); j++) {
                    if (ledge.get(j).getFrom().getNumber() == n.get(i).getNumber()) {
                        lnode.add(ledge.get(j).getTo());
                    }
                }
            }
        }
        return lnode;
    }


    /***
     * Strategy who explore graph and make works the workers in priority in task of current task child
     *
     * @param w : the number of workers
     */
    public void strategyChildPriority(List<workers> w) {
        List<Node> criticalPath = getCriticalPath();
        for (int i = 0; i < criticalPath.size(); i++) {
            System.out.println(criticalPath.get(i) + " " +criticalPath.get(i).getDuration());
        }

        List<Node> n = gNormal.getAllNodes();
        for (int i = 0; i < n.size(); i++) {
            n.get(i).setRealized(false);
        }

        int nodeCriticalPathSize = criticalPath.size();
        List<workers> workingWorkers = new ArrayList<>();
        int duration = 0;
        int currNode = 0;
        int durationWork = 0;
        Node currentNode = criticalPath.get(0);

        while (duration < 300) {
            if (criticalPath.get(nodeCriticalPathSize-1).getRealized()) {
                break;
            }
            if(duration == criticalPath.get(currNode).getDuration()) {
                System.out.println("Task " + criticalPath.get(currNode).toString() + " is working in " + duration);
                criticalPath.get(currNode).setRealized(true);
                int workinsWokrersSize = workingWorkers.size();
                for (int i = 0; i < workinsWokrersSize; i++)  {
                    try {
                        if (workingWorkers.get(i).getNode().getNumber() == criticalPath.get(currNode).getNumber()) {
                            workers wRemove = workingWorkers.get(i);
                            wRemove.setDisponible(true);
                            workingWorkers.remove(wRemove);
                            System.out.println("The workers " + wRemove.toString() + " is available now.");
                        }
                    } catch (Exception e) {

                    }
                }
            }

            if (criticalPath.get(currNode).getRealized()) {

                System.out.println(" The task : " + criticalPath.get(currNode) + " is now ended.");
                currNode++;
                if(currNode == criticalPath.size()) {
                    break;
                }
                System.out.println(currentNode.toString());
                System.out.println("The next task is " + criticalPath.get(currNode).toString());
                criticalPath.get(currNode).setRealized(false);
            }

            List<Node> lTask = taskIsAvailable(criticalPath.get(currNode));
            int nvTask = lTask.size();
            if (nvTask != 0) {
                int max = 0;
                for (int i = 0; i < nvTask; i++) {
                    workers works = workersAvailable(w);
                    if (works != null) {
                        if (!dependenciesIsFree(lTask.get(i))) {
                            workingWorkers.add(works);
                            works.setNode(lTask.get(i));
                            works.setDisponible(false);
                            System.out.println("The workers " + works.toString() + " works on the task " + lTask.get(i).toString());
                            if (max < lTask.get(i).getDuration()) {
                                max = lTask.get(i).getDuration();
                            }
                        }
                    } else {
                        System.out.println("Any workers is available.");
                    }
                }
                durationWork += max;
            }
            duration++;
        }
        System.out.println("The working duration : " + duration + ".");
        for (int i = 0; i < w.size(); i++) {
            w.get(i).setDisponible(true);
        }
    }



    public void strategyTaskSoonOrOutdated(List<workers> w) {

    }




    /***
     * Fonction who calculated the critical path of one pert graph
     *
     * @return
     */
    public List<Node> getCriticalPath() {
        int duree = earlyStartTimes(gNormal);
        lateStartTimes(gReverse, duree);
        gNormal.sortMapNodeByKey();
        gReverse.sortMapNodeByKey();

        List<Node> res = new ArrayList<>();
        List<Node> alpha = gNormal.adjList.get(new Node(0));
        //res.add(new Node(0));
        return getCriticalPathRecursivity(new Node(0), res);
    }

    /***
     * Function who exporting the graph as a file in the DOT syntax
     *
     * @param name : the name of the dot file
     */
    public void toDotFile(String name) {
        String pg = "digraph G {\n";
        for (int i = 0; i < ledge.size(); i++) {
            pg += " " + ledge.get(i).getFrom().getNumber() + " -> " + ledge.get(i).getTo().getNumber() + " [label=" + ledge.get(i).getWeighted() + "];\n";
        }
        pg += "}";

        String pathOfFileOutput = System.getProperty("user.dir") + "/src/" + name + ".dot"; //Current directory
        try {
            File ff = new File(pathOfFileOutput);
            ff.createNewFile();
            FileWriter ffw = new FileWriter(ff);
            try {
                ffw.write(pg);
            } finally {
                ffw.close();
            }
        } catch (Exception e) {
            System.out.println("Error. Could not create file");
        }
    }

    /***
     * Create a PDF Image of a given graph with the DOT file
     *
     * @param name : the name of the pdf file
     */
    public void DotFileToPDFImage(String name) {
        toDotFile(name);
        try {
            String cmd = "dot -Tpdf src/" + name + ".dot -o src/" + name +".pdf";
            Runtime.getRuntime().exec(cmd);
        } catch (Exception e) {
            System.out.println("Error. Could not create the pdf image of the graph.");
        }
    }

    /***
     * Recursively function to determinate critical path
     *
     * @param n : one node on the graph
     * @param res : the result list
     * @return the list of the critical path
     */
    public List<Node> getCriticalPathRecursivity(Node n, List<Node> res) {
        for(Node o : gNormal.adjList.get(n)) {
            if(o.getEarly() == o.getLate()) {
                res.add(o);
                return getCriticalPathRecursivity(o, res);
            }
        }
        return res;
    }

    /***
     * Search the critical path of one graph
     *
     * @return the critical path
     */
    public List<Edge> getCriticalPath2() {
        int duree = earlyStartTimes(gNormal);
        lateStartTimes(gReverse, duree);
        gNormal.sortMapNodeByKey();
        gReverse.sortMapNodeByKey();

        List<Node> nodeCriticalPath = new ArrayList<>();
        for (Map.Entry<Node, List<Node>> entry : gNormal.adjList.entrySet()) {
            if(entry.getKey().getEarly() == entry.getKey().getLate()) {
                nodeCriticalPath.add(entry.getKey());
            }
        }

        List<Edge> edgeCriticalPath = new ArrayList<>();
        int lsize = nodeCriticalPath.size();
        for (int i = 0; i < lsize-1; i++) {
            Node n1 = nodeCriticalPath.get(i);
            Node n2 = nodeCriticalPath.get(i+1);
            int dist = distance(n1.getNumber(), n2.getNumber());
            Edge e = new Edge(n1, n2, dist);
            System.out.println(e.toString());
            if(dist != -1) {
                edgeCriticalPath.add(e);
            }
        }
        return edgeCriticalPath;
    }

    /***
     * Function who determinate the late start time of all node in graph
     *
     * @param g : the graph for determinate late start of node
     * @param durationConstruction : the duration construction of the graph
     */
    public void lateStartTimes(Graf g, int durationConstruction) {
        int V = g.adjList.size();
        int dist [] = new int[V];

        for (int i = 0; i < V ; i++) {
            dist[i] = Integer.MIN_VALUE;
        }
        dist[V-1] = 0;

        for (int i = V-1; i >= 0; i--) {
            List<Node> l =  g.adjList.get(new Node(i));
            int lsize = l.size();
            for (int j = 0; j < lsize; j++) {
                int distan = distance(l.get(j).getNumber(), i);
                if (dist[l.get(j).getNumber()] < dist[i] + distan) {
                    dist[l.get(j).getNumber()] = dist[i] + distan;
                }
            }
        }
        int distLgth = dist.length;

        gNormal.sortMapNodeByKey();
        int i = 0;
        for (Map.Entry<Node, List<Node>> entry : gNormal.adjList.entrySet()) {
            Node nodeFrom = entry.getKey();
            //System.out.print(durationConstruction - dist[i]+ " ");
            nodeFrom.setLate(durationConstruction - dist[i]);
            i++;
        }
    }

    /***
     * Function who determinate the early start time of all node in graph
     *
     * @param g : the graph for determinate early start of node
     * @return the duration of the graph
     */
    public int earlyStartTimes(Graf g) {
        int V = g.adjList.size();
        int dist [] = new int [V];

        for (int i = 0; i < V ; i++) {
            dist[i] = Integer.MIN_VALUE;
        }
        dist[0] = 0;

        for (int i = 0; i < V; i++) {
            List<Node> l =  g.adjList.get(new Node(i));
            int lsize = l.size();
            for (int j = 0; j < lsize; j++) {
                int distan = distance(i, l.get(j).getNumber());
                if (dist[l.get(j).getNumber()] < dist[i] + distan) {
                    dist[l.get(j).getNumber()] = dist[i] + distan;
                }
            }
        }
        int distLgth = dist.length;

        g.sortMapNodeByKey();
        int i = 0;
        for (Map.Entry<Node, List<Node>> entry : g.adjList.entrySet()) {
            Node nodeFrom = entry.getKey();
            nodeFrom.setEarly(dist[i]);
            i++;
        }
        return dist[distLgth-1];
    }

    /***
     * Function who return the weight of one graph
     *
     * @param n1 : the node from of one edge
     * @param n2 : the node to of one edge
     * @return the weight of one graph
     */
    public int distance(int n1, int n2) {
        int nbEdge = ledge.size();
        for (int i = 0; i < nbEdge; i++) {
            if (n1 == ledge.get(i).getFrom().getNumber() && n2 == ledge.get(i).getTo().getNumber() ) {
                return ledge.get(i).getWeighted();
            }
        }
        return -1;
    }

    /***
     * Function who transform a PW4 struct to a PW2 struct
     *
     * @return one graph in PW2 struct
     */
    public Graf pw4StructToPw2() {
        Graf g = new Graf();
        int listNodeSize = listNode.size();

        for (int i = 0; i < listNodeSize; i++) {
            Node n1 = new Node(i + 1);
            Node n = new Node(i + 1);
            g.addNode(n);
            int listEdgeSize = listNode.get(i).getDependencies().size();
            for (int j = 0; j < listEdgeSize; j++) {
                nodePert nString = listNode.get(i).getDependencies().get(j);
                n1.setDuration(getDuration(nString.getName()));
                Node n2 = new Node(charToInt(nString.getName()));
                g.addEdge(n1, n2, getDuration(nString.getName()));
            }
        }

        Node nAlpha = new Node(0);
        Node nDebut = new Node(1);
        g.addNode(nAlpha);
        g.addEdge(nDebut, nAlpha, 0);

        Node nBeta = new Node(listNodeSize +1);
        Node nFin = new Node(listNodeSize);
        g.addNode(nBeta);
        g.addEdge(nBeta, nFin, 1);




        gReverse = g;
        Graf gr = g.getReverseGraph();
        gNormal = gr;
        ledge = gr.ledge;
//        for (int i = 0; i < ledge.size(); i++) {
//            System.out.println(ledge.get(i) + "\n");
//        }
        return gr;
    }

    /***
     * Function for determinate the duration of one task
     *
     * @param node : the node for determinate the duration
     * @return the duration of one task
     */
    public int getDuration(String node) {
        int nbNode = listNode.size();
        for (int i = 0; i < nbNode; i++) {
            if (node.equals(listNode.get(i).getName())) {
                return listNode.get(i).getDuration();
            }
        }
        return -1;
    }

    /***
     * Transform a character to int
     *
     * @param s : the character
     * @return return the character in int format
     */
    public int charToInt(String s) {
        switch (s) {
            case "A" : {
                return 1;
            }
            case "B" : {
                return 2;
            }
            case "C" : {
                return 3;
            }
            case "D" : {
                return 4;
            }
            case "E" : {
                return 5;
            }
            case "F" : {
                return 6;
            }
            case "G" : {
                return 7;
            }
            case "H" : {
                return 8;
            }
            case "I" : {
                return 9;
            }
            case "J" : {
                return 10;
            }
            case "K" : {
                return 11;
            }
            case "L" : {
                return 12;
            }
            case "M" : {
                return 13;
            }
            case "N" : {
                return 14;
            }
            case "O" : {
                return 15;
            }
            case "P" : {
                return 16;
            }
            case "Q" : {
                return 17;
            }
            case "R" : {
                return 18;
            }
            case "S" : {
                return 19;
            }
            case "T" : {
                return 20;
            }
            case "U" : {
                return 21;
            }
            case "V" : {
                return 22;
            }
            case "W" : {
                return 23;
            }
            case "X" : {
                return 24;
            }
            case "Y" : {
                return 25;
            }
            case "Z" : {
                return 26;
            }
        }
        return -1;
    }

    /***
     * Function who read a fil and transform her in PW4 struct
     *
     * @param path : the path of the file
     */
    public void readFile(String path) {
        String p = System.getProperty("user.dir") + "/" + path;
        System.out.println(p);
        try {
            BufferedReader br = new BufferedReader(new FileReader(p));
            String line;
            while ((line = br.readLine()) != null) {
                if (line.substring(0, 1).equals("#")) {
                    continue; //car c'est un commentaire
                }

                String name = "";
                String label = "";
                int duration = -1;
                List<nodePert> dependencies = new ArrayList<nodePert>();

                String [] l  = line.split(" ");
                int lsize = l.length;

                for (int i = 0; i < lsize; i++) {
                    if (i == 0) {
                        String [] l1 = l[0].split(",");
                        name = l1[0];
                    } else if (i == 1) {
                        String [] l2 = l[1].split(",");
                        label = l2[0];
                    } else if (i == 2) {
                        String [] l3 = l[2].split(",");
                        duration = Integer.parseInt(l3[0]);
                    } else {
                        String [] ln = l[i].split(",");
                        if (ln[0].equals("-")) {
                            break;
                        }
                        int nbNodeSize = listNode.size();
                        for (int j = 0; j < nbNodeSize; j++) {
                            nodePert ns = listNode.get(j);
                            if (ns.getName().equals(ln[0])) {
                                dependencies.add(ns);
                            }
                        }
                    }
                }
                nodePert np = new nodePert(name, label, duration, dependencies);
                listNode.add(np);
            }
            br.close();
        } catch (Exception e) {
            System.out.println("Error. Could not open the txt file.");
        }
    }
}
