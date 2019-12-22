import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * AppMainPW4 for launch a graph PW4 program
 *
 */
public class AppMainPW4 {

    public static void main(String[] args ) {
        pertPW pw = new pertPW();
                while(true) {
            System.out.println("Choose an action :");
            System.out.println("1- Read a construction description file");
            System.out.println("0- Quit.");
            Scanner sc = new Scanner(System.in);
            String menu1 = sc.nextLine();
            try {
                switch (Integer.parseInt(menu1)) {
                    case 1: {
                        System.out.println("\nLoad a graph with dot file.");
                        System.out.println("Enter the path of the construction description file to open.");
                        String path = sc.nextLine();
                        pw.readFile(path);
                        Graf g = pw.pw4StructToPw2();
                        menuApLectureFichier(pw, g);
                    }
                    break;
                    case 0: {
                        System.out.println("\nSee you soon !");
                        Runtime.getRuntime().exit(0);
                    }
                    break;
                    default: {
                        System.out.println("\nError, this option is not configure");
                    }
                }
            } catch (Exception e) {
                System.out.println("\nThe input isn't a number. Please try again.");
            }
        }
    }

    /***
     * Function who realise operatioon in the pert chart
     *
     * @param pw : the PW structure to access pert function
     * @param g : the graph in PW2 format
     */
    public static void menuApLectureFichier(pertPW pw, Graf g) {
        List<workers> listWorkers = new ArrayList<>();
        int nbWorkers = 0;
        while (true) {
            System.out.println("Choose an action :");
            System.out.println("1- Draw the corresponding graph.");
            System.out.println("2- Save the graph in the PW2format.");
            System.out.println("3- Compute and display the total duration of the construction.");
            System.out.println("4- Compute and display the earliest and latest start times of each task.");
            System.out.println("5- Compute and display the critical path(s).");
            System.out.println("6- Enter the number of workers.");
            System.out.println("7- Choose child priority strategy.");
            System.out.println("8- Choose random strategy.");
            System.out.println("9- Compute and display the construction's duration according the chosen strategy.");
            System.out.println("0- Quit.");
            Scanner sc = new Scanner(System.in);
            String menu2 = sc.nextLine();

            try {
                switch (Integer.parseInt(menu2)) {
                    case 1: {
                        System.out.println("\nThe draw of the graph. Enter a name : ");
                        String nameOfDotFile = sc.nextLine();
                        pw.DotFileToPDFImage(nameOfDotFile);
                    }
                    break;
                    case 2: {
                        System.out.println("\nSave the graph in the PW2 format. Enter a name :");
                        String nameOfDotFile = sc.nextLine();
                        pw.toDotFile(nameOfDotFile);
                    }
                    break;
                    case 3: {
                        System.out.println("\nCompute and display the total duration of the construction :");
                        int dist = pw.earlyStartTimes(g);
                        System.out.println("The duration of the construction : " + dist + ".");
                    }
                    break;
                    case 4: {
                        System.out.println("\nCompute and display the earliest and latest start times of each task : (format : node{n [early, late]})");
                        int dist = pw.earlyStartTimes(g);
                        pw.lateStartTimes(pw.gReverse, dist);
                        int nbNode = pw.gNormal.adjList.size();
                        List<Node> lnodes = pw.gNormal.getAllNodes();
                        for (int i = 0; i< nbNode; i++) {
                            System.out.println(lnodes.get(i).toString());
                        }
                    }
                    break;
                    case 5: {
                        System.out.println("\nCompute and display the critical path(s) :");
                        System.out.println("This is the critical path :");
                        List<Node> lnode = pw.getCriticalPath();
                        int lsize = lnode.size();
                        for (int i = 0; i < lsize; i++) {
                            System.out.println(lnode.get(i));
                        }
                    }
                    break;
                    case 6: {
                        listWorkers.clear();
                        System.out.println("\nEnter the numbers of workers :");
                        nbWorkers = Integer.parseInt(sc.nextLine());
                        while (nbWorkers < 1) {
                            System.out.println("\nThe number of workers entered is less than 1, but he must be better than 0. Please try again:");
                            nbWorkers = Integer.parseInt(sc.nextLine());
                        }
                        for (int i = 1; i <= nbWorkers; i++) {
                            workers w = new workers(i, true);
                            listWorkers.add(w);
                        }
                        System.out.println("The numbers of workers : " + nbWorkers + ".");
                    }
                    break;
                    case 7: {
                        System.out.println("\nYou choose child priority strategy :");
                        if (listWorkers.size() < 1) {
                            System.out.println("The number of workers isn't configure.");
                        } else {
                            pw.strategyChildPriority(listWorkers);
                        }
                    }
                    break;
                    case 8: {
                        System.out.println("\nYou choose random strategy :");
                        if (listWorkers.size() < 1) {
                            System.out.println("The number of workers isn't configure.");
                        } else {
                            pw.strategyNodToNode(listWorkers);
                        }
                    }
                    break;
                    case 9 : {
                        System.out.println("\nCompute and display the construction's duration according the chosen strategy :");
                    }
                    break;
                    case 0: {
                        System.out.println("\nSee you soon !");
                        Runtime.getRuntime().exit(0);
                    }
                    break;
                    default: {
                        System.out.println("\nError, this option is not configure");
                    }
                }
            } catch (Exception e) {
                System.out.println("\nThe input isn't a number. Please try again.");
            }
        }
    }
}
