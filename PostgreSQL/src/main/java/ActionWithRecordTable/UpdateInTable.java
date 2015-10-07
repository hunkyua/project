package ActionWithRecordTable;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by oktopus on 07.10.15.
 */
public class UpdateInTable {
    JDBCConnector jdbc = new JDBCConnector();
    protected void updateInTable() throws SQLException {
        Connection connection = null;
        Statement statement = null;

        String updateInTable = "UPDATE TEST"
                + " SET USERNAME = '}|{oI7a', SURNAME = 'Pupkina' "
                + "WHERE ID = 1";

        try {
            connection = jdbc.getDBConnection();
            statement = connection.createStatement();
            System.out.println(updateInTable);
            statement.execute(updateInTable);
            System.out.println("ActionWithRecordTable.UpdateInTable \"test\" is complete!");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                statement.close();
            }
        }
    }
}
