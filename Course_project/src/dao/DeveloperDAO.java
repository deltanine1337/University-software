package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Tables.Developer;

public class DeveloperDAO {
    public static ArrayList<Developer> getDevelopers() throws SQLException, ClassNotFoundException {
        try( ResultSet rs = DAO.getConnection().createStatement().executeQuery("SELECT * from DEVELOPER") ){
            ArrayList<Developer> developers = new ArrayList<>();
            while (rs.next()){
                developers.add(new Developer( rs.getInt(1), rs.getString(2) ));
            }
            return developers;
        }
    }

    public static void addDeveloper(String name) throws SQLException, ClassNotFoundException {
        try( PreparedStatement ps = DAO.getConnection().prepareStatement("INSERT INTO DEVELOPER VALUES (DEFAULT, ?)")){
            ps.setString(1, name);
            ps.executeUpdate();
        }
    }

    public static Developer getDeveloperByName(String name) throws SQLException, ClassNotFoundException {
        try(PreparedStatement prepareStatement = DAO.getConnection().
                prepareStatement("SELECT * FROM DEVELOPER WHERE developer_name = ?") ){
            prepareStatement.setString(1, name);

            ResultSet rs = prepareStatement.executeQuery();
            rs.next();
            Developer developer = new Developer(
                    rs.getInt(1), rs.getString(2)
            );
            return developer;
        }
    }

    public static Developer getDeveloperById(int id) throws SQLException, ClassNotFoundException {
        try(PreparedStatement prepareStatement = DAO.getConnection().
                prepareStatement("SELECT * from DEVELOPER where DEVELOPER_ID = ?") ){
            prepareStatement.setInt(1, id);

            ResultSet rs = prepareStatement.executeQuery();
            rs.next();
            Developer developer = new Developer(
                    rs.getInt(1), rs.getString(2)
            );
            return developer;
        }
    }

    public static void deleteDeveloper(int devid) throws SQLException, ClassNotFoundException{
        try(PreparedStatement prepareStatement = DAO.getConnection().prepareStatement("DELETE from Developer WHERE developer_id = ?") ){
            prepareStatement.setInt(1, devid);
            prepareStatement.executeUpdate();
        }
    }

    public static void updateDeveloper (Developer developer) throws SQLException, ClassNotFoundException {
        try( PreparedStatement prepareStatement = DAO.getConnection().
                prepareStatement("UPDATE Developer SET developer_name = ? WHERE developer_id = ?") ){
            prepareStatement.setString(1, developer.getName());
            prepareStatement.setInt(2, developer.getDeveloperId());
            prepareStatement.executeUpdate();
        }
    }
}
