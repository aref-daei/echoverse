package service;

import model.Episode;
import util.LinkedList;
import util.ShortestPath;

public class EpisodeRecommenderService {
    private static final int MAX_AFFINITY = 12; // 6 + 4 + 2

    private final LinkedList<Episode> episodes;
    private final int length;

    public EpisodeRecommenderService(LinkedList<Episode> episodes) {
        this.episodes = episodes;
        this.length = episodes.size();
    }

    public LinkedList<Episode> recommend(Episode source, int count) {
        LinkedList<Episode> recommendations = new LinkedList<>();

        int sourceIndex = episodes.indexOf(source);
        if (sourceIndex == -1) return recommendations;

        int[][] graph = buildCostGraph();

        ShortestPath sp = new ShortestPath(length);
        int[] dist = sp.dijkstra(graph, sourceIndex);

        int[] indices = new int[length];
        for (int i = 0; i < length; i++) {
            indices[i] = i;
        }

        for (int i = 0; i < length - 1; i++) {
            for (int j = 0; j < length - i - 1; j++) {
                if (dist[indices[j]] > dist[indices[j + 1]]) {
                    int temp = indices[j];
                    indices[j] = indices[j + 1];
                    indices[j + 1] = temp;
                }
            }
        }

        int added = 0;
        for (int i = 0; i < length && added < count; i++) {
            int idx = indices[i];

            if (idx == sourceIndex) continue;
            if (dist[idx] == Integer.MAX_VALUE) continue;

            recommendations.addLast(episodes.get(idx));
            added++;
        }

        return recommendations;
    }

    private int[][] buildCostGraph() {
        int[][] graph = new int[length][length];

        for (int i = 0; i < length; i++) {
            Episode ep1 = episodes.get(i);

            for (int j = 0; j < length; j++) {
                Episode ep2 = episodes.get(j);

                if (i == j) {
                    graph[i][j] = 0;
                    continue;
                }

                int affinity = calculateAffinity(ep1, ep2);

                if (affinity == 0) {
                    graph[i][j] = 0;
                } else {
                    graph[i][j] = MAX_AFFINITY - affinity; // cost
                }
            }
        }

        return graph;
    }

    private int calculateAffinity(Episode ep1, Episode ep2) {
        int affinity = 0;

        if (ep1.getGenre().equals(ep2.getGenre())) affinity += 6;
        if (ep1.getProductionTeam().equals(ep2.getProductionTeam())) affinity += 4;
        if (ep1.getLanguage().equals(ep2.getLanguage())) affinity += 2;

        return affinity;
    }
}
