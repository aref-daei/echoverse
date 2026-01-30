package model;

public class ProductionTeam extends Model {
    private String name;

    public ProductionTeam(String id, String name) {
        super(id);
        this.name = name;
    }

    public String getCrews() {
        return name;
    }

    public void setCrews(String name) {
        this.name = name;
    }
}
