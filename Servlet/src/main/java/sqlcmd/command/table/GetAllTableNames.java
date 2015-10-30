package sqlcmd.command.table;

import sqlcmd.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;

/**
 * Created by oktopus on 09.10.15.
 */
public class GetAllTableNames implements Command {

    private Connector connector;
    private View view;

    public GetAllTableNames(Connector connector, View view) {
        this.connector = connector;
        this.view = view;
    }

    @Override
    public boolean canExecute(int choose) {
        return choose == 7;
    }

    @Override
    public void execute() throws SQLException {
        try {
            Statement stmt = connector.get().createStatement();
            ResultSet rs = stmt.executeQuery("Select table_name FROM information_schema.tables " +
                    "WHERE table_schema = 'public' ORDER BY table_name ASC");
            String[] tables = new String[100];
            int index = 0;
            while (rs.next()) {
                tables[index++] = rs.getString("table_name");
            }
            tables = Arrays.copyOf(tables, index, String[].class);
            view.write(Arrays.toString(tables));
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
