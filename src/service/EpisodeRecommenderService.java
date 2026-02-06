package service;

import model.Episode;
import util.LinkedList;
import util.ShortestPath;

public class EpisodeRecommenderService {
    private final LinkedList<Episode> episodes;
    private final int length;
    private final int[][] affinities;

    public EpisodeRecommenderService(LinkedList<Episode> episodes) {
        this.episodes = episodes;
        this.length = episodes.size();
        this.affinities = new int[length][length];
    }

    public LinkedList<Episode> recommend(Episode episode, int n) {
        LinkedList<Episode> recEpisodes = new LinkedList<>();

        for (int i = 0; i < length - 1; i++) {
            Episode ep1 = episodes.get(i);
            for (int j = 0; j < length - 1; j++) {
                Episode ep2 = episodes.get(j);
                int affinity = calculateAffinity(ep1, ep2);
                affinities[i][j] = affinity;
            }
        }

        ShortestPath shortestPath = new ShortestPath(length);
        int[] result = shortestPath.dijkstra(affinities, episodes.indexOf(episode));

        for (int i = 0; i < length - 1; i++) {
            for (int j = 0; j < length - 1; j++) {
                if (result[j] > result[j + 1]) {
                    int temp = result[j];
                    result[j] = result[j + 1];
                    result[j + 1] = temp;
                }
            }
        }

        for (int i = 0; i < length - 1; i++) {
            recEpisodes.addFirst(episodes.get(i));
        }

        return recEpisodes;
    }

    public int calculateAffinity(Episode ep1, Episode ep2) {
        int affinity = 0;

        if (ep1.equals(ep2)) return affinity;
        if (ep1.getGenre().equals(ep2.getGenre())) affinity += 6;
        if (ep1.getProductionTeam().equals(ep2.getProductionTeam())) affinity += 4;
        if (ep1.getLanguage().equals(ep2.getLanguage())) affinity += 2;

        return affinity;
    }
}
