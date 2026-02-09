package service;

import model.Episode;
import util.LinkedList;
import util.ShortestPath;

public class EpisodeRecommenderService {
    // بیشترین میزان شباهت ممکن
    private static final int MAX_AFFINITY = 12; // 6 + 4 + 2

    // لیست اپیزودها
    private final LinkedList<Episode> episodes;
    // تعداد اپیزودها
    private final int length;

    public EpisodeRecommenderService(LinkedList<Episode> episodes) {
        this.episodes = episodes;
        this.length = episodes.size();
    }

    // متد برای پیشنهاد اپیزودهای مشابه
    public LinkedList<Episode> recommend(Episode source, int count) {
        LinkedList<Episode> recommendations = new LinkedList<>();

        int sourceIndex = episodes.indexOf(source);
        if (sourceIndex == -1) return recommendations;

        // ساخت گراف هزینه‌ها
        int[][] graph = buildCostGraph();

        // اجرای الگوریتم دایکسترا
        ShortestPath sp = new ShortestPath(length);
        int[] dist = sp.dijkstra(graph, sourceIndex);

        // نگه‌داری ایندکس اپیزودها
        int[] indices = new int[length];
        for (int i = 0; i < length; i++) {
            indices[i] = i;
        }

        // مرتب‌سازی بر اساس فاصله
        for (int i = 0; i < length - 1; i++) {
            for (int j = 0; j < length - i - 1; j++) {
                if (dist[indices[j]] > dist[indices[j + 1]]) {
                    int temp = indices[j];
                    indices[j] = indices[j + 1];
                    indices[j + 1] = temp;
                }
            }
        }

        // انتخاب بهترین اپیزودها
        int added = 0;
        for (int i = 0; i < length && added < count; i++) {
            int idx = indices[i];

            // حذف خود اپیزود مبدا
            if (idx == sourceIndex) continue;
            // اگر مسیری وجود نداشته باشد
            if (dist[idx] == Integer.MAX_VALUE) continue;

            recommendations.addLast(episodes.get(idx));
            added++;
        }

        return recommendations;
    }

    // متد برای ساخت گراف بر اساس میزان شباهت اپیزودها
    private int[][] buildCostGraph() {
        int[][] graph = new int[length][length];

        for (int i = 0; i < length; i++) {
            Episode ep1 = episodes.get(i);

            for (int j = 0; j < length; j++) {
                Episode ep2 = episodes.get(j);

                // یال به خودش وجود ندارد
                if (i == j) {
                    graph[i][j] = 0;
                    continue;
                }

                // محاسبه شباهت
                int affinity = calculateAffinity(ep1, ep2);

                // اگر شباهتی نباشد یال نداریم
                if (affinity == 0) {
                    graph[i][j] = 0;
                } else {
                    // تبدیل شباهت به هزینه
                    graph[i][j] = MAX_AFFINITY - affinity;
                }
            }
        }

        return graph;
    }

    // متد برای محاسبه میزان شباهت دو اپیزود
    private int calculateAffinity(Episode ep1, Episode ep2) {
        int affinity = 0;

        if (ep1.getGenre().equals(ep2.getGenre())) affinity += 6;
        if (ep1.getProductionTeam().equals(ep2.getProductionTeam())) affinity += 4;
        if (ep1.getLanguage().equals(ep2.getLanguage())) affinity += 2;

        return affinity;
    }
}
