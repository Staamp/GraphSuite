/***
 * Class who define one worker
 */
public class workers {

    private int id;
    private Boolean disponible;
    private Node node;

    /***
     * Constructor of workers class
     *
     * @param id : the id of one worker
     * @param disponible : if the worker is available
     */
    public workers(int id, Boolean disponible) {
        this.id = id;
        this.disponible = disponible;
    }

    /***
     * Getter of node how the workers is
     *
     * @return the node
     */
    public Node getNode() {
        return node;
    }

    /***
     * Setter on the new node of work for one workers
     *
     * @param node : the new node
     */
    public void setNode(Node node) {
        this.node = node;
    }

    /***
     * Getter on the id of one worker
     *
     * @return the id of one worker
     */
    public int getId() {
        return id;
    }

    /***
     * Setter on the id of one worker
     *
     * @param id : the new id worker
     */
    public void setId(int id) {
        this.id = id;
    }

    /***
     * Getter one the availability of one worker
     *
     * @return the availability of one worker
     */
    public Boolean getDisponible() {
        return disponible;
    }

    /***
     * Setter the availability of one worker
     *
     * @param disponible : the new availability of one worker
     */
    public void setDisponible(Boolean disponible) {
        this.disponible = disponible;
    }

    /***
     * Function who return a string of one worker
     *
     * @return the information of workers in string format
     */
    @Override
    public String toString() {
        return "workers{" +
                "id=" + id +
                ", disponible=" + disponible +
                '}';
    }
}
