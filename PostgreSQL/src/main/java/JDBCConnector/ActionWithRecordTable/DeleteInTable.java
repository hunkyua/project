package JDBCConnector.ActionWithRecordTable;

import JDBCConnector.JDBCConnector;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by oktopus on 07.10.15.
 */
public class DeleteInTable {
    JDBCConnector jdbc = new JDBCConnector();
    public void deleteInTable() throws SQLException {
        Connection connection = null;
        Statement statement = null;

        String deleteInTable = "DELETE FROM TEST WHERE ID = 4";

        try {
            connection = jdbc.getDBConnection();
            statement = connection.createStatement();
            System.out.println(deleteInTable);
            statement.execute(deleteInTable);
            System.out.println("Delete in table \"test\" is complete!");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                statement.close();
            }

            if (connection != null) {
                connection.close();
            }
        }
    }
}
