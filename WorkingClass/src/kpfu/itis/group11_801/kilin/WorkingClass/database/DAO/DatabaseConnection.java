package kpfu.itis.group11_801.kilin.WorkingClass.database.DAO;

import java.sql.*;

public class DatabaseConnection {
    private static DatabaseConnection dao = null;
    private Connection connection;

    private DatabaseConnection() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
        }catch(ClassNotFoundException e) {
            e.printStackTrace();
        }
        this.connection = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/working_class",
                "postgres",
                "9497013311"
        );
    }

    public static DatabaseConnection getDatabaseConnection() throws SQLException {
        if (dao == null) {
            dao = new DatabaseConnection();
        }
        return dao;
    }

    public Connection getConnnection() {
        return connection;
    }
}