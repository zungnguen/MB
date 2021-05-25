import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;

public class Graph {
    private static final String[] VERTEX_LIST = {"A", "B", "C", "D", "E"};
    private final List<Edge>[] adj;
    private List<String> allPath;
    private int to;
    private int maxDistance;
    private int stops;
    private int routesCount;
    private int tripsCount;
    
    private Graph(int n) {
       if (n < 0) throw new IllegalArgumentException("Number of vertices in a Graph must be positive");
        adj = (LinkedList<Edge>[]) new LinkedList[n];
        for (int i = 0; i < n; i++)
            adj[i] = new LinkedList<>();
    }
    
    public Graph(String inputGraph) {
        this(VERTEX_LIST.length);
        initializeGraph(inputGraph);
    }
    
    private void initializeGraph(String inputGraph){
        String[] inputArr = inputGraph.split(",");
        for (String s : inputArr) {
            s = s.trim();
            int from = getIndex(s.substring(0, 1));
            int to = getIndex(s.substring(1, 2));
            int weight = Integer.parseInt(s.substring(2));
            Edge e = new Edge(from, to, weight);
            addEdge(e);
        }
    }

    private void addEdge(Edge e) {
        int v = e.from();
        adj[v].add(e);
    }

    private List<Edge> adj(int v) {
        if (v < 0 || v >= VERTEX_LIST.length) throw new IndexOutOfBoundsException("vertex " + v + " not exists");
        return adj[v];
    }

    public String displayDistance(String route){
        int distance = calculateDistance(route);
        return (distance != -1) ? String.valueOf(distance) : "NO SUCH ROUTE";
    }
    
    private int calculateDistance(String route){
        if(route == null) {
        System.out.println("Route is null");
        }
        int distance = 0;
        String[] vertex = route.trim().split("");
        int from, to;
        
        for (int i = 0; i < vertex.length-1;) {
            boolean hasPath = false;
            from = getIndex(vertex[i++]);
            to = getIndex(vertex[i]);
            List<Edge> edgeList = adj(from);
            for (Edge edge : edgeList) 
                if (edge.to() == to) {
                    distance += edge.weight();
                    hasPath = true;
                    break;
                }
            if(!hasPath) return -1;
        }
        
        return distance;
    }
    
    
    public int calculateTripsCount(String from, String to, Predicate<Integer> p, int stops){
        this.to = getIndex(to);
        this.stops = stops;
        this.tripsCount = 0;
        int startIndex = getIndex(from);
        calculateTripsCount(startIndex, String.valueOf(startIndex), p);
        
        return tripsCount;
    }
    
    private void calculateTripsCount(int from, String path, Predicate<Integer> p) {
        List<Edge> edges = adj(from);
        for (Edge e: edges) {
            
            String next = path + e.to();
            int stopCount = next.length()-1;
            
            if (this.to == e.to() && p.test(stopCount)) 
                tripsCount++;
            
            if(stopCount <= stops)
                calculateTripsCount(e.to(), next, p);
        }
    }
    
    
    public int calculateShortestPath(String from, String to){
        allPath = new ArrayList<>();
        this.to = getIndex(to);
        int startIndex = getIndex(from);
        calculateShortestPath(startIndex, String.valueOf(startIndex));
        
        int shortestDistance = Integer.MAX_VALUE, currentDistance;
        for(String s: allPath){
            currentDistance = calculateDistance(s);
            if(shortestDistance > currentDistance)
                shortestDistance = currentDistance;
        }
        
        if(shortestDistance == Integer.MAX_VALUE) return 0;
        
        return shortestDistance;
    }
    
    private void calculateShortestPath(int from, String path) {
        List<Edge> edges = adj(from);
        for (Edge e: edges) {
            
            if (path.length()>1 && path.substring(1).contains(String.valueOf(e.to()))) //checked visited or not
                continue;  
            
            String next = path + e.to();

            if (this.to == e.to()) 
                allPath.add(getPathName(next)); 
            
            calculateShortestPath(e.to(), next);
        }
    }


    public int calculateRoutesCount(String from, String to, int maxDistance){
        this.to = getIndex(to);
        this.maxDistance = maxDistance;
        this.routesCount = 0;
        int startIndex = getIndex(from);
        calculateRoutesCount(startIndex, String.valueOf(startIndex));
        
        return routesCount;
    }
    
    private void calculateRoutesCount(int from, String path) {
        List<Edge> edges = adj(from);
        for (Edge e: edges) {
            
            String next = path + e.to();
            int distance = calculateDistance(getPathName(next));
            
            if (this.to == e.to() && (distance < maxDistance)) 
                routesCount++;
            
            if(distance < maxDistance)
                calculateRoutesCount(e.to(), next);
        }
    }
      
    
    private static int getIndex(String vertex) {
        int index = Arrays.binarySearch(VERTEX_LIST, vertex);
        if (index < 0) 
            System.out.println("Wrong input");

        return index;
    }
    
    private String getVertexName(int index) {
        if (index < 0 || index >= VERTEX_LIST.length) 
            System.out.println("Wrong index");

        return VERTEX_LIST[index];
    }
    
    private String getPathName(String path){
        String arr[] = path.trim().split("");
        String name = "";
        for(String v: arr)
            name += getVertexName(Integer.parseInt(v));
        
        return name;
    }
}