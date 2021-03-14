package Tables;

public class Specs {
    private int specId;
    private String name;

    public Specs(int specId, String name) {
        this.specId = specId;
        this.name = name;
    }
    public Specs(String name) {
        this.name = name;
    }

    public int getSpecId() {
        return specId;
    }

    public void setSpecId(int specId) {
        this.specId = specId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

