package service;

import model.Crew;
import model.ProductionTeam;
import util.LinkedBinaryTree;

public class ProductionTeamManagerService {
    ProductionTeam prodTeam;
    LinkedBinaryTree<Crew> crews;

    public ProductionTeamManagerService(ProductionTeam prodTeam) {
        this(prodTeam, new LinkedBinaryTree<>());
    }

    public ProductionTeamManagerService(ProductionTeam prodTeam, LinkedBinaryTree<Crew> crews) {
        this.prodTeam = prodTeam;
        this.crews = crews;
    }

    public Crew searchById(char type, String crewId) {
        return switch (Character.toLowerCase(type)) {
            case 'b' -> crews.findByBfs(new Crew(crewId));
            case 'd' -> crews.findByDfs(new Crew(crewId));
            default -> throw new IllegalArgumentException("Invalid type");
        };
    }

    public void addSupervisor(Crew crew) {
        crews.addRoot(crew);
    }

    public void addCrew(Crew parent, Crew crew) {
        crews.add(parent, crew);
    }

    public void removeCrew(Crew crew) {
        crews.remove(crew);
    }

    public String displayTeamTree() {
        return crews.displayTree();
    }
}
