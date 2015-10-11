package sqlcmd;


/**
 * Created by oktopus on 10.10.2015.
 */
public class Main {

    public static void main(String[] args) {
        View view = new ViewImpl();
        Connector connector = new JDBCConnector();

        new MainController(view, connector).run();
    }
}

