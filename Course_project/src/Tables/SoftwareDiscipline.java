package Tables;

public class SoftwareDiscipline {
    private int softwareId;
    private int disciplineId;

    public SoftwareDiscipline(int softwareId, int disciplineId) {
        this.disciplineId = disciplineId;
        this.softwareId = softwareId;
    }

    public int getDisciplineId() {
        return disciplineId;
    }

    public void setDisciplineId(int disciplineId) {
        this.disciplineId = disciplineId;
    }

    public int getSoftwareId() {
        return softwareId;
    }

    public void setSoftwareId(int softwareId) {
        this.softwareId = softwareId;
    }
}

