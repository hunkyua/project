package ua.com.hunkyua.sqlcmd.dao.db;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ua.com.hunkyua.sqlcmd.dao.JDBCDatabaseManager;

import java.sql.SQLException;

/**
 * Created by Hunky on 22.11.2015.
 */
public class ShowDBTest {
    private JDBCDatabaseManager jdbcDatabaseManager;


    @Before
    public void setup () throws SQLException, ClassNotFoundException {
        jdbcDatabaseManager = new JDBCDatabaseManager();
        jdbcDatabaseManager.connect("postgres", "postgres", "1336");
    }

    @Test
    public void testDBShow() throws Exception {
        String expected = "[postgres, test, lol]";
        jdbcDatabaseManager.dbShow();
        String actual = jdbcDatabaseManager.result.toString();
        Assert.assertEquals(expected, actual);
    }
}