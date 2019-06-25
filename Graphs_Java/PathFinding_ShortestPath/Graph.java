/**
 * This is the file that contains a graph calss that includes several search
 * algorithms amd find the shortest path, including the bfs, dfs, dijkstra, and
 * a* algorithm. It has some
 * instance variables like a list of vertices, all edges, and result path
 * It also includes several helper methods
 * @Author Yuan Gao
 * @PID A15605474
 */
import java.util.*;

public class Graph {
    /**
     * This is the graph calss that includes several search
     * algorithms amd find the shortest path, including the bfs, dfs, dijkstra,
     * and
     * a* algorithm. It has some
     * instance variables like a list of vertices, all edges, and result path
     * It also includes several helper methods
     * @Author Yuan Gao
     * @PID A15605474
     */
    private ArrayList<Vertex> vertices;

    public ArrayList<Edge> getEdges() {
        return edges;
    }

    public void setEdges(ArrayList<Edge> edges) {
        this.edges = edges;
    }

    private ArrayList<Edge> edges;

    /**
     * Constructor for Graph
     */
    public Graph() {
        vertices = new ArrayList<>();
        edges = new ArrayList<>();
    }

    /**
     * Adds a vertex to the graph. Throws IllegalArgumentException
     * if given vertex
     * already exist in the graph.
     *
     * @param v vertex to be added to the graph
     * @throws IllegalArgumentException if two vertices with the same
     * name are added.
     */
    public void addVertex(Vertex v) throws IllegalArgumentException {
        //invalid input
        if(vertices.contains(v)){
            throw new IllegalArgumentException();
        }
        vertices.add(0, v);
    }

    /**
     * Gets a collection of all the vertices in the graph
     *
     * @return collection of all the vertices in the graph
     */
    public Collection<Vertex> getVertices() {
        return vertices;
    }

    /**
     * Gets the vertex object with the given name
     *
     * @param name name of the vertex object requested
     * @return vertex object associated with the name
     */
    public Vertex getVertex(String name) {
        //iterate all vertices
        for(Vertex v:vertices){
            if(v.getName().equals(name)){
                return v;
            }
        }
        return null;
    }

    /**
     * Adds a directed edge from vertex u to vertex v,
     * Throws IllegalArgumentException if one of
     * the vertex does not exist
     *
     * @param nameU name of vertex u
     * @param nameV name of vertex v
     * @param weight weight of the edge between vertex u and v
     * @throws IllegalArgumentException if one of the vertex
     * does not exist
     */
    public void addEdge(String nameU, String nameV, Double weight)
        throws IllegalArgumentException {
        //create two headers
        Vertex vU = getVertex(nameU);
        Vertex vV = getVertex(nameV);
        //invalid input
        if(vU==null || vV==null){
            throw new IllegalArgumentException();
        }
        //form a edge
        Edge toAdd = new Edge(vU, vV, weight);
        edges.add(toAdd);
        vU.addAdj(vV);
    }

    /**
     * Adds an undirected edge between vertex u and vertex v by
     * adding a directed
     * edge from u to v, then a directed edge from v to u
     *
     * @param nameU name of vertex u
     * @param nameV name of vertex v
     * @param weight  weight of the edge between vertex u and v
     */
    public void addUndirectedEdge(String nameU, String nameV,
                                  double weight) {
        if (getVertex(nameU) == null || getVertex(nameV) == null) {
            throw new IllegalArgumentException();
        }
        //add both head to become undirected
        addEdge(nameU, nameV, weight);
        addEdge(nameV, nameU, weight);
    }

    /**
     * Computes the euclidean distance between two points as described by
     * their
     * coordinates
     *
     * @param ux (double) x coordinate of point u
     * @param uy (double) y coordinate of point u
     * @param vx (double) x coordinate of point v
     * @param vy (double) y coordinate of point v
     * @return (double) distance between the two points
     */
    public double computeEuclideanDistance(double ux, double uy,
                                           double vx, double vy) {
        return Math.sqrt(Math.pow(ux-vx, 2)+Math.pow(uy-vy, 2));
    }

    /**
     * Calculates the euclidean distance for all edges in the
     * map using the
     * computeEuclideanCost method.
     */
    public void computeAllEuclideanDistances() {
        //iterating through all edges
        for(Edge e:edges){
            //setting each of them
            e.setDistance(computeEuclideanDistance(
                e.getSource().getX(), e.getSource().getY(),
                e.getTarget().getX(), e.getTarget().getY()
            ));
        }
    }

    /**
     * Helper method to reset all the vertices before doing
     * graph traversal algorithms
     */
    private void resetAllVertices() {
        for(Vertex v:vertices){
            v.setFrom(null);
        }
    }

    /**
     * Find the path from vertex with name s to vertex with
     * name t, using DFS
     *
     * @param s the name of the starting vertex
     * @param t the name of the targeting vertex
     */
    public void DFS(String s, String t) {
        //reset before start
        resetAllVertices();
        //store the tovisit
        Stack<Vertex> toVisite = new Stack<>();
        //store visited
        ArrayList<Vertex> visited = new ArrayList<>();
        //start by starting vertex
        Vertex beg = getVertex(s);
        Vertex end = getVertex(t);
        Vertex currV = beg;
        toVisite.push(currV);
        beg.setFrom(currV);

        //whiel the stack is not empty
        while (!toVisite.isEmpty()){
            currV = toVisite.pop();
            if(currV.equals(end)){
                break;
            }
            visited.add(currV);
            //find the adjancey and push them
            for (Vertex v : currV.getAdjacency()) {

                //don't visit same thing
                if(!visited.contains(v)) {
                    toVisite.push(v);
                    v.setFrom(currV);
                }
            }
        }


    }

    /**
     * Find the path from vertex with name s to vertex
     * with name t, using BFS
     *
     * @param s the name of the starting vertex
     * @param t the name of the targeting vertex
     */
    public void BFS(String s, String t) {
        //reset before start
        resetAllVertices();
        //store the tovisit
        Queue<Vertex> toVisit = new LinkedList<>();
        //start by starting vertex
        Vertex beg = getVertex(s);
        Vertex end = getVertex(t);
        Vertex currV = beg;
        toVisit.offer(currV);
        beg.setFrom(currV);
        //whiel the queue is not empty
        while (!toVisit.isEmpty()){
            currV = toVisit.remove();
            //early exit
            if(currV.equals(end)){
                break;
            }
            //go to the adjancy
            for (Vertex v : currV.getAdjacency()) {
                //if has benn setted, skip
                if(v.getFrom()==null) {
                    toVisit.offer(v);
                    v.setFrom(currV);
                }
            }
        }
    }

    /**
     * Helper class for Dijkstra and A*, used in priority queue
     */
    private class CostVertex implements Comparable<CostVertex> {
        /**
         * This is the helper class that stores a vertex with cost
         * so we can pop according the the value of the cost, which is vital
         * for the dijkstra and a*
         * @Author Yuan Gao
         */
        double cost;
        Vertex vertex;

        /**
         * Constuctor of the costvertex
         * @param cost the so far
         * @param vertex the vertex
         */
        public CostVertex(double cost, Vertex vertex) {
            this.cost = cost;
            this.vertex = vertex;
        }

        /**
         * compare to cost vertex by their cost
         * @param o anthor to compare
         * @return positive if this is larger, 0 if equal, negative if
         * not equal
         */
        public int compareTo(CostVertex o) {
            return Double.compare(cost, o.cost);
        }

        /**
         * The string representation of the class
         * @return string representation of the class
         */
        @Override
        public String toString(){
            return vertex.getName();
        }

    }

    /**
     * Find the shortest path from vertex with name s to vertex
     * with name t, using Dijkstra
     *
     * @param s the name of starting vertex
     * @param t the name of targeting vertex
     */
    public void Dijkstra(String s, String t) {
        //reset before start
        resetAllVertices();
        //store to visit
        PriorityQueue<CostVertex> toVisit = new PriorityQueue<>();
        //keeping track of teh cost of each vertex
        ArrayList<CostVertex> costStored = new ArrayList<>();
        //start at beggining
        Vertex beg = getVertex(s);
        Vertex end = getVertex(t);
        Vertex currV = beg;
        //add the first
        CostVertex currCostV = new CostVertex(0, currV);
        toVisit.offer(currCostV);
        costStored.add(currCostV);
        beg.setFrom(beg);

        //while the queeu is not empty
        while (!toVisit.isEmpty()){
            currV = toVisit.remove().vertex;
            //early exit
            if(currV.equals(end)){
                break;
            }
            //for teh adjancey
            for (Vertex v : currV.getAdjacency()) {
                //finding new cost
                double newCost = findEdge(currV, v).getDistance()
                    + findOldCostV(costStored, currV).cost;
                CostVertex oldCost = findOldCostV(costStored, v);
                //if not found or the cost is llarge, update the vertex
                if(v.getFrom()==null || oldCost.cost>newCost) {
                    CostVertex CostV = new CostVertex(newCost, v);
                    toVisit.offer(CostV);
                    v.setFrom(currV);
                    costStored.remove(oldCost);
                    //add the new cost
                    costStored.add(new CostVertex(newCost, v));
                }
            }
        }
    }

