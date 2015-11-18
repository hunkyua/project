package sqlcmd.command.crud;

import sqlcmd.JDBCConnector;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hunky on 15.11.2015.
 */
public class SelectRecord {
    public static String error = "";
    public static String doesNotExist = "";
    public static String line = "";
    public static String line2= "";
    public static List<Object> list = new ArrayList<>();
    public static List<String> list2 = new ArrayList<>();

    public static void SelectRecordInTable(String tableName, String select) throws ClassNotFoundException, SQLException {
        Statement statement = null;
        String selectInTable = "SELECT " + select + " FROM " + tableName;

        try {
            doesNotExist = "";
            error = "";
            statement = JDBCConnector.getConnection().createStatement();
            statement.execute(selectInTable);
            ResultSet rs = statement.executeQuery(selectInTable);
            line2 = "<td>ID</td><td>USERNAME</td><td>SURNAME</td><tr></tr>";
            list2.add(line2);

            while (rs.next()) {

                String id = rs.getString("ID");
                String username = rs.getString("USERNAME");
                String surname = rs.getString("SURNAME");
                if (!(id.equals(null) || id.equals(""))) {
                list.add(id);
                }
                if (!(username.equals(null) || username.equals(""))) {
                    list.add(username);
                }
                if (!(surname.equals(null) || surname.equals(""))) {
                    list.add(surname);
                }
                if (id.equals(null) || id.equals("")) {
                    line = username + "</td>" + " " + "<td>" + surname + "</td>" + "<tr>"+ "</tr>" + "</p>" ;
                }
                if (username.equals(null) || username.equals("")) {
                    line = "<p align=\"center\">" + "<td>" + id  + "</td>" + " "+ "<td>" + surname + "</td>" + "<tr>"+ "</tr>" + "</p>" ;                }
                if (surname.equals(null) || surname.equals("")) {
                    line = "<p align=\"center\">" + "<td>" + id  + "</td>" + " "+ "<td>" + username + "</td>";
                }
                else {
                line = "<p align=\"center\">" + "<td>" + id  + "</td>" + " "+ "<td>" + username + "</td>" + " "
                        + "<td>" + surname + "</td>" + "<tr>"+ "</tr>" + "</p>" ;
                }
                list2.add(line);

            }
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
