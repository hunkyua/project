package sqlcmd.command.crud;

import sqlcmd.*;

import java.sql.Connection;
import java.util.Date;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Created by oktopus on 07.10.15.
 */
public class InsertRecordInTable implements Command {
    private static final DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

    private Connector connector;
    private View view;

    public InsertRecordInTable(Connector connector, View view) {
        this.connector = connector;
        this.view = view;
    }

    @Override
    public boolean canExecute(int choose) {
        return choose == 3;
    }

    @Override
    public void execute() throws SQLException {
        Statement statement = null;
        view.write("Enter tableName where do you want InsertRecordInTable:");
        String tableName = view.read();
        String insertInTable = "INSERT INTO " + tableName
                + " (USERNAME, SURNAME, CREATE_DATE) " + "VALUES"
                + "('Valentin', 'Opanasyuk'," + "to_date('"
                + getCurrentTimeStamp() + "', 'yyyy/mm/dd hh24:mi:ss'))";

        try {
            statement = connector.get().createStatement();
            view.write(insertInTable);
            statement.execute(insertInTable);
            view.write("Insert in table \"test\" complete!");
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
