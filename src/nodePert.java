import java.util.List;

/***
 * Structure of node pert to read one file, before translation on PW2 format
 */
public class nodePert {

    private String name;
    private String label;
    private int duration;
    private List<nodePert> dependencies;

    /***
     * Constructor of nodePert
     *
     * @param name : name of the node
     * @param label : label of the node
     * @param duration : duration of the task
     * @param dependencies : dependencies before realize this task
     */
    public nodePert(String name, String label, int duration, List<nodePert> dependencies) {
        this.name = name;
        this.label = label;
        this.duration = duration;
        this.dependencies = dependencies;
    }

    /***
     * Getter on the name of one task
     *
     * @return the name of one task
     */
    public String getName() {
        return name;
    }

    /***
     * Setter on the name of one task
     *
     * @param name : the new name of the task
     */
    public void setName(String name) {
        this.name = name;
    }

    /***
     * Getter on the label of the task
     *
     * @return the label of the task
     */
    public String getLabel() {
        return label;
    }

    /***
     * Setter on the label of the task
     *
     * @param label : the new label of the task
     */
    public void setLabel(String label) {
        this.label = label;
    }

    /***
     * Getter on the duration of one task
     * @return the duration of the task
     */
    public int getDuration() {
        return duration;
    }

    /***
     * Setter on the duration of one task
     *
     * @param duration : the new duration of the task
     */
    public void setDuration(int duration) {
        this.duration = duration;
    }

    /***
     * Getter on the dependencies of the task
     *
     * @return the dependencies of the task
     */
    public List<nodePert> getDependencies() {
        return dependencies;
    }

    /***
     * Setter of the dependencies of one task
     *
     * @param dependencies : the new dependencies of the task
     */
    public void setDependencies(List<nodePert> dependencies) {
        this.dependencies = dependencies;
    }

    /***
     * Function who add dependencies on one task
     *
     * @param np : the dependencies added
     */
    public void addDependencies(nodePert np) {
        this.dependencies.add(np);
    }

    /***
     * Function who remove dependencies on one task
     *
     * @param np : the dependencies removed
     */
    public void removeDependencies(nodePert np) {
        this.dependencies.remove(np);
    }

    /***
     * Function who return in string format the node pert
     *
     * @return the string created
     */
    @Override
    public String toString() {
        String ret = "nodePert{" +
                "name='" + name + '\'' +
                ", label='" + label + '\'' +
                ", duration=" + duration +
                ", dependencies=[";
        int depSize = dependencies.size();
        for (int i = 0; i < depSize; i++) {
            ret += dependencies.get(i).getName();
            if (i != depSize -1) {
                ret += ", ";
            }
        }
        ret += "]}";
        return ret;
    }
}
