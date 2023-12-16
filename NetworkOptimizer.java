import java.util.*;

// ENTRYWAY
public class NetworkOptimizer {
    public static void main(String[] args) {
        Graph network = new Graph();

        network.addDevice("Router1", "Main Router");
        network.addDevice("Server", "Remote Server");
        network.addDevice("Router2", "Secondary Router");
        network.addDevice("WAP", "Wireless Access Point");
        network.addDevice("PC1", "Alice's Desktop");
        network.addDevice("PC2", "Desktop PC 2");
        network.addDevice("SmartPhone1", "Bob's iPhone");
        network.addDevice("Laptop1", "Bob's Laptop");

        network.addConnection("Router1", "Server", 100);
        network.addConnection("Router1", "Router2", 5);
        network.addConnection("Router1", "WAP", 5);
        network.addConnection("Router2", "PC1", 4);
        network.addConnection("Router2", "PC2", 3);
        network.addConnection("WAP", "SmartPhone1", 1);
        network.addConnection("WAP", "Laptop1", 2);

        Scanner scanner = new Scanner(System.in);

        // display available devices
        System.out.println("Available Devices: " + network.getDevices());

        // input source device
        System.out.print("Enter source device: ");
        String sourceDevice = scanner.nextLine();

        // Dijkstra's algorithm
        Map<String, Integer> optimizedPaths = network.dijkstra(sourceDevice);

        // display the optimized paths
        System.out.println("\nOptimized Paths from " + sourceDevice + ":");
        for (Map.Entry<String, Integer> entry : optimizedPaths.entrySet()) {
            System.out.println("To: " + entry.getKey() + ", Cost: " + entry.getValue());
        }

        // input key for B-tree search
        System.out.print("\nEnter a key for B-tree search: ");
        String bTreeKey = scanner.nextLine();

        // display the description from B-tree search
        String description = network.getDescription(sourceDevice, bTreeKey);
        System.out.println("\nDescription for key '" + bTreeKey + "': " + description);

        scanner.close();
    }
}