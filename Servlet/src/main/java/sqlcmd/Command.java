package sqlcmd;

import java.sql.SQLException;

/**
 * Created by oktopus on 10.10.2015.
 */
public interface Command {

    boolean canExecute(int choose);

    void execute() throws SQLException;
}
