package sqlcmd.command.db;

import sqlcmd.JDBCConnector;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hunky on 22.11.2015.
 */
public class ShowDB {
    public static String error = "";
    public static String doesNotExist = "";
    public static List<String> result = new ArrayList<>();

    public void dbShow() throws SQLException, ClassNotFoundException {
        Statement statement = null;

        String showDB = "SELECT datname FROM pg_database WHERE datistemplate = false;";
        try {
            doesNotExist = "";
            error = "";
            statement = JDBCConnector.getConnection().createStatement();
            statement.execute(showDB);
            ResultSet rs = statement.executeQuery(showDB);
            while (rs.next()) {
               String res = rs.getString("datname");
                result.add(res);
            }
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

