package service;

import model.Episode;

public class EpisodeRecommenderService {
    public int calculateAffinity(Episode ep1, Episode ep2) {
        int affinity = 0;

        if (ep1.getGenre().equals(ep2.getGenre())) affinity += 6;
        if (Math.abs(ep1.getRating() - ep2.getRating()) <= 1) affinity += 5;
        if (ep1.getProductionTeam().equals(ep2.getProductionTeam())) affinity += 4;
        if (ep1.getLanguage().equals(ep2.getLanguage())) affinity += 3;
        if (Math.abs(ep1.getYear() - ep2.getYear()) <= 10) affinity += 2;
        if (Math.abs(ep1.getDuration() - ep2.getDuration()) <= 30) affinity += 1;

        return affinity;
    }
}
