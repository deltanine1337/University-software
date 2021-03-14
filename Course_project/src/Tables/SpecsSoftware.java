package Tables;

public class SpecsSoftware {
    private int specId;
    private int softwareId;
    private double value;

    public SpecsSoftware(int specId, int softwareId, double value) {
        this.specId = specId;
        this.softwareId = softwareId;
        this.value = value;
    }

    public int getSpecId() {
        return specId;
    }
    public void setSpecId(int specId) {
        this.specId = specId;
    }

    public int getSoftwareId() {
        return softwareId;
    }
    public void setSoftwareId(int softwareId)
    {
        this.softwareId = softwareId;
    }

    public double getValue()
    {
        return value;
    }
    public void setValue(double value)
    {
        this.value = value;
    }


}

