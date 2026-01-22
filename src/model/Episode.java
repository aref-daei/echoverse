package model;

public class Episode extends Model {
    private String title;
    private String genre;
    private int year;
    private ProductionTeam prod_team;
    private String language;
    private int duration;
    private float rating;

    public Episode(String id, String title, String genre, int year,
                   ProductionTeam prod_team,
                   String language, int duration, float rating) {
        super(id);
        this.title = title;
        this.genre = genre;
        this.year = year;
        this.prod_team = prod_team;
        this.language = language;
        this.duration = duration;
        this.rating = rating;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public ProductionTeam getProd_team() {
        return prod_team;
    }

    public void setProd_team(ProductionTeam prod_team) {
        this.prod_team = prod_team;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public static int calculateAffinity(Episode ep1, Episode ep2) {
        int affinity = 0;

        if (ep1.genre.equals(ep2.genre)) affinity += 6;
        if (Math.abs(ep1.rating - ep2.rating) <= 1) affinity += 5;
        if (ep1.prod_team.equals(ep2.prod_team)) affinity += 4;
        if (ep1.language.equals(ep2.language)) affinity += 3;
        if (Math.abs(ep1.year - ep2.year) <= 10) affinity += 2;
        if (Math.abs(ep1.duration - ep2.duration) <= 30) affinity += 1;

        return affinity;
    }
}
