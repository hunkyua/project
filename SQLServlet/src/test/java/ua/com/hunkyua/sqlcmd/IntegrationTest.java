package ua.com.hunkyua.sqlcmd;

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
        assertOut("");
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
                "Please enter name: \n" +
                "sqlcmd\n" +
                "Please enter user\n" +
                "postgres\n" +
                "Please enter password: \n" +
                "postgres\n" +
                "Connection complete!\n" +
                "Enter dao 1...9 (or 0 for help) :\n" +
                "0\n" +
                "You can choose next dao:\n" +
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
                "Please choose dao:\n" +
                "Enter dao 1...9 (or 0 for help) :\n" +
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
        assertOut(tableName);
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
