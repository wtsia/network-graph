import java.util.*;

class Graph {
    private Map<String, Map<String, Integer>> adjacencyList;

    public Graph() {
        this.adjacencyList = new HashMap<>();
    }

    public void addDevice(String device) {
        adjacencyList.put(device, new HashMap<>());
    }

    public Set<String> getDevices() {
        return adjacencyList.keySet();
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
}