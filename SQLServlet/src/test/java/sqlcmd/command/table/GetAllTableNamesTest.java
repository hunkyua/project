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
public class GetAllTableNamesTest {

    private TableNames tableNames;

    @Before
    public void setup () throws SQLException, ClassNotFoundException {
        tableNames = new TableNames();
    }

    @Test
    public void testToString() throws Exception {
        String expected = "[users, roots]";
        String actual = TableNames.toString(new String[] {"users", "roots"});
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testGetAllTableNames() throws Exception {
        new JDBCConnector("postgres", "postgres", "1336").getConnection();
        tableNames.GetAllTableNames();
        String[] actual = tableNames.tables;
        Assert.assertEquals("[lll, people, tab1, users, vvv]", Arrays.toString(actual));
    }
}