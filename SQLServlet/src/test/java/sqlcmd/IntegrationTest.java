package sqlcmd;

import org.junit.Test;
import java.util.Random;
import static org.junit.Assert.assertEquals;

/**
 * Created by oktopus on 10.10.2015.
 */
public class IntegrationTest {

    private ConsoleMock console = new ConsoleMock();

    @Test
    public void testConnectionError() throws Exception {
        // given
        enterValidDbConnection("errorUser");

        // when

        // then
        assertOut("Welcome to: \n" +
                "     __________________________   \n" +
                "    /  ssss   qqqq      ll    /   \n" +
                "   / ss     qq  qq     ll    /    \n" +
                "  /  sss   qq  qq     ll    /     \n" +
                " /    ss  qq  qq     ll    /      \n" +
                "/  ssss   qqqq qqq  lllll /       \n" +
                "--------------------------        \n" +
                "Please enter db_name: \n" +
                "sqlcmd\n" +
                "Please enter db_user\n" +
                "errorUser\n" +
                "Please enter db_password: \n" +
                "errorUser\n" +
                "Connection failed: FATAL: password authentication failed for user \"errorUser\"\n");
    }

    @Test
    public void testSuccessfulConnection() throws Exception {
        // given
        enterValidDbConnection("postgres");
        enterHelpCommand();
        enterExitCommand();

        // when


        // then
        assertOut("Welcome to: \n" +
                "     __________________________   \n" +
                "    /  ssss   qqqq      ll    /   \n" +
                "   / ss     qq  qq     ll    /    \n" +
                "  /  sss   qq  qq     ll    /     \n" +
                " /    ss  qq  qq     ll    /      \n" +
                "/  ssss   qqqq qqq  lllll /       \n" +
                "--------------------------        \n" +
                "Please enter db_name: \n" +
                "sqlcmd\n" +
                "Please enter db_user\n" +
                "postgres\n" +
                "Please enter db_password: \n" +
                "postgres\n" +
                "Connection complete!\n" +
                "Enter command 1...9 (or 0 for help) :\n" +
                "0\n" +
                "You can choose next command:\n" +
                "****************************\n" +
                "0: HELP\n" +
                "1: Create table\n" +
                "2: Delete table\n" +
                "3: Insert record in table\n" +
                "4: Update record in table\n" +
                "5: Delete record in table\n" +
                "6: Select record in table\n" +
                "7: Show all tables in database\n" +
                "8: Show size tables in database\n" +
                "9: Exit\n" +
                "Please choose command:\n" +
                "Enter command 1...9 (or 0 for help) :\n" +
                "9\n" +
                "*****************************\n" +
                "*********  Goodbye  *********\n" +
                "*****************************\n");
    }

    @Test
    public void testCreateAndDropTable() throws Exception {
        // given
        enterValidDbConnection("postgres");
        String tableName = enterCreateCommand();
        enterShowTablesCommand();
        enterGetTableSize(tableName);
        enterDropTableCommand(tableName);
        enterShowTablesCommand();
        enterBadCommand();
        enterExitCommand();

        // when


        // then
        assertOut("Welcome to: \n" +
                "     __________________________   \n" +
                "    /  ssss   qqqq      ll    /   \n" +
                "   / ss     qq  qq     ll    /    \n" +
                "  /  sss   qq  qq     ll    /     \n" +
                " /    ss  qq  qq     ll    /      \n" +
                "/  ssss   qqqq qqq  lllll /       \n" +
                "--------------------------        \n" +
                "Please enter db_name: \n" +
                "sqlcmd\n" +
                "Please enter db_user\n" +
                "postgres\n" +
                "Please enter db_password: \n" +
                "postgres\n" +
                "Connection complete!\n" +
                "Enter command 1...9 (or 0 for help) :\n" +
                "1\n" +
                "Enter tableName what do you want create:\n" +
                "%s\n" +
                "CREATE TABLE %s(ID serial PRIMARY KEY,USERNAME VARCHAR(20) NOT NULL, SURNAME VARCHAR(20) NOT NULL, CREATE_DATE DATE NOT NULL )\n" +
                "Create table \"test\" is coplete!\n" +
                "Enter command 1...9 (or 0 for help) :\n" +
                "7\n" +
                "[%s, test, user]\n" +
                "Enter command 1...9 (or 0 for help) :\n" +
                "8\n" +
                "Enter tableName what do you want getTableSize:\n" +
                "%s\n" +
                "***********************************\n" +
                "** Quantity of records in table = 0 **\n" +
                "***********************************\n" +
                "Enter command 1...9 (or 0 for help) :\n" +
                "2\n" +
                "Enter tableName what do you want delete:\n" +
                "%s\n" +
                "DROP TABLE %s\n" +
                "Delete table \"test\" is complete!\n" +
                "Enter command 1...9 (or 0 for help) :\n" +
                "7\n" +
                "[test, user]\n" +
                "Enter command 1...9 (or 0 for help) :\n" +
                "888\n" +
                "******************************\n" +
                "**** Unsupported command *****\n" +
                "******************************\n" +
                "Enter command 1...9 (or 0 for help) :\n" +
                "9\n" +
                "*****************************\n" +
                "*********  Goodbye  *********\n" +
                "*****************************\n", tableName);
    }

    private void enterBadCommand() {
        console.addIn("888");
    }

    private void enterGetTableSize(String tableName) {
        console.addIn("8");
        console.addIn(tableName);
    }

    private void enterDropTableCommand(String tableName) {
        console.addIn("2");
        console.addIn(tableName);
    }

    private void enterShowTablesCommand() {
        console.addIn("7");
    }

    private String enterCreateCommand() {
        console.addIn("1");
        String tableName = "table" + Math.abs(new Random().nextInt());
        console.addIn(tableName);
        return tableName;
    }

    private void enterExitCommand() {
        console.addIn("9");
    }

    private void enterHelpCommand() {
        console.addIn("0");
    }

    private void enterValidDbConnection(String postgres) {
        console.addIn("sqlcmd");
        console.addIn(postgres);
        console.addIn(postgres);
    }

    private void assertOut(String expected, String... parameters) {
        String string = expected.replaceAll("\\n", "\r\n");
        if (parameters.length > 0) {
            string = string.replaceAll("%s", parameters[0]);
        }
        assertEquals(string, console.getOut());
    }


}
