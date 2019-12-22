import java.util.Objects;

/***
 * Node is the class who create and compare the Nodes
 */
public class Node {

    private int number;
    private String name;
    private int duration;
    private int early;
    private int late;
    private Boolean realized;
    private int timeWork;

    public int getTimeWork() {
        return timeWork;
    }

    public void setTimeWork(int timeWork) {
        this.timeWork = timeWork;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    /***
     * Getter on one task to determinate is the task is realized or not
     *
     * @return if the task is realized
     */
    public Boolean getRealized() {
        return realized;
    }

    /***
     * Setter on one task to affect status of her
     *
     * @param realized : the new state of the task
     */
    public void setRealized(Boolean realized) {
        this.realized = realized;
    }

    /***
     * Getter on the early start time of one node
     *
     * @return the early start time
     */
    public int getEarly() {
        return early;
    }

    /***
     * Setter on the early start time of one node
     *
     * @param early : the new early start time
     */
    public void setEarly(int early) {
        this.early = early;
    }

    /***
     * Getter on the late start time of one node
     *
     * @return the late start time
     */
    public int getLate() {
        return late;
    }

    /***
     * Setter on the late start time of one node
     *
     * @param late : the new late start time
     */
    public void setLate(int late) {
        this.late = late;
    }

    /***
     * Constructor who create a node with a name and a number
     *
     * @param name the name of one node
     * @param number the number of one node
     */
    public Node(String name, int number) {
        this.number = number;
        this.name = name;
    }

    /***
     * Default constructor
     * @param node
     */
    public Node(Node node) {
        this.number = -1;
        this.name = "";
    }

    /***
     * Constructor who create a node with a number and without a name
     *
     * @param number the number of one node
     */
    public Node(int number) {
        this.number = number;
    }

    /***
     * Setter on name of node
     *
     * @param name set the name of one node
     */
    public void setName(String name) {
        this.name = name;
    }

    /***
     * Setter on number of node
     *
     * @param number set the number of one node
     */
    public void setNumber(int number) {
        this.number = number;
    }

    /***
     * Getter on name of node
     *
     * @return the name of one node
     */
    public String getName() {
        return name;
    }

    /***
     * Getter on number of node
     *
     * @return the number of one node
     */
    public int getNumber() {
        return number;
    }

    /***
     * Function who return a string of one node
     *
     * @return one string of one node
     */
    @Override
    public String toString() {
        return "node{" + number + " [" + getEarly() + ", " + getLate() +"]}";
    }

    /***
     * Function who compare two nodes together
     *
     * @param obj  node to compare with an another
     * @return the result of the comparison
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) {
            return  false;
        }
        if (!(obj instanceof Node)) {
            return false;
        }
        Node n = (Node) obj;
        //return this.getNumber() == n.getNumber() && this.getName().equals(n.getName());
        return this.getNumber() == n.getNumber();
    }

    /***
     * Function who realize a hashcode of one object node
     *
     * @return the result of hashcode
     */
    @Override
    public int hashCode() {
        return Objects.hash(number, name);
    }
}
