package sqlcmd.command.db;

import sqlcmd.JDBCConnector;

import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Hunky on 17.11.2015.
 */
public class CreateUser {
    public static String error = "";
    public static String doesNotExist = "";
    public static void UserCreate(String username, String password) throws SQLException, ClassNotFoundException {
        Statement statement = null;

        String createUserSQL = "CREATE USER " + username + " PASSWORD " + "'" + password + "'";
        try {
            doesNotExist = "";
            error = "";
            statement = JDBCConnector.getConnection().createStatement();
            statement.execute(createUserSQL);
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

