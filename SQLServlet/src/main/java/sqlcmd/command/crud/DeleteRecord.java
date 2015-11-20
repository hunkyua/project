package sqlcmd.command.crud;

import sqlcmd.JDBCConnector;

import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Hunky on 15.11.2015.
 */
public class DeleteRecord {
    public static String error = "";
    public static String doesNotExist = "";

    public static void DeleteRecordInTable(String tableName, int id) throws SQLException, ClassNotFoundException {
        Statement statement = null;
        String deleteInTable = "DELETE FROM " + tableName + " WHERE ID =" + id;

        try {
            doesNotExist = String.format("Record id:\"%s\" in table:\"%s\" deleted", id, tableName);
            error = "";
            statement = JDBCConnector.getConnection().createStatement();
            statement.execute(deleteInTable);
        } catch (SQLException e) {
            doesNotExist = String.format("Sorry, but record id:\"%s\" in table:\"%s\" can't be deleted. Try again", id, tableName);
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
