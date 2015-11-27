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


    public void getTableSize(String tableName) throws ClassNotFoundException, SQLException {
        Statement statement = null;
        try {
            doesNotExist = "";
            error = "";
            statement = JDBCConnector.getConnection().createStatement();
            size = "";
            ResultSet rsCount = statement.executeQuery("SELECT COUNT (*) FROM " + tableName);
            rsCount.next();
            int a = rsCount.getInt(1);
            size = String.format("Size of table:\"%s\" = ", tableName) + Integer.toString(a);
            rsCount.close();
        } catch (SQLException e) {
            doesNotExist = String.format("Sorry, but table:\"%s\" doesn't exist. Try again", tableName);
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
