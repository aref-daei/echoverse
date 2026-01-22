package model;

import util.LinkedList;

public class Channel extends Model {
    private int title;
    private LinkedList<Episode> episodes;
    private boolean isVerified;

    public Channel(String id, int title) {
        this(id, title, new LinkedList<>(), false);
    }

    public Channel(String id, int title, LinkedList<Episode> episodes) {
        this(id, title, episodes, false);
    }

    public Channel(String id, int title, LinkedList<Episode> episodes, boolean isVerified) {
        super(id);
        this.title = title;
        this.episodes = episodes;
        this.isVerified = isVerified;
    }

    public int getTitle() {
        return title;
    }

    public void setTitle(int title) {
        this.title = title;
    }

    public LinkedList<Episode> getEpisodes() {
        return episodes;
    }

    public void setEpisodes(LinkedList<Episode> episodes) {
        this.episodes = episodes;
    }

    public boolean isVerified() {
        return isVerified;
    }

    public void setVerified(boolean verified) {
        isVerified = verified;
    }

    public void addEpisode(Episode episode) {
        episodes.addLast(episode);
    }
}
