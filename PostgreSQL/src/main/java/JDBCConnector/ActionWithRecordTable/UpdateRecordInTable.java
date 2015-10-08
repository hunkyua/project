package JDBCConnector.ActionWithRecordTable;

import JDBCConnector.JDBCConnector;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by oktopus on 07.10.15.
 */
public class UpdateRecordInTable {
    JDBCConnector jdbc = new JDBCConnector();
    Connection connection;

    public void updateInTable() throws SQLException {
        Statement statement = null;

        String updateInTable = "UPDATE TEST"
                + " SET USERNAME = '}|{oI7a', SURNAME = 'Pupkina' "
                + "WHERE ID = 1";

        try {
            connection = jdbc.isConnected();
            statement = connection.createStatement();
            System.out.println(updateInTable);
            statement.execute(updateInTable);
            System.out.println("Update in table \"test\" is complete!");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                statement.close();
            }
        }
    }
}
