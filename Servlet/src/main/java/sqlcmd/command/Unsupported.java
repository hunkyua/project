package sqlcmd.command;

import sqlcmd.Command;
import sqlcmd.View;
import sqlcmd.ViewImpl;

import java.sql.SQLException;

/**
 * Created by oktopus on 10.10.2015.
 */
public class Unsupported implements Command {

    private View view;

    public Unsupported(View view) {
        this.view = view;
    }

    @Override
    public boolean canExecute(int choose) {
        return true;
    }

    @Override
    public void execute() throws SQLException {
        view.write("******************************");
        view.write("**** Unsupported command *****");
        view.write("******************************");
    }
}
