package JDBCConnector;

/**
 * Created by oktopus on 09.10.15.
 */
public class Help {
    public void help() {
        System.out.println("You can choose next command:");
        System.out.println("****************************");
        System.out.println("0: HELP");
        System.out.println("1: Create table");
        System.out.println("2: Delete table");
        System.out.println("3: Insert record in table");
        System.out.println("4: Update record in table");
        System.out.println("5: Delete record in table");
        System.out.println("6: Select record in table");
        System.out.println("7: Show all tables in database");
        System.out.println("8: Show size tables in database");
        System.out.println("9: Exit");
        System.out.println("Please choose command:");
    }
}
