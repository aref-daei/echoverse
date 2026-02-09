package util;

public class ShortestPath {
    private final int V; // تعداد نودهای گراف

    public ShortestPath(int V) {
        this.V = V;
    }

    public int[] dijkstra(int[][] graph, int src) {
        // آرایه فاصله هر نود از مبدا
        int[] dist = new int[V];

        // مشخص می‌کند نود بررسی شده یا نه
        Boolean[] sptSet = new Boolean[V];

        // مقداردهی اولیه فاصله‌ها
        for (int i = 0; i < V; i++) {
            dist[i] = Integer.MAX_VALUE;
            sptSet[i] = false;
        }

        dist[src] = 0;

        // اجرای الگوریتم
        for (int count = 0; count < V - 1; count++) {
            int u = minDistance(dist, sptSet);

            sptSet[u] = true;

            for (int v = 0; v < V; v++)

                if (!sptSet[v] && graph[u][v] != 0 &&
                        dist[u] != Integer.MAX_VALUE &&
                        dist[u] + graph[u][v] < dist[v])
                    dist[v] = dist[u] + graph[u][v];
        }

        // برگرداندن فاصله‌ها
        return dist;
    }

    // متد برای پیدا کردن نودی که کمترین فاصله را دارد
    private int minDistance(int[] dist, Boolean[] sptSet) {
        int min = Integer.MAX_VALUE, min_index = -1;

        for (int v = 0; v < V; v++)
            if (sptSet[v] == false && dist[v] <= min) {
                min = dist[v];
                min_index = v;
            }

        return min_index;
    }
}
