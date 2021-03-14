package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Locale;

public class DAO {
    public static Connection getConnection () throws ClassNotFoundException, SQLException {
        Locale.setDefault(Locale.getDefault());
        Class.forName("org.postgresql.Driver");
        return DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/Software_DB",
                "postgres","123");
    }
}