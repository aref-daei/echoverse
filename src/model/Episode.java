package model;

public class Episode extends Model {
    private String title;
    private String genre;
    private int year;
    private ProductionTeam productionTeam;
    private String language;
    private int duration;
    private float rating;

    public Episode(String id, String title, String genre, int year,
                   ProductionTeam productionTeam,
                   String language, int duration, float rating) {
        super(id);
        this.title = title;
        this.genre = genre;
        this.year = year;
        this.productionTeam = productionTeam;
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
}
