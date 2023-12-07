import java.util.*;

public class NetworkOptimizer {
    public static void main(String[] args) {
        Graph network = new Graph();

        // Adding devices to the network
        network.addDevice("Router1");
        network.addDevice("Router2");
        network.addDevice("Server1");

        // Adding connections with weights (metrics)
        network.addConnection("Router1", "Router2", 5);
        network.addConnection("Router2", "Server1", 3);

        // Performing Dijkstra's algorithm from Router1
        Map<String, Integer> optimizedPaths = network.dijkstra("Router1");

        // Displaying the optimized paths
        System.out.println("Optimized Paths from Router1:");
        for (Map.Entry<String, Integer> entry : optimizedPaths.entrySet()) {
            System.out.println("To: " + entry.getKey() + ", Cost: " + entry.getValue());
        }
    }
}