import java.util.*;

// SET
// PRIORITY QUEUE
// DIJKSTRA's

public class Graph {
    private Map<String, Map<String, Integer>> adjacencyList;
    private Map<String, BTree> bTrees;

    public Graph() {
        this.adjacencyList = new HashMap<>();
        this.bTrees = new HashMap<>();
    }

    public void addDevice(String device, String description) {
        adjacencyList.put(device, new HashMap<>());
        bTrees.put(device, new BTree(2)); // Assume a minimum degree of 2 for the B-tree
        bTrees.get(device).insert(device, description);
    }

    public void addConnection(String device1, String device2, int weight) {
        adjacencyList.get(device1).put(device2, weight);
        adjacencyList.get(device2).put(device1, weight);
    }

    public Map<String, Integer> dijkstra(String source) {
        Map<String, Integer> distances = new HashMap<>();
        Set<String> visited = new HashSet<>();

        PriorityQueue<String> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(distances::get));
        priorityQueue.add(source);
        distances.put(source, 0);

        while (!priorityQueue.isEmpty()) {
            String current = priorityQueue.poll();
            visited.add(current);

            for (Map.Entry<String, Integer> neighbor : adjacencyList.get(current).entrySet()) {
                String next = neighbor.getKey();
                int weight = neighbor.getValue();

                if (!visited.contains(next)) {
                    int newDistance = distances.get(current) + weight;

                    if (!distances.containsKey(next) || newDistance < distances.get(next)) {
                        distances.put(next, newDistance);
                        priorityQueue.add(next);
                    }
                }
            }
        }

        return distances;
    }

    public Set<String> getDevices() {
        return adjacencyList.keySet();
    }

    public String getDescription(String device, String key) {
        System.out.println("Device: " + device + " Key: " + key);
        return bTrees.get(device).search(key);
    }
}