package sqlcmd.command.crud;

import sqlcmd.JDBCConnector;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

/**
 * Created by Hunky on 15.11.2015.
 */
public class RecordSelect {
    public static String error = "";
    public static String doesNotExist = "";
    public static String line2= "";
    public static Map<Integer, String> line1 = new HashMap<>();
    public static List<String> list = new ArrayList<>();


    public void selectRecordInTable(String tableName, String select) throws ClassNotFoundException, SQLException {
        Statement statement = null;
        String selectInTable = "SELECT " + select + " FROM " + tableName;

        try {
            doesNotExist = String.format("Record in table:\"%s\"", tableName);
            error = "";
            statement = JDBCConnector.getConnection().createStatement();
            statement.execute(selectInTable);
            ResultSet rs = statement.executeQuery(selectInTable);
            line2 = "<td>ID</td><td>USERNAME</td><td>SURNAME</td><tr></tr>";
            list.add(line2);

            while (rs.next()) {
                String id = rs.getString("ID");
                String username = rs.getString("USERNAME");
                String surname = rs.getString("SURNAME");
                line1.put(Integer.parseInt(id), username + ", " + surname);
                String line = "<p align=\"center\">" + "<td>" + id  + "</td>" + " "+ "<td>" + username + "</td>" + " "
                        + "<td>" + surname + "</td>" + "<tr>"+ "</tr>" + "</p>" ;

                list.add(line);
            }

        } catch (SQLException e) {
            doesNotExist = String.format("Sorry, but record in table:\"%s\" can't be selected. Try again", tableName);
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
