package sqlcmd.command.db;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import sqlcmd.JDBCConnector;

import java.sql.SQLException;

/**
 * Created by Hunky on 22.11.2015.
 */
public class ShowDBTest {
    private ShowDB showDB;

    @Before
    public void setup () throws SQLException, ClassNotFoundException {
        showDB = new ShowDB();
        new JDBCConnector().getConnection();
    }

    @Test
    public void testDBShow() throws Exception {
        String expected = "[postgres, test, lol]";
        showDB.dbShow();
        String actual = showDB.result.toString();
        Assert.assertEquals(expected, actual);
    }
}