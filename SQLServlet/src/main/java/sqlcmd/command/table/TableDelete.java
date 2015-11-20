package sqlcmd.command.table;

import sqlcmd.JDBCConnector;

import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Hunky on 15.11.2015.
 */
public class TableDelete {
    public static String error = "";
    public static String doesNotExist = "";
    public static void DeleteTable (String tableName) throws ClassNotFoundException, SQLException {
        Statement statement = null;
        String deleteTableSQL = "DROP TABLE " + tableName;

        try {
            doesNotExist = String.format("Table:\"%s\" deleted", tableName);
            error = "";
            statement = JDBCConnector.getConnection().createStatement();
            statement.execute(deleteTableSQL);
        } catch (SQLException e) {
            doesNotExist = String.format("Sorry, but table:\"%s\" can't be deleted. Try again", tableName);
            if (doesNotExist.equals("Сервер запросил парольную аутентификацию, но пароль не был указан.")){
            error = ">>> Please Connect to DB";
            }
        } finally {
            if (statement != null) {
                statement.close();
            }
        }
    }
}
