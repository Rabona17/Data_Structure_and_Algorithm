import java.util.ArrayList;

public class Vertex {
    private final String name; // the name of this vertex
    private final int x; // the x coordinates of this vertex on map
    private final int y; // the y coordinates of this vertex on map
    private Vertex from;

    /**
     * Get where does ot from
     * @return where does ot from
     */
    public Vertex getFrom() {
        return from;
    }

    /**
     * set where it comes from
     * @param from where it comes from
     */
    public void setFrom(Vertex from) {
        this.from = from;
    }

    /**
     * get the adjancy of the vertex
     * @return the adjancy of the vertex
     */
    public ArrayList<Vertex> getAdjacency() {
        return adjacency;
    }

    /**
     * set the adjancey pf the vertex
     * @param adjacency the adjancey pf the vertex
     */
    public void setAdjacency(ArrayList<Vertex> adjacency) {
        this.adjacency = adjacency;
    }

    private ArrayList<Vertex> adjacency;

    /**
     * Constructor for the class
     * @param name teh name of the vertex
     * @param x x corrdinate
     * @param y y coordinate
     */
    public Vertex(String name, int x, int y) {
        //inituialing
        this.name = name;
        this.x = x;
        this.y = y;
        adjacency = new ArrayList<>();
    }

    /**
     * add an edge
     * @param e the edge to add
     */
    public void addAdj(Vertex v){
        adjacency.add(v);
    }

    /**
     * getter for the name
     * @return the name of the vertex
     */
    public String getName() {
        return name;
    }

    /**
     * getter for the x coordinate
     * @return the x corrdinate
     */
    public int getX() {
        return x;
    }

    /**
     * getter for the y coordinate
     * @return the y coordinate
     */
    public int getY() {
        return y;
    }

    /**
     * compute the hashcode of the obj
     * @return the hascode
     */
    @Override
    public int hashCode() {
        // we assume that each vertex has a unique name
        return name.hashCode();
    }


    /**
     * override the equals. We compare the name
     * @param o another to compare
     * @return true if the two vertices has same name,
     * false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null) {
            return false;
        }
        //if not an instance, surely false
        if (!(o instanceof Vertex)) {
            return false;
        }
        Vertex oVertex = (Vertex) o;

        //comparing the name
        return name.equals(oVertex.name) && x == oVertex.x && y == oVertex.y;
    }

    /**
     * String representation of the vertx
     * @return String representation of the vertx
     */
    public String toString() {
        return name + " (" + x + ", " + y + ")";
    }
}
