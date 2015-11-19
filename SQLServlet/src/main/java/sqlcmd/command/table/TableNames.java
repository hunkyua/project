package sqlcmd.command.table;

import sqlcmd.JDBCConnector;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;

/**
 * Created by Hunky on 15.11.2015.
 */
public class TableNames {
    public static String error = "";
    public static String doesNotExist = "";
    public static String[] tables = {"*Please enter Show tables*"};

    public static String toString(String[] tables) {
        if (tables == null)
            return "null";

        int iMax = tables.length - 1;
        if (iMax == -1)
            return "[]";

        StringBuilder b = new StringBuilder();
        b.append('[');
        for (int i = 0; ; i++) {
            b.append(String.valueOf(tables[i]));
            if (i == iMax)
                return b.append(']').toString();
            b.append(", ");
        }
    }

    public static void GetAllTableNames() throws ClassNotFoundException, SQLException {
        Statement statement = null;
        try {
            doesNotExist = "";
            error = "";
            statement = JDBCConnector.getConnection().createStatement();
            ResultSet rs = statement.executeQuery("Select table_name FROM information_schema.tables " +
                    "WHERE table_schema = 'public' ORDER BY table_name ASC");
//            ResultSet rs = stmt.executeQuery("Select table_name FROM information_schema.tables WHERE table_schema = 'test'");

            int index = 0;
            tables = new String[100];
            while (rs.next()) {
                tables[index++] = rs.getString("table_name");
            }
            tables = Arrays.copyOf(tables, index, String[].class);
            rs.close();
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
