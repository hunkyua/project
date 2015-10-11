package sqlcmd.command.crud;

import sqlcmd.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by oktopus on 07.10.15.
 */
public class DeleteRecordInTable implements Command {

    private Connector connector;
    private View view;

    public DeleteRecordInTable(Connector connector, View view) {
        this.connector = connector;
        this.view = view;
    }

    @Override
    public boolean canExecute(int choose) {
        return choose == 5;
    }

    @Override
    public void execute() throws SQLException {
        Statement statement = null;
        view.write("Enter tableName where do you want DeleteRecordInTable:");
        String tableName = view.read();
        String deleteInTable = "DELETE FROM " + tableName + " WHERE ID = 4";

        try {
            statement = connector.get().createStatement();
            view.write(deleteInTable);
            statement.execute(deleteInTable);
            view.write("Delete in table \"test\" is complete!");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                statement.close();
            }
        }
    }
}
