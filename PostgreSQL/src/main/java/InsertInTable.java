import java.sql.Connection;
import java.util.Date;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Created by oktopus on 07.10.15.
 */
public class InsertInTable {
    private static final DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

    JDBCConnector jdbc = new JDBCConnector();
    protected void insertRecordInTable() throws SQLException {
        Connection connection = null;
        Statement statement = null;

        String insertInTable = "INSERT INTO TEST"
                + "(USERNAME, SURNAME, CREATE_DATE) " + "VALUES"
                + "('Valentin', 'Opanasyuk'," + "to_date('"
                + getCurrentTimeStamp() + "', 'yyyy/mm/dd hh24:mi:ss'))";

        try {
            connection = jdbc.getDBConnection();
            statement = connection.createStatement();
            System.out.println(insertInTable);
            statement.execute(insertInTable);
            System.out.println("InsertInTable in table \"test\" complete!");
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
