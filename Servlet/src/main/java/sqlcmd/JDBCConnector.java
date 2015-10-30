package sqlcmd;

import java.io.CharArrayWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * Created by oktopus on 07.10.15.
 */
public class JDBCConnector implements Connector {

    private final String DB_DRIVER = "org.postgresql.Driver";
//    private final String DB_DRIVER = "com.mysql.jdbc.Driver";
    private final String DB_URL = "jdbc:postgresql://localhost:5432/";
//    private final String DB_URL = "jdbc:mysql://localhost:3306/";
    public static String db_name;
    public static String db_user;
    public static String db_password;
    public static Connection connection;
    private View view = new ViewImpl();

    @Override
    public Connection get() {
        return connection;
    }

    @Override
    public boolean connect() {
        try {
            Class.forName(DB_DRIVER);
        } catch (ClassNotFoundException e) {
            view.write("Add JDBC driver please!!!");
            e.printStackTrace();
        }
        Scanner sc = new Scanner(System.in);
        view.write("Please enter db_name: ");
        db_name = sc.nextLine();
        view.write("Please enter db_user");
        db_user = sc.nextLine();
        view.write("Please enter db_password: ");
        db_password = sc.nextLine();
        try {
            connection = DriverManager.getConnection(
                    DB_URL + db_name, db_user, db_password);
            view.write("Connection complete!");
            return true;
        } catch (SQLException e) {
            view.write("Connection failed: " + e.getMessage());
            return false;
        }
    }
}


