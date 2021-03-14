package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Tables.Discipline;

public class DisciplineDAO {
    public static ArrayList<Discipline> getDisciplines() throws SQLException, ClassNotFoundException {
        try( ResultSet rs = DAO.getConnection().createStatement().executeQuery("SELECT * from DISCIPLINE") ){
            ArrayList<Discipline> disciplines = new ArrayList<>();
            while (rs.next()){
                disciplines.add(new Discipline( rs.getInt(1), rs.getString(2) ));
            }
            return disciplines;
        }
    }

    public static void addDiscipline(String name) throws SQLException, ClassNotFoundException {
        try( PreparedStatement ps = DAO.getConnection().prepareStatement("INSERT INTO DISCIPLINE VALUES (DEFAULT, ?)")){
            ps.setString(1, name);
            ps.executeUpdate();
        }
    }

    public static Discipline getDisciplineByName(String name) throws SQLException, ClassNotFoundException {
        try(PreparedStatement prepareStatement = DAO.getConnection().
                prepareStatement("SELECT * from DISCIPLINE where discipline_name = ?") ){
            prepareStatement.setString(1, name);
            ResultSet rs = prepareStatement.executeQuery();
            rs.next();
            Discipline discipline = new Discipline(
                    rs.getInt(1), rs.getString(2)
            );
            return discipline;
        }
    }

    public static Discipline getDisciplinerById(int id) throws SQLException, ClassNotFoundException {
        try(PreparedStatement prepareStatement = DAO.getConnection().
                prepareStatement("SELECT * from DISCIPLINE where discipline_id = ?") ){
            prepareStatement.setInt(1, id);

            ResultSet rs = prepareStatement.executeQuery();
            rs.next();
            Discipline discipline = new Discipline(
                    rs.getInt(1), rs.getString(2)
            );
            return discipline;
        }
    }

    public static void deleteDiscipline(int disc_id)throws SQLException, ClassNotFoundException{
        try(PreparedStatement prepareStatement = DAO.getConnection().prepareStatement("DELETE from Discipline WHERE discipline_id = ?") ){
            prepareStatement.setInt(1, disc_id);
            prepareStatement.executeUpdate();
        }
    }

    public static void updateDiscipline (Discipline discipline) throws SQLException, ClassNotFoundException {
        try( PreparedStatement prepareStatement = DAO.getConnection().
                prepareStatement("UPDATE Discipline SET discipline_name = ? WHERE discipline_id = ?") ){
            prepareStatement.setString(1, discipline.getName());
            prepareStatement.setInt(2, discipline.getDisciplineId());
            prepareStatement.executeUpdate();
        }
    }
}
