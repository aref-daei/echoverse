package model;

public class Episode extends Model {
    private String title;
    private String genre;
    private int year;
    private ProductionTeam productionTeam;
    private String language;
    private int duration;
    private int priority;

    public Episode(String id, String title, String genre, int year,
                   ProductionTeam productionTeam,
                   String language, int duration, int priority) {
        super(id);
        this.title = title;
        this.genre = genre;
        this.year = year;
        this.productionTeam = productionTeam;
        this.language = language;
        this.duration = duration;
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

    @Override
    public String toString() {
        return String.format(
                "Title: %s  Id: %s  Genre: %s  Year: %s%nProd. Team: %s  Language: %s  Duration: %s",
                getTitle(), getId(), getGenre(), getYear(),
                getProductionTeam().getName(), getLanguage(), getDuration()
        );
    }
}
