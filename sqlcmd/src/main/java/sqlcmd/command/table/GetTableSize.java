package sqlcmd.command.table;

import sqlcmd.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by oktopus on 09.10.15.
 */
public class GetTableSize implements Command {

    private Connector connector;
    private View view;

    public GetTableSize(Connector connector, View view) {
        this.connector = connector;
        this.view = view;
    }

    @Override
    public boolean canExecute(int choose) {
        return choose == 8;
    }

    @Override
    public void execute() throws SQLException {
        try {
            view.write("Enter tableName what do you want getTableSize:");
            String tableName = view.read();
            Statement stmt = connector.get().createStatement();
            ResultSet rsCount = stmt.executeQuery("SELECT COUNT (*) FROM " + tableName);
            rsCount.next();
            int size = rsCount.getInt(1);
            rsCount.close();
            view.write("***********************************");
            view.write("** Quantity of records in table = " + size + " **");
            view.write("***********************************");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
