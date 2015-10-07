
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 * Created by oktopus on 07.10.15.
 */
public class JDBCConnector {
    private static final String DB_DRIVER = "org.postgresql.Driver";
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/";
    private static final String DB_NAME = "sqlbd";
    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "root";

    public static Connection getDBConnection() {
        Connection connection = null;
        try {
            Class.forName(DB_DRIVER);
        } catch (ClassNotFoundException e) {
            System.out.println("Add JDBC driver please!!!");
            e.printStackTrace();
        }

        try {
            connection = DriverManager.getConnection(
                    DB_URL + DB_NAME, DB_USER, DB_PASSWORD);
            return connection;
        } catch (SQLException e) {
            System.out.println("Connection failed");
            e.printStackTrace();
        }
        System.out.println("Failed to make connection");
        return connection;
    }
}


