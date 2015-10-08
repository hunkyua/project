package JDBCConnector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * Created by oktopus on 07.10.15.
 */
public class JDBCConnector {
    private final String DB_DRIVER = "org.postgresql.Driver";
    private final String DB_URL = "jdbc:postgresql://localhost:5432/";
//    private final String DB_NAME = "sqlbd";
//    private final String DB_USER = "postgres";
//    private final String DB_PASSWORD = "root";
    public static Connection connection;
    public Connection isConnected() {
        return connection;
    }

    public void getDBConnection() {
        try {
            Class.forName(DB_DRIVER);
        } catch (ClassNotFoundException e) {
            System.out.println("Add JDBC driver please!!!");
            e.printStackTrace();
        }
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter db_name: ");
        String db_name = sc.nextLine();
        System.out.println("Please enter db_user");
        String db_user = sc.nextLine();
        System.out.println("Please enter db_password: ");
        String db_password = sc.nextLine();
        try {
            connection = DriverManager.getConnection(
                    DB_URL + db_name, db_user, db_password);
        } catch (SQLException e) {
            System.out.println("Connection failed");
            e.printStackTrace();
        }

        System.out.println("Failed to make connection");
    }
}


