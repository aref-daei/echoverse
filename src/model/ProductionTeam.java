package model;

public class ProductionTeam extends Model {
    private Crew[] crews;

    public ProductionTeam(String id, Crew[] crews) {
        super(id);
        this.crews = crews;
    }

    public Crew[] getCrews() {
        return crews;
    }

    public void setCrews(Crew[] crews) {
        this.crews = crews;
    }
}
