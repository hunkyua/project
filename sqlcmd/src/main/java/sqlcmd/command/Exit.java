package sqlcmd.command;

import sqlcmd.Command;
import sqlcmd.View;
import sqlcmd.ViewImpl;

import java.sql.SQLException;

/**
 * Created by oktopus on 09.10.15.
 */
public class Exit implements Command {

    private View view;

    public Exit(View view) {
        this.view = view;
    }

    @Override
    public boolean canExecute(int choose) {
        return choose == 9;
    }

    @Override
    public void execute() throws SQLException {
        view.write("*****************************");
        view.write("*********  Goodbye  *********");
        view.write("*****************************");
    }

}
