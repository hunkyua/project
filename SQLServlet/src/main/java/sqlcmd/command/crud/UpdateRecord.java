package sqlcmd.command.crud;

import sqlcmd.JDBCConnector;

import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Hunky on 15.11.2015.
 */
public class UpdateRecord {
    public static String error = "";
    public static String doesNotExist = "";

    public static void UpdateRecordInTable(String tableName, int id, String username, String surname) throws ClassNotFoundException, SQLException {
        Statement statement = null;
        String updateInTable = "UPDATE " + tableName
                + " SET USERNAME = '"+username+"'"+","
                + " SURNAME = '"+surname+"' "
                + "WHERE ID ="+id;
        try {
            doesNotExist = String.format("Record id:\"%s\" in table:\"%s\" updated", id, tableName );
            error = "";
            statement = JDBCConnector.getConnection().createStatement();
            statement.execute(updateInTable);
        } catch (SQLException e) {
            doesNotExist = String.format("Sorry, but record id: \"%s\" in table:\"%s\" can't be updated. Try again", id, tableName);
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
