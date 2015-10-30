package sqlcmd.command.table;

import sqlcmd.*;
import java.sql.SQLException;
import java.sql.Statement;


/**
 * Created by oktopus on 07.10.15.
 */
public class DeleteTable implements Command {

    private Connector connector;
    private View view;

    public DeleteTable(Connector connector, View view) {
        this.connector = connector;
        this.view = view;
    }

    @Override
    public boolean canExecute(int choose) {
        return choose == 2;
    }

    @Override
    public void execute() throws SQLException {
        Statement statement = null;
        view.write("Enter tableName what do you want delete:");
        String tableName = view.read();
        String deleteTableSQL = "DROP TABLE " + tableName;

        try {
            statement = connector.get().createStatement();
            view.write(deleteTableSQL);
            statement.execute(deleteTableSQL);
            view.write("Delete table \"test\" is complete!");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                statement.close();
            }
        }
    }


}
