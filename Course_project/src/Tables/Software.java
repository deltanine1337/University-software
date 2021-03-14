package Tables;

import java.sql.Date;

public class Software {
    private int softwareId;
    private String name;
    private String version;
    private short numOfLic;
    private Date admissionDate;
    private int typeId;
    private int developerId;

    public Software(){}

    public Software(int softwareId, String name, String version, short numOfLic, Date admissionDate, int typeId, int developerId) {
        this.softwareId = softwareId;
        this.name = name;
        this.version = version;
        this.numOfLic = numOfLic;
        this.admissionDate = admissionDate;
        this.typeId = typeId;
        this.developerId = developerId;
    }


    public int getSoftwareId() {return softwareId;}
    public void setSoftwareId(int softwareId) { this.softwareId = softwareId; }

    public int getTypeId() {return typeId;}
    public void setTypeId(int typeId) { this.typeId = typeId; }

    public int getDeveloperId() {return developerId;}
    public void setDeveloperId(int developerId) { this.developerId = developerId; }

    public String getName() {return name;}
    public void setName(String name) { this.name = name; }

    public String getVersion() {return version;}
    public void setVersion(String version) { this.version = version; }

    public short getNumOfLic() {return numOfLic;}
    public void setNumOfLic(short numOfLic) { this.numOfLic = numOfLic; }

    public Date getAdmissionDate() {return admissionDate;}
    public void setAdmissionDate(Date admissionDate) { this.admissionDate = admissionDate; }


}
