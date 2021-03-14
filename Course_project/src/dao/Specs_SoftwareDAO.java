package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Tables.SpecsSoftware;


public class Specs_SoftwareDAO {
    public static ArrayList<SpecsSoftware> getSpecSoftwares() throws SQLException, ClassNotFoundException {
        try( ResultSet rs = DAO.getConnection().createStatement().executeQuery("SELECT * from Specs_Software") ){
            ArrayList<SpecsSoftware> specSoftwares = new ArrayList<>();
            while (rs.next()){
                specSoftwares.add(new SpecsSoftware( rs.getInt(1), rs.getInt(2), rs.getDouble(3) ));
            }
            return specSoftwares;
        }
    }

    public static void addSpecSoftware(int spec_id, int soft_id, double value) throws SQLException, ClassNotFoundException {
        try( PreparedStatement ps = DAO.getConnection().prepareStatement("INSERT INTO Specs_Software VALUES (?, ?, ?)")){
            ps.setInt(1, spec_id);
            ps.setInt(2, soft_id);
            ps.setDouble(3, value);
            ps.executeUpdate();
        }
    }

    public static ArrayList<SpecsSoftware> getSpecSoftwareBySpec(String spec_name) throws SQLException, ClassNotFoundException {
        try(PreparedStatement prepareStatement = DAO.getConnection().
                prepareStatement("SELECT * from Specs_Software where spec_id = (SELECT spec_id FROM Specs WHERE spec_name = ?)") ){
            prepareStatement.setString(1, spec_name);
            ArrayList<SpecsSoftware> specSoftwares = new ArrayList<>();
            ResultSet rs = prepareStatement.executeQuery();
            while (rs.next()){
                specSoftwares.add(new SpecsSoftware( rs.getInt(1), rs.getInt(2), rs.getDouble(3) ));
            }
            return specSoftwares;
        }
    }

    public static ArrayList<SpecsSoftware> getSpecSoftwareBySoftware(String software_name) throws SQLException, ClassNotFoundException {
        try(PreparedStatement prepareStatement = DAO.getConnection().
                prepareStatement("SELECT * from Specs_Software where software_id = (SELECT software_id FROM Software WHERE software_name = ?)") ){
            prepareStatement.setString(1, software_name);

            ArrayList<SpecsSoftware> specSoftwares = new ArrayList<>();
            ResultSet rs = prepareStatement.executeQuery();
            while (rs.next()){
                specSoftwares.add(new SpecsSoftware( rs.getInt(1), rs.getInt(2), rs.getDouble(3) ));
            }
            return specSoftwares;
        }
    }

    public static SpecsSoftware getSpecSoftwareByIds(int spid, int soid) throws SQLException, ClassNotFoundException {
        try( PreparedStatement prepareStatement = DAO.getConnection().
                prepareStatement("SELECT * FROM Specs_software where spec_id = ? and software_id = ?")){
            prepareStatement.setInt(1, spid);
            prepareStatement.setInt(2,soid);
            ResultSet rs = prepareStatement.executeQuery();
            rs.next();
            SpecsSoftware specsSoftware = new SpecsSoftware(rs.getInt(1), rs.getInt(2), rs.getDouble(3));
            return specsSoftware;
        }
    }

    public static void updateValue(SpecsSoftware specsSoftware) throws SQLException, ClassNotFoundException {
        try( PreparedStatement prepareStatement = DAO.getConnection().
                prepareStatement("UPDATE Specs_Software SET spec_value = ? WHERE spec_id = ? and software_id = ?") ){
            prepareStatement.setDouble(1, specsSoftware.getValue());
            prepareStatement.setInt(2, specsSoftware.getSpecId());
            prepareStatement.setInt(3,specsSoftware.getSoftwareId());
            prepareStatement.executeUpdate();
        }
    }

    public static void deleteSpecSoftware(int specid, int software_id)throws SQLException, ClassNotFoundException{
        try(PreparedStatement prepareStatement = DAO.getConnection().prepareStatement("DELETE from Specs_software WHERE spec_id = ? and software_id = ?") ){
            prepareStatement.setInt(1, specid);
            prepareStatement.setInt(2, software_id);
            prepareStatement.executeUpdate();
        }
    }

    public static String withoutNullAfterDot(double value)
    {
        String val = Double.toString(value);
        String[] vals = val.split("\\.");
        if (val.split("\\.")[1].equals("0"))
        {
            val = val.split("\\.")[0];
        }
        return val;
    }
}
