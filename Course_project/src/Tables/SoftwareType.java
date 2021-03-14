package Tables;

public class SoftwareType {
    private int typeId;
    private String name;

    public SoftwareType(int typeId, String name) {
        this.typeId = typeId;
        this.name = name;
    }
    public SoftwareType(String name) {
        this.name = name;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
