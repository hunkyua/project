package JDBCConnector.ActionWithTable;

import JDBCConnector.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by oktopus on 09.10.15.
 */
public class GetTableSize {
    JDBCConnector jdbc = new JDBCConnector();
    Reader reader = new Reader();
    Connection connection;

    public void getTableSize() {
        try {
            connection = jdbc.isConnected();
            System.out.println("Enter tableName what do you want getTableSize:");
            String tableName = reader.Reader();
            Statement stmt = connection.createStatement();
            ResultSet rsCount = stmt.executeQuery("SELECT COUNT (*) FROM " + tableName);
            rsCount.next();
            int size = rsCount.getInt(1);
            rsCount.close();
            System.out.println("***********************************");
            System.out.println("** Quantity of records in table = " + size + " **");
            System.out.println("***********************************");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
