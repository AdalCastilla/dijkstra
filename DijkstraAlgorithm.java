import java.util.Arrays;

public class DijkstraAlgorithm {
    //Inicializar nuestro inifnito que en este caso seria Integer.MAX_VALUE se utiliza comunmente para representar el valor positivo más grande que puede almacenar un entero
    private static final int INFINITY = Integer.MAX_VALUE;

    public static void dijkstra(int[][] graph, int start) {
        int vertices = graph.length;
        int[] distances = new int[vertices];
        boolean[] visited = new boolean[vertices];
        // Inicializar distancias y visitados
        Arrays.fill(distances, INFINITY);// Todas las distancias se inicializan a INFINITY
        distances[start] = 0;// La distancia desde el nodo de inicio a sí mismo es 0
        //Iterar sobre todos los nodos
        for (int i = 0; i < vertices - 1; i++) {
            // Encontrar el nodo con la distancia mínima no visitada
            int minVertex = findMinDistanceVertex(distances, visited);
            visited[minVertex] = true;// Marcar el nodo como visitado
            // Actualizar las distancias de los nodos vecinos no visitados
            for (int j = 0; j < vertices; j++) {
                if (!visited[j] && graph[minVertex][j] != 0 &&
                        distances[minVertex] != INFINITY &&
                        distances[minVertex] + graph[minVertex][j] < distances[j]) {
                    distances[j] = distances[minVertex] + graph[minVertex][j];
                }
            }
        }
        //Imprimir el resultado
        printResult(distances, start);
    }
    // Método para encontrar el nodo con la distancia mínima no visitada
    private static int findMinDistanceVertex(int[] distances, boolean[] visited) {
        int minDistance = INFINITY;
        int minVertex = -1;

        for (int i = 0; i < distances.length; i++) {
            if (!visited[i] && distances[i] < minDistance) {
                minDistance = distances[i];
                minVertex = i;
            }
        }

        return minVertex;
    }
    // Método para imprimir el resultado
    private static void printResult(int[] distances, int start) {
        System.out.println("Distancias desde el punto de inicio " + start + ":");
        for (int i = 0; i < distances.length; i++) {
            System.out.println( i + "-> " + distances[i]);
        }
    }
    // Método principal
    public static void main(String[] args) {
        int[][] graph = {
            {0, 4, 0, 0, 0, 0, 0, 8, 0},
            {4, 0, 8, 0, 0, 0, 0, 11, 0},
            {0, 8, 0, 7, 0, 4, 0, 0, 2},
            {0, 0, 7, 0, 9, 14, 0, 0, 0},
            {0, 0, 0, 9, 0, 10, 0, 0, 0},
            {0, 0, 4, 14, 10, 0, 2, 0, 0},
            {0, 0, 0, 0, 0, 2, 0, 1, 6},
            {8, 11, 0, 0, 0, 0, 1, 0, 7},
            {0, 0, 2, 0, 0, 0, 6, 7, 0}
        };

        int startVertex = 0;
        dijkstra(graph, startVertex);
    }
}
