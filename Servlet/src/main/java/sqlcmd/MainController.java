package sqlcmd;

import sqlcmd.command.crud.*;
import sqlcmd.command.table.*;
import sqlcmd.command.Exit;
import sqlcmd.command.Help;
import sqlcmd.command.Unsupported;

import java.util.Arrays;
import java.util.List;

/**
 * Created by oktopus on 10.10.2015.
 */
public class MainController {

    private List<Command> commands;

    private Connector connector;
    private View view;

    public MainController(View view, Connector connector) {
        this.connector = connector;
        this.view = view;

        commands = Arrays.asList(new Help(view),
                new CreateTable(connector, view),
                new DeleteTable(connector, view),
                new InsertRecordInTable(connector, view),
                new UpdateRecordInTable(connector, view),
                new DeleteRecordInTable(connector, view),
                new SelectRecordInTable(connector, view),
                new GetAllTableNames(connector, view),
                new GetTableSize(connector, view),
                new Exit(view),
                new Unsupported(view));

    }

    public void run() {
        welcome();

        boolean connected = connector.connect();

        try {
            while (connected) {
                view.write("Enter command 1...9 (or 0 for help) :");
                int choose = Integer.parseInt(view.read());

                for (Command command : commands) {
                    if (command.canExecute(choose)) {
                        command.execute();
                        if (command instanceof Exit) { // TODO think about it
                            connected = false;
                        }
                        break;
                    }
                }
            }
        } catch (Exception e) {
            view.write("Oups! Something wrong: " + e.getMessage());
        }
    }

    private void welcome() {
        view.write("Welcome to: ");
        view.write("     __________________________   ");
        view.write("    /  ssss   qqqq      ll    /   ");
        view.write("   / ss     qq  qq     ll    /    ");
        view.write("  /  sss   qq  qq     ll    /     ");
        view.write(" /    ss  qq  qq     ll    /      ");
        view.write("/  ssss   qqqq qqq  lllll /       ");
        view.write("--------------------------        ");
    }
}
