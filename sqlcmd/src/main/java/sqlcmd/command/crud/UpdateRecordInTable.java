package sqlcmd.command.crud;

import sqlcmd.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by oktopus on 07.10.15.
 */
public class UpdateRecordInTable implements Command {

    private Connector connector;
    private View view;

    public UpdateRecordInTable(Connector connector, View view) {
        this.connector = connector;
        this.view = view;
    }

    @Override
    public boolean canExecute(int choose) {
        return choose == 4;
    }

    @Override
    public void execute() throws SQLException {
        Statement statement = null;
        view.write("Enter tableName where do you want UpdateRecordInTable:");
        String tableName = view.read();
        String updateInTable = "UPDATE " + tableName
                + " SET USERNAME = '}|{oI7a', SURNAME = 'Pupkina' "
                + "WHERE ID = 1";

        try {
            statement = connector.get().createStatement();
            view.write(updateInTable);
            statement.execute(updateInTable);
            view.write("Update in table \"test\" is complete!");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                statement.close();
            }
        }
    }
}
