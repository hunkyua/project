package sqlcmd.command;

import sqlcmd.Command;
import sqlcmd.View;
import sqlcmd.ViewImpl;

import java.sql.SQLException;

/**
 * Created by oktopus on 09.10.15.
 */
public class Help implements Command {

    private View view;

    public Help(View view) {
        this.view = view;
    }

    @Override
    public boolean canExecute(int choose) {
        return choose == 0;
    }

    @Override
    public void execute() throws SQLException {
        view.write("You can choose next command:");
        view.write("****************************");
        view.write("0: HELP");
        view.write("1: Create table");
        view.write("2: Delete table");
        view.write("3: Insert record in table");
        view.write("4: Update record in table");
        view.write("5: Delete record in table");
        view.write("6: Select record in table");
        view.write("7: Show all tables in database");
        view.write("8: Show size tables in database");
        view.write("9: Exit");
        view.write("Please choose command:");
    }

}
