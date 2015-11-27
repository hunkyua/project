package sqlcmd.command.table;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import sqlcmd.JDBCConnector;

import java.sql.SQLException;
import java.util.Arrays;

/**
 * Created by Hunky on 20.11.2015.
 */
public class TableNamesTest {

    private TableNames tableNames;
    private TableCreate tableCreate;

    @Before
    public void setup () throws SQLException, ClassNotFoundException {
        tableNames = new TableNames();
        tableCreate = new TableCreate();
        new JDBCConnector().getConnection();
    }

    @Test
    public void testToString() throws Exception {
        String expected = "[users, roots, people]";
        String actual = TableNames.toString(new String[] {"users", "roots", "people"});
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testGetAllTableNames() throws Exception {
        tableNames.getAllTableNames();
        String[] actual = tableNames.tables;
        String expected = "[people, users]";
        Assert.assertEquals(expected, Arrays.toString(actual));
    }
}