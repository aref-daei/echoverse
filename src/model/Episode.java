package model;

public class Episode extends Model {
    private String title;
    private String genre;
    private ProductionTeam productionTeam;
    private String language;
    private int priority;

    public Episode(String id, String title) {
        this(id, title, "", new ProductionTeam("", ""), "", 0);
    }

    public Episode(String id, String title, String genre, ProductionTeam productionTeam,
                   String language, int priority) {
        super(id);
        this.title = title;
        this.genre = genre;
        this.productionTeam = productionTeam;
        this.language = language;
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
                "Title: %s  Id: %s  Genre: %s%nProduction Team: %s  Language: %s",
                getTitle(), getId(), getGenre(),
                getProductionTeam().getName(), getLanguage()
        );
    }
}
