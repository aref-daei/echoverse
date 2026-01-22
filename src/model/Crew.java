package model;

public class Crew extends Model {
    private String name;
    private int age;
    private boolean isSupervisor;

    public Crew(String id, String name, int age, boolean isSupervisor) {
        super(id);
        this.name = name;
        this.age = age;
        this.isSupervisor = isSupervisor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isSupervisor() {
        return isSupervisor;
    }

    public void setSupervisor(boolean supervisor) {
        isSupervisor = supervisor;
    }
}
