package JDBCConnector.ActionWithTable;

import JDBCConnector.JDBCConnector;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by oktopus on 07.10.15.
 */
public class DeleteTable {
    JDBCConnector jdbc = new JDBCConnector();
    public void deleteTable() throws SQLException {
        Connection connection = null;
        Statement statement = null;

        String deleteTableSQL = "DROP TABLE test";

        try {
            connection = jdbc.getDBConnection();
            statement = connection.createStatement();
            System.out.println(deleteTableSQL);
            statement.execute(deleteTableSQL);
            System.out.println("Delete table \"test\" is complete!");
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
