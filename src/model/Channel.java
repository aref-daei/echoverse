package model;

import util.LinkedList;

public class Channel extends Model {
    private String title;
    private LinkedList<Episode> episodes;

    public Channel(String id, String title) {
        this(id, title, new LinkedList<>(), false);
    }

    public Channel(String id, String title, LinkedList<Episode> episodes) {
        this(id, title, episodes, false);
    }

    public Channel(String id, String title, LinkedList<Episode> episodes, boolean isVerified) {
        super(id);
        this.title = title;
        this.episodes = episodes;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LinkedList<Episode> getEpisodes() {
        return episodes;
    }

    public void setEpisodes(LinkedList<Episode> episodes) {
        this.episodes = episodes;
    }

    public void addEpisode(Episode episode) {
        episodes.addLast(episode);
    }

    @Override
    public String toString() {
        return String.format("Title: %s  Id: %s", getTitle(), getId());
    }
}
