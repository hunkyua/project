package ua.com.hunkyua.sqlcmd.dao.table;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ua.com.hunkyua.sqlcmd.dao.JDBCDatabaseManager;

import java.sql.SQLException;

/**
 * Created by Hunky on 20.11.2015.
 */
public class TableSizeTest {
    private JDBCDatabaseManager jdbcDatabaseManager;


    @Before
    public void setup () throws SQLException, ClassNotFoundException {
        jdbcDatabaseManager = new JDBCDatabaseManager();
        jdbcDatabaseManager.connect("postgres", "postgres", "1336");
    }

    @Test
    public void testGetTableSize() throws Exception {
        jdbcDatabaseManager.getTableSize("people");
        String actual = jdbcDatabaseManager.size;
        Assert.assertEquals("Size of table:\"people\" = 12", actual);
    }
}