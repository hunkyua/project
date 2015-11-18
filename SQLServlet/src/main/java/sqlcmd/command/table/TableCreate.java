package sqlcmd.command.table;

import sqlcmd.JDBCConnector;

import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Hunky on 15.11.2015.
 */
public class TableCreate {
    public static String error = "";
    public static String doesNotExist = "";
    public static void CreateTable(String tableName) throws SQLException, ClassNotFoundException {
        Statement statement = null;

        String createTableSQL = "CREATE TABLE " + tableName + "("
                + "ID serial PRIMARY KEY,"
                + "USERNAME VARCHAR(20) NOT NULL, "
                + "SURNAME VARCHAR(20) NOT NULL "
                + ")";
        try {
            doesNotExist = "";
            error = "";
            statement = JDBCConnector.getConnection().createStatement();
            statement.execute(createTableSQL);
        } catch (SQLException e) {
            doesNotExist = e.getMessage();
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
