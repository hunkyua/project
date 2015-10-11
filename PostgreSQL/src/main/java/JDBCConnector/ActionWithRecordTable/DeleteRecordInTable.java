package JDBCConnector.ActionWithRecordTable;

import JDBCConnector.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by oktopus on 07.10.15.
 */
public class DeleteRecordInTable {
    JDBCConnector jdbc = new JDBCConnector();
    Reader reader = new Reader();
    Connection connection;

    public void deleteInTable() throws SQLException {
        Statement statement = null;
        System.out.println("Enter tableName where do you want DeleteRecordInTable:");
        String tableName = reader.Reader();
        String deleteInTable = "DELETE FROM " + tableName + " WHERE ID = 4";

        try {
            connection = jdbc.isConnected();
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
        }
    }
}
