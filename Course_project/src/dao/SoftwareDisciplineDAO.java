package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Tables.SoftwareDiscipline;

public class SoftwareDisciplineDAO {
    public static ArrayList<SoftwareDiscipline> getSoftwareDisciplines() throws SQLException, ClassNotFoundException {
        try( ResultSet rs = DAO.getConnection().createStatement().executeQuery("SELECT * from Software_Discipline") ){
            ArrayList<SoftwareDiscipline> SoftwareDisciplines = new ArrayList<>();
            while (rs.next()){
                SoftwareDisciplines.add(new SoftwareDiscipline( rs.getInt(1), rs.getInt(2)));
            }
            return SoftwareDisciplines;
        }
    }

    public static void addSoftwareDiscipline(int spec_id, int soft_id) throws SQLException, ClassNotFoundException {
        try( PreparedStatement ps = DAO.getConnection().prepareStatement("INSERT INTO Software_Discipline VALUES (?, ?)")){
            ps.setInt(1, spec_id);
            ps.setInt(2, soft_id);
            ps.executeUpdate();
        }
    }

    public static ArrayList<SoftwareDiscipline> getSoftwareDisciplineBySoftware(String software_name) throws SQLException, ClassNotFoundException {
        try(PreparedStatement prepareStatement = DAO.getConnection().
                prepareStatement("SELECT * from Software_Discipline where software_id = (SELECT software_id FROM Software WHERE software_name = ?)") ){
            prepareStatement.setString(1, software_name);

            ResultSet rs = prepareStatement.executeQuery();
            ArrayList<SoftwareDiscipline> SoftwareDisciplines = new ArrayList<>();
            while (rs.next()){
                SoftwareDisciplines.add(new SoftwareDiscipline( rs.getInt(1), rs.getInt(2)));
            }
            return SoftwareDisciplines;
        }
    }

    public static SoftwareDiscipline getSoftwareDisciplineByIds(int sid, int did) throws SQLException, ClassNotFoundException {
        try (PreparedStatement prepareStatement = DAO.getConnection().
                prepareStatement("SELECT * from Software_Discipline where discipline_id = ? and software_id = ?")) {
            prepareStatement.setInt(1, did);
            prepareStatement.setInt(2, sid);
            ResultSet rs = prepareStatement.executeQuery();
            rs.next();
            SoftwareDiscipline softwareDiscipline = new SoftwareDiscipline(
                    rs.getInt(1), rs.getInt(2)
            );
            return softwareDiscipline;
        }
    }

    public static ArrayList<SoftwareDiscipline> getSoftwareDisciplineByDiscipline(String discipline_name) throws SQLException, ClassNotFoundException {
        try(PreparedStatement prepareStatement = DAO.getConnection().
                prepareStatement("SELECT * from Software_Discipline where discipline_id = (SELECT discipline_id FROM Discipline WHERE discipline_name = ?)") ){
            prepareStatement.setString(1, discipline_name);

            ResultSet rs = prepareStatement.executeQuery();
            ArrayList<SoftwareDiscipline> SoftwareDisciplines = new ArrayList<>();
            while (rs.next()){
                SoftwareDisciplines.add(new SoftwareDiscipline( rs.getInt(1), rs.getInt(2)));
            }
            return SoftwareDisciplines;
        }
    }

    public static void deleteSoftwareDiscipline(int software_id, int disc_id)throws SQLException, ClassNotFoundException{
        try(PreparedStatement prepareStatement = DAO.getConnection().prepareStatement("DELETE from Software_discipline WHERE software_id = ? AND discipline_id = ?") ){
            prepareStatement.setInt(1, software_id);
            prepareStatement.setInt(2,disc_id);
            prepareStatement.executeUpdate();
        }
    }
}
