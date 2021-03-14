package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Tables.Software;

public class SoftwareDAO {
    public static ArrayList<Software> getSoftwares() throws SQLException, ClassNotFoundException {
        try( ResultSet rs = DAO.getConnection().createStatement().executeQuery("SELECT * from Software") ){
            ArrayList<Software> softwares = new ArrayList<>();
            while (rs.next()){
                softwares.add(new Software( rs.getInt(1), rs.getString(2), rs.getString(3), rs.getShort(4),
                        rs.getDate(5), rs.getInt(6), rs.getInt(7)));
            }
            return softwares;
        }
    }

    public static void addSoftware(Software software) throws SQLException, ClassNotFoundException {
        try( PreparedStatement ps = DAO.getConnection().prepareStatement("INSERT INTO Software VALUES (DEFAULT, ?, ?, ?, ?, ?, ?)")){
            ps.setString(1, software.getName());
            ps.setString(2, software.getVersion());
            ps.setShort(3, software.getNumOfLic());
            ps.setDate(4, software.getAdmissionDate());
            ps.setInt(5, software.getTypeId());
            ps.setInt(6, software.getDeveloperId());
            ps.executeUpdate();
        }
    }

    public static Software getSoftwareById(int id) throws SQLException, ClassNotFoundException {
        try(PreparedStatement prepareStatement = DAO.getConnection().
                prepareStatement("SELECT * FROM Software WHERE software_id = ?") ) {
            prepareStatement.setInt(1, id);
            ResultSet rs = prepareStatement.executeQuery();
            rs.next();
            Software software = new Software( rs.getInt(1), rs.getString(2), rs.getString(3), rs.getShort(4),
                    rs.getDate(5), rs.getInt(6), rs.getInt(7));
            return software;
        }

    }

    public static Software getSoftwareByName(String name) throws SQLException, ClassNotFoundException {
        try(PreparedStatement prepareStatement = DAO.getConnection().
                prepareStatement("SELECT * FROM Software WHERE software_name = ?") ){
            prepareStatement.setString(1, name);

            ResultSet rs = prepareStatement.executeQuery();
            rs.next();
                Software software = new Software( rs.getInt(1), rs.getString(2), rs.getString(3), rs.getShort(4),
                        rs.getDate(5), rs.getInt(6), rs.getInt(7));
            return software;
        }
    }

    public static List<Software> getSoftwareByType(String type) throws SQLException, ClassNotFoundException {
        try(PreparedStatement prepareStatement = DAO.getConnection().
                prepareStatement("SELECT * FROM Software WHERE type_id = (SELECT type_id from Software_type where type_name = ?)") ){
            prepareStatement.setString(1, type);

            ResultSet rs = prepareStatement.executeQuery();
            ArrayList<Software> softwares = new ArrayList<>();
            while (rs.next()){
                softwares.add(new Software( rs.getInt(1), rs.getString(2), rs.getString(3), rs.getShort(4),
                        rs.getDate(5), rs.getInt(6), rs.getInt(7)));
            }
            return softwares;
        }
    }

    public static List<Software> getSoftwareByDeveloper(String developer) throws SQLException, ClassNotFoundException {
        try(PreparedStatement prepareStatement = DAO.getConnection().
                prepareStatement("SELECT * FROM Software WHERE developer_id = (SELECT developer_id from Software_type where developer_name = ?)") ){
            prepareStatement.setString(1, developer);

            ResultSet rs = prepareStatement.executeQuery();
            ArrayList<Software> softwares = new ArrayList<>();
            while (rs.next()){
                softwares.add(new Software( rs.getInt(1), rs.getString(2), rs.getString(3), rs.getShort(4),
                        rs.getDate(5), rs.getInt(6), rs.getInt(7)));
            }
            return softwares;
        }
    }

    public static void updateSoftware(Software software) throws SQLException, ClassNotFoundException {
        try( PreparedStatement prepareStatement = DAO.getConnection().
                prepareStatement("UPDATE Software SET software_name = ?, software_version = ?, num_of_licenses = ?, admission_date = ?, " +
                        "type_id = ?, developer_id = ? WHERE software_id = ?") ){
            prepareStatement.setString(1, software.getName());
            prepareStatement.setString(2, software.getVersion());
            prepareStatement.setShort(3, software.getNumOfLic());
            prepareStatement.setDate(4, software.getAdmissionDate());
            prepareStatement.setInt(5, software.getTypeId());
            prepareStatement.setInt(6, software.getDeveloperId());
            prepareStatement.setInt(7, software.getSoftwareId());
            prepareStatement.executeUpdate();
        }
    }

    public static void deleteSoftware(int software_id)throws SQLException, ClassNotFoundException{
        try(PreparedStatement prepareStatement = DAO.getConnection().prepareStatement("DELETE from Software WHERE software_id = ?") ){
            prepareStatement.setInt(1, software_id);
            prepareStatement.executeUpdate();
        }
    }
}
