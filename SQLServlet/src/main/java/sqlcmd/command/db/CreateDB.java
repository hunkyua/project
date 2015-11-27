package sqlcmd.command.db;

import sqlcmd.JDBCConnector;

import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Hunky on 17.11.2015.
 */
public class CreateDB {
    public static String error = "";
    public static String doesNotExist = "";
    public static void dbCreate(String db_name, String db_user) throws SQLException, ClassNotFoundException {
        Statement statement = null;

        String createDB = "CREATE DATABASE " + db_name + " OWNER " + db_user;
        try {
            doesNotExist = "";
            error = "";
            statement = JDBCConnector.getConnection().createStatement();
            statement.execute(createDB);
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