    /**
     * find the old cost(existing cost) of a certain vertex
     * @param cv an arraylist storing cost vertex
     * @param ev the vertex to find
     * @return the costvertex conating this ev
     */
    private CostVertex findOldCostV(ArrayList<CostVertex> cv, Vertex ev){
        for(CostVertex co:cv){
            //iterating all the costvertex
            if(co.vertex.equals(ev)){
                return co;
            }
        }
        return null;
    }
    /**
     * Helper method to calculate the h value in A*
     *
     * @param cur the current vertex being explored
     * @param goal the goal vertex to reach
     * @return the h value of cur and goal vertices
     */
    private double hValue(String cur, String goal) {
        //using eucleadean
        Vertex beg = getVertex(cur);
        Vertex end = getVertex(goal);
        return computeEuclideanDistance(beg.getX(),
            beg.getY(), end.getX(), end.getY());
    }

    /**
     * Find the path from vertex with name s to vertex with name t, using A*
     *
     * @param s the name of starting vertex
     * @param t the name of targeting vertex
     */
    public void AStar(String s, String t) {
        //rest before start
        resetAllVertices();
        //setting the tovisit priority queue
        PriorityQueue<CostVertex> toVisit = new PriorityQueue<>();
        //store all costvertex
        ArrayList<CostVertex> costStored = new ArrayList<>();
        //start from the first one
        Vertex beg = getVertex(s);
        Vertex end = getVertex(t);
        Vertex currV = beg;
        //adding the first one
        CostVertex currCostV = new CostVertex(0, currV);
        toVisit.offer(currCostV);
        costStored.add(currCostV);
        beg.setFrom(beg);

        //while the queeu is not empty
        while (!toVisit.isEmpty()){
            currV = toVisit.remove().vertex;
            //early exit
            if(currV.equals(end)){
                break;
            }
            for (Vertex v : currV.getAdjacency()) {
                //computing the new cost
                double newCost = findEdge(currV, v).getDistance()
                    + findOldCostV(costStored, currV).cost;
                CostVertex oldCost = findOldCostV(costStored, v);
                if(v.getFrom()==null || oldCost.cost>newCost) {
                    //adding the hvalue to make our algorithm proceed
                    //in the right direction
                    CostVertex CostV = new CostVertex(
                        newCost+ hValue(v.getName(), end.getName()), v);
                    toVisit.offer(CostV);
                    v.setFrom(currV);
                    costStored.remove(oldCost);
                    //add to the stored list
                    costStored.add(new CostVertex(newCost, v));
                }
            }
        }

    }

    /**
     * Find the edge between two vertex
     * @param start staring vertex
     * @param end endong vertex
     * @return the edge between two vertex
     */
    private Edge findEdge(Vertex start, Vertex end){
        for(Edge e:edges){
            //uterating through all of them
            if(e.getSource().equals(start) && e.getTarget().equals(end)){
                return e;
            }
        }
        return null;
    }

    /**
     * Returns a list of edges for a path from city s to city t.
     *
     * @param s starting city name
     * @param t ending city name
     * @return list of edges from s to t
     */
    public List<Edge> getPath(String s, String t) {
        Vertex beg = getVertex(s);
        Vertex end = getVertex(t);
        Vertex currV = end;
        //reconstructing path
        List<Edge> path = new ArrayList<>();
        while(!currV.equals(beg)){
            //System.out.println(currV+ " is from "+currV.getFrom());
            path.add(0,findEdge(currV.getFrom(),currV));
            currV = currV.getFrom();
        }
        return path;
    }

}
