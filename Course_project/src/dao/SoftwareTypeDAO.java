package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Tables.SoftwareType;

public class SoftwareTypeDAO {
    public static ArrayList<SoftwareType> getSoftwareTypes() throws SQLException, ClassNotFoundException {
        try( ResultSet rs = DAO.getConnection().createStatement().executeQuery("SELECT * from Software_type") ){
            ArrayList<SoftwareType> softwareTypes = new ArrayList<>();
            while (rs.next()){
                softwareTypes.add(new SoftwareType( rs.getInt(1), rs.getString(2) ));
            }
            return softwareTypes;
        }
    }

    public static void addSoftwareType(String name) throws SQLException, ClassNotFoundException {
        try( PreparedStatement ps = DAO.getConnection().prepareStatement("INSERT INTO Software_type VALUES (DEFAULT, ?)")){
            ps.setString(1, name);
            ps.executeUpdate();
        }
    }

    public static SoftwareType getSoftwareTypeByName(String fio) throws SQLException, ClassNotFoundException {
        try (PreparedStatement prepareStatement = DAO.getConnection().
                prepareStatement("SELECT * from Software_type where type_name = ?")) {
            prepareStatement.setString(1, fio);

            ResultSet rs = prepareStatement.executeQuery();
            if (rs.next()) {
                SoftwareType softwareType = new SoftwareType(
                        rs.getInt(1), rs.getString(2)
                );
                return softwareType;
            }
            else return null;
        }
    }

    public static SoftwareType getSoftwareTypeById(int id) throws SQLException, ClassNotFoundException {
        try(PreparedStatement prepareStatement = DAO.getConnection().
                prepareStatement("SELECT * from Software_type where type_id = ?") ){
            prepareStatement.setInt(1, id);

            ResultSet rs = prepareStatement.executeQuery();
            rs.next();
            SoftwareType softwareType = new SoftwareType(
                    rs.getInt(1), rs.getString(2)
            );
            return softwareType;
        }
    }

    public static void deleteSoftwareType(int softwaretype_id)throws SQLException, ClassNotFoundException{
        try(PreparedStatement prepareStatement = DAO.getConnection().prepareStatement("DELETE from Software_type WHERE type_id = ?") ){
            prepareStatement.setInt(1, softwaretype_id);
            prepareStatement.executeUpdate();
        }
    }

    public static void updateSoftwareType (SoftwareType softwareType) throws SQLException, ClassNotFoundException {
        try( PreparedStatement prepareStatement = DAO.getConnection().
                prepareStatement("UPDATE Software_type SET type_name = ? WHERE type_id = ?") ){
            prepareStatement.setString(1, softwareType.getName());
            prepareStatement.setInt(2, softwareType.getTypeId());
            prepareStatement.executeUpdate();
        }
    }
}
