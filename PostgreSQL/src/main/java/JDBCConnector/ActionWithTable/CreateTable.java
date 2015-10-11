package JDBCConnector.ActionWithTable;

import JDBCConnector.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by oktopus on 07.10.15.
 */
public class CreateTable {
    JDBCConnector jdbc = new JDBCConnector();
    Reader reader = new Reader();
    Connection connection;

    public void createDBUserTable() throws SQLException {
        Statement statement = null;
        System.out.println("Enter tableName what do you want create:");
        String tableName = reader.Reader();
        String createTableSQL = "CREATE TABLE " + tableName + "("
                + "ID serial PRIMARY KEY,"
                + "USERNAME VARCHAR(20) NOT NULL, "
                + "SURNAME VARCHAR(20) NOT NULL, "
                + "CREATE_DATE DATE NOT NULL "
                + ")";

        try {
            connection = jdbc.isConnected();
            statement = connection.createStatement();
            System.out.println(createTableSQL);
            statement.execute(createTableSQL);
            System.out.println("Create table \"test\" is coplete!");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                statement.close();
            }
        }
    }
}
