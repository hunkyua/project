package JDBCConnector.ActionWithTable;

import JDBCConnector.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;


/**
 * Created by oktopus on 07.10.15.
 */
public class DeleteTable {
    JDBCConnector jdbc = new JDBCConnector();
    Reader reader = new Reader();
    Connection connection;

    public void deleteTable() throws SQLException {
        Statement statement = null;
        System.out.println("Enter tableName what do you want delete:");
        String tableName = reader.Reader();
        String deleteTableSQL = "DROP TABLE " + tableName;

        try {
            connection = jdbc.isConnected();
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
        }
    }


}
