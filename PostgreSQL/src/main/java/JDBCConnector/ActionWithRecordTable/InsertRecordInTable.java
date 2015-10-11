package JDBCConnector.ActionWithRecordTable;

import JDBCConnector.*;

import java.sql.Connection;
import java.util.Date;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Created by oktopus on 07.10.15.
 */
public class InsertRecordInTable {
    private static final DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

    JDBCConnector jdbc = new JDBCConnector();
    Reader reader = new Reader();
    Connection connection;

    public void insertRecordInTable() throws SQLException {

        Statement statement = null;
        System.out.println("Enter tableName where do you want InsertRecordInTable:");
        String tableName = reader.Reader();
        String insertInTable = "INSERT INTO " + tableName
                + " (USERNAME, SURNAME, CREATE_DATE) " + "VALUES"
                + "('Valentin', 'Opanasyuk'," + "to_date('"
                + getCurrentTimeStamp() + "', 'yyyy/mm/dd hh24:mi:ss'))";

        try {
            connection = jdbc.isConnected();
            statement = connection.createStatement();
            System.out.println(insertInTable);
            statement.execute(insertInTable);
            System.out.println("Insert in table \"test\" complete!");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                statement.close();
            }
        }
    }

    public String getCurrentTimeStamp() {
        Date today = new Date();
        return dateFormat.format(today.getTime());
    }
}
