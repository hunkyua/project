package sqlcmd.command.crud;

import sqlcmd.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by oktopus on 07.10.15.
 */
public class SelectRecordInTable implements Command {

    private Connector connector;
    private View view;

    public SelectRecordInTable(Connector connector, View view) {
        this.connector = connector;
        this.view = view;
    }

    @Override
    public boolean canExecute(int choose) {
        return choose == 6;
    }

    @Override
    public void execute() throws SQLException {

        Statement statement = null;
        view.write("Enter tableName where do you want SelectRecordInTable:");
        String tableName = view.read();
        String selectInTable = "SELECT * FROM " + tableName;

        try {
            statement = connector.get().createStatement();
            view.write(selectInTable);
            statement.execute(selectInTable);
            ResultSet rs = statement.executeQuery(selectInTable);
            List<Object> list = new ArrayList<>();
            view.write("_________________________");
            view.write("|ID|" + " USERNAME|" + "  SURNAME |");
            view.write("-------------------------");

            while (rs.next()) {
                String id = rs.getString("ID");
                String username = rs.getString("USERNAME");
                String surname = rs.getString("SURNAME");
                list.add(id);
                list.add(username);
                list.add(surname);

                String line = "|" + id + " |" + " " +
                        username + "|" + " " +
                        surname + "|" + " ";
                view.write(line);
            }
            view.write("-------------------------");

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                statement.close();
            }
        }
    }
}
