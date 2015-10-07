package ActionWithTable;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by oktopus on 07.10.15.
 */
public class CreateTable {
    JDBCConnector jdbc = new JDBCConnector();
    protected void createDBUserTable() throws SQLException {
        Connection connection = null;
        Statement statement = null;

        String createTableSQL = "CREATE TABLE TEST("
                + "ID serial PRIMARY KEY,"
                + "USERNAME VARCHAR(20) NOT NULL, "
                + "SURNAME VARCHAR(20) NOT NULL, "
                + "CREATE_DATE DATE NOT NULL "
                + ")";

        try {
            connection = jdbc.getDBConnection();
            statement = connection.createStatement();
            System.out.println(createTableSQL);
            statement.execute(createTableSQL);
            System.out.println("Table \"test\" is created!");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                statement.close();
            }
        }
    }
}
