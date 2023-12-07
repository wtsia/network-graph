import java.util.*;

public class AppConsole {
    public static void main(String[] args) {
        Graph network = new Graph();

        // Adding devices to the network
        network.addDevice("Router1");
        network.addDevice("Router2");
        network.addDevice("Server1");

        // Adding connections with weights (metrics)
        network.addConnection("Router1", "Router2", 5);
        network.addConnection("Router2", "Server1", 3);

        Scanner scanner = new Scanner(System.in);

        // Display available devices
        System.out.println("Available Devices: " + network.getDevices());

        // Input source device
        System.out.print("Enter source device: ");
        String sourceDevice = scanner.nextLine();

        // Perform Dijkstra's algorithm
        Map<String, Integer> optimizedPaths = network.dijkstra(sourceDevice);

        // Display the optimized paths
        System.out.println("\nOptimized Paths from " + sourceDevice + ":");
        for (Map.Entry<String, Integer> entry : optimizedPaths.entrySet()) {
            System.out.println("To: " + entry.getKey() + ", Cost: " + entry.getValue());
        }

        scanner.close();
    }
}