package JDBCConnector.ActionWithRecordTable;

import JDBCConnector.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by oktopus on 07.10.15.
 */
public class SelectRecordInTable {
    JDBCConnector jdbc = new JDBCConnector();
    Reader reader = new Reader();
    Connection connection;

    public void selectInTable() throws SQLException {

        Statement statement = null;
        System.out.println("Enter tableName where do you want SelectRecordInTable:");
        String tableName = reader.Reader();
        String selectInTable = "SELECT * FROM " + tableName;

        try {
            connection = jdbc.isConnected();
            statement = connection.createStatement();
            System.out.println(selectInTable);
            statement.execute(selectInTable);
            ResultSet rs = statement.executeQuery(selectInTable);
            List<Object> list = new ArrayList<>();
            System.out.println("_________________________");
            System.out.println("|ID|" + " USERNAME|" + "  SURNAME |");
            System.out.println("-------------------------");

            while (rs.next()) {
                String id = rs.getString("ID");
                String username = rs.getString("USERNAME");
                String surname = rs.getString("SURNAME");
                list.add(id);
                list.add(username);
                list.add(surname);

                System.out.print("|" + id + " |" + " ");
                System.out.print(username + "|" + " ");
                System.out.print(surname + "|" + " ");
                System.out.println();
            }
            System.out.println("-------------------------");

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                statement.close();
            }
        }
    }
}
