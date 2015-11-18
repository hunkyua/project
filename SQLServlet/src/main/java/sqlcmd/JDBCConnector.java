package sqlcmd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by oktopus on 07.10.15.
 */
public class JDBCConnector {
    //    private final String DB_DRIVER = "com.mysql.jdbc.Driver";
//    private final String DB_URL = "jdbc:mysql://localhost:3306/";
    private static final String DB_DRIVER = "org.postgresql.Driver";
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/";
    public static String db_name;
    public static String db_user;
    public static String db_password;
    public static String error = "";
    public static String er_connect = "";

    public JDBCConnector(String n, String u, String p) {
        db_name = n;
        db_user = u;
        db_password = p;
    }

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        error = "";
        er_connect = "";
        Class.forName(DB_DRIVER);
        return DriverManager.getConnection(DB_URL + db_name, db_user, db_password);
       
    }
}



