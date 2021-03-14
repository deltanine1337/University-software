package Tables;

public class Discipline {
    private int disciplineId;
    private String name;

    public Discipline(int disciplineId, String name) {
        this.disciplineId = disciplineId;
        this.name = name;
    }
    public Discipline(String name) {
        this.name = name;
    }

    public int getDisciplineId() {
        return disciplineId;
    }

    public void setDisciplineId(int disciplineId) {
        this.disciplineId = disciplineId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

