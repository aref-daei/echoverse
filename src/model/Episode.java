package model;

public class Episode extends Model {
    private String title;
    private String genre;
    private int year;
    private ProductionTeam productionTeam;
    private String language;
    private int duration;
    private float rating;
    private int priority;

    public Episode(String id, String title, String genre, int year,
                   ProductionTeam productionTeam,
                   String language, int duration, float rating, int priority) {
        super(id);
        this.title = title;
        this.genre = genre;
        this.year = year;
        this.productionTeam = productionTeam;
        this.language = language;
        this.duration = duration;
        this.rating = rating;
        this.priority = priority;
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

    public ProductionTeam getProductionTeam() {
        return productionTeam;
    }

    public void setProductionTeam(ProductionTeam productionTeam) {
        this.productionTeam = productionTeam;
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

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    @Override
    public int compareTo(Model o) {
        Episode episode = (Episode) o;
        return Integer.compare(this.priority, episode.priority);
    }
}
