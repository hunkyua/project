package sqlcmd.command.crud;

import sqlcmd.JDBCConnector;

import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Hunky on 13.11.2015.
 */
public class InsertRecord {
    public static String error = "";
    public static String doesNotExist = "";

    public static void InsertRecordInTable(String tableName, String username, String surname) throws SQLException, ClassNotFoundException {
        Statement statement = null;
        String insertInTable = "INSERT INTO " + tableName
                + " (USERNAME, SURNAME) " + "VALUES"
                + "('"+ username +"', '" + surname + "')";

        try {
            doesNotExist = "";
            error = "";
            statement = JDBCConnector.getConnection().createStatement();
            statement.execute(insertInTable);
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
