package sqlcmd.command.table;

import sqlcmd.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by oktopus on 07.10.15.
 */
public class CreateTable implements Command {

    private Connector connector;
    private View view;

    public CreateTable(Connector connector, View view) {
        this.connector = connector;
        this.view = view;
    }

    @Override
    public boolean canExecute(int choose) {
        return choose == 1;
    }

    @Override
    public void execute() throws SQLException {
        Statement statement = null;
        view.write("Enter tableName what do you want create:");
        String tableName = view.read();
        String createTableSQL = "CREATE TABLE " + tableName + "("
                + "ID serial PRIMARY KEY,"
                + "USERNAME VARCHAR(20) NOT NULL, "
                + "SURNAME VARCHAR(20) NOT NULL, "
                + "CREATE_DATE DATE NOT NULL "
                + ")";

        try {
            statement = connector.get().createStatement();
            view.write(createTableSQL);
            statement.execute(createTableSQL);
            view.write("Create table \"test\" is coplete!");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                statement.close();
            }
        }
    }
}
