package ua.com.hunkyua.sqlcmd.dao.crud;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ua.com.hunkyua.sqlcmd.dao.JDBCDatabaseManager;

import java.sql.SQLException;

/**
 * Created by Hunky on 22.11.2015.
 */
public class RecordUpdateTest {

    private JDBCDatabaseManager jdbcDatabaseManager;


    @Before
    public void setup () throws SQLException, ClassNotFoundException {
        jdbcDatabaseManager = new JDBCDatabaseManager();
        jdbcDatabaseManager.connect("postgres", "postgres", "1336");
    }

    @Test
    public void testUpdateRecordInTable() throws Exception {
        jdbcDatabaseManager.updateRecordInTable("people", 1, "Sasha", "Grey");
        jdbcDatabaseManager.selectRecordInTable("people", "*");
        String expected = "Sasha, Grey";
        String actual = jdbcDatabaseManager.line1.get(1);

        Assert.assertEquals(expected, actual);
    }
}