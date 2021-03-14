package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Tables.Specs;

public class SpecDAO {
    public static ArrayList<Specs> getSpecs() throws SQLException, ClassNotFoundException {
        try( ResultSet rs = DAO.getConnection().createStatement().executeQuery("SELECT * from SPECS") ){
            ArrayList<Specs> specs = new ArrayList<>();
            while (rs.next()){
                specs.add(new Specs( rs.getInt(1), rs.getString(2) ));
            }
            return specs;
        }
    }

    public static void addSpec(String name) throws SQLException, ClassNotFoundException {
        try( PreparedStatement ps = DAO.getConnection().prepareStatement("INSERT INTO SPECS VALUES (DEFAULT, ?)")){
            ps.setString(1, name);
            ps.executeUpdate();
        }
    }

    public static Specs getSpecByname(String name) throws SQLException, ClassNotFoundException {
        try(PreparedStatement prepareStatement = DAO.getConnection().
                prepareStatement("SELECT * from SPECS where spec_name = ?") ){
            prepareStatement.setString(1, name);

            ResultSet rs = prepareStatement.executeQuery();
            rs.next();
            Specs specs = new Specs(
                    rs.getInt(1), rs.getString(2)
            );
            return specs;
        }
    }

    public static Specs getSpecById(int id) throws SQLException, ClassNotFoundException {
        try(PreparedStatement prepareStatement = DAO.getConnection().
                prepareStatement("SELECT * from SPECS where spec_id = ?") ){
            prepareStatement.setInt(1, id);

            ResultSet rs = prepareStatement.executeQuery();
            rs.next();
            Specs specs = new Specs(
                    rs.getInt(1), rs.getString(2)
            );
            return specs;
        }
    }

    public static void deleteSpec(int spec_id)throws SQLException, ClassNotFoundException{
        try(PreparedStatement prepareStatement = DAO.getConnection().prepareStatement("DELETE from Specs WHERE spec_id = ?") ){
            prepareStatement.setInt(1, spec_id);
            prepareStatement.executeUpdate();
        }
    }

    public static void updateSpec (Specs specs) throws SQLException, ClassNotFoundException {
        try( PreparedStatement prepareStatement = DAO.getConnection().
                prepareStatement("UPDATE Specs SET spec_name = ? WHERE spec_id = ?") ){
            prepareStatement.setString(1, specs.getName());
            prepareStatement.setInt(2, specs.getSpecId());
            prepareStatement.executeUpdate();
        }
    }
}
