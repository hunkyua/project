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
    public static String db_name;
    public static String db_user;
    public static String db_password;
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
        db_name = sc.nextLine();
        System.out.println("Please enter db_user");
        db_user = sc.nextLine();
        System.out.println("Please enter db_password: ");
        db_password = sc.nextLine();
        try {
            connection = DriverManager.getConnection(
                    DB_URL + db_name, db_user, db_password);
            System.out.println("Connection complete!");
        } catch (SQLException e) {
            System.out.println("Connection failed");
            System.exit(0);
            e.printStackTrace();
        }


    }
}


