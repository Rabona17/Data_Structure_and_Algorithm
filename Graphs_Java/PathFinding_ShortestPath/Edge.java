/**
 * This file contains a edge class that has several attributes.
 * It has two vertices, representing source and target, and it
 * has its distance. It also provides getters and setters
 * @Author Yuan Gao
 * @PID A15605474
 */
public class Edge {
    /**
     * This is class that has several attributes.
     * It has two vertices, representing source and target, and it
     * has its distance. It also provides getters and setters
     * @Author Yuan Gao
     * @PID A15605474
     */
    private double distance; // the distance from source to target
    private Vertex source; // the source vertex this edge starts from
    private Vertex target; // the target vertex this edge ends at

    /**
     * Constructor for edge
     * @param v1 source of the edge
     * @param v2 target of the edge
     * @param weight the distance
     */
    public Edge(Vertex v1, Vertex v2, double weight) {
        source = v1;
        target = v2;
        this.distance = weight;
    }

    /**
     * Getters for the distance
     * @return teh distance of the edge
     */
    public double getDistance() {
        return distance;
    }

    /**
     * Setters for the distance
     * @param distance teh distance to set
     */
    public void setDistance(double distance) {
        this.distance = distance;
    }

    /**
     * Getters for the source of the edge
     * @return the source of the edge
     */
    public Vertex getSource() {
        return source;
    }

    /**
     * Getter for the target of the edge
     * @return the target of the edge
     */
    public Vertex getTarget() {
        return target;
    }

    /**
     * String representation of the edge
     * @return String representation of the edge
     */
    public String toString() {
        return source + " - " + target;
    }
}