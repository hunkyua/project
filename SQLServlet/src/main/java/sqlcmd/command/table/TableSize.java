package sqlcmd.command.table;

import sqlcmd.JDBCConnector;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Hunky on 13.11.2015.
 */
public class TableSize {
    public static String error = "";
    public static String size = "";
    public static String doesNotExist = "";


    public static void GetTableSize(String tableName) throws ClassNotFoundException, SQLException {
        Statement statement = null;
        try {
            doesNotExist = "";
            error = "";
            statement = JDBCConnector.getConnection().createStatement();
            size = "";
            ResultSet rsCount = statement.executeQuery("SELECT COUNT (*) FROM " + tableName);
            rsCount.next();
            int a = rsCount.getInt(1);
            size = "Size of table = " + Integer.toString(a);
            rsCount.close();
        } catch (SQLException e) {
            doesNotExist = e.getMessage();
            if (doesNotExist.equals("Сервер запросил парольную аутентификацию, но пароль не был указан.")) {
                error = ">>> Please Connect to DB";
            }
        } finally {
            if (statement != null) {
                statement.close();
            }
        }
    }
}
