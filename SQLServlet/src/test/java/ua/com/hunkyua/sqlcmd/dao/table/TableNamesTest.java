package ua.com.hunkyua.sqlcmd.dao.table;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ua.com.hunkyua.sqlcmd.dao.JDBCDatabaseManager;

import java.sql.SQLException;
import java.util.Arrays;

/**
 * Created by Hunky on 20.11.2015.
 */
public class TableNamesTest {

    private JDBCDatabaseManager jdbcDatabaseManager;


    @Before
    public void setup () throws SQLException, ClassNotFoundException {
        jdbcDatabaseManager = new JDBCDatabaseManager();
        jdbcDatabaseManager.connect("postgres", "postgres", "1336");
    }

    @Test
    public void testToString() throws Exception {
        String expected = "[users, roots, people]";
        String actual = jdbcDatabaseManager.toString(new String[] {"users", "roots", "people"});
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testGetAllTableNames() throws Exception {
        jdbcDatabaseManager.getAllTableNames();
        String[] actual = jdbcDatabaseManager.tables;
        String expected = "[people, users]";
        Assert.assertEquals(expected, Arrays.toString(actual));
    }
}