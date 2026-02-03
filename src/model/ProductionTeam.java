package model;

public class ProductionTeam extends Model {
    private String name;

    public ProductionTeam(String id, String name) {
        super(id);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format("Name: %s", getName());
    }
}
