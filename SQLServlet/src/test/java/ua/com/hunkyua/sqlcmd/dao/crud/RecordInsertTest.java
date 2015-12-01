package ua.com.hunkyua.sqlcmd.dao.crud;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ua.com.hunkyua.sqlcmd.dao.JDBCDatabaseManager;

import java.sql.SQLException;
import java.util.Map;

/**
 * Created by Hunky on 22.11.2015.
 */
public class RecordInsertTest {
    private JDBCDatabaseManager jdbcDatabaseManager;


    @Before
    public void setup () throws SQLException, ClassNotFoundException {
        jdbcDatabaseManager = new JDBCDatabaseManager();
        jdbcDatabaseManager.connect("postgres", "postgres", "1336");
    }

    @Test
    public void testInsertRecordInTable() throws Exception {
        String actual = "";
        String expected = "abdula, mahmud";
        jdbcDatabaseManager.insertRecordInTable("people", "abdula", "mahmud");
        jdbcDatabaseManager.selectRecordInTable("people", "*");
        Map<Integer, String> rec = jdbcDatabaseManager.line1;
            for (int id : rec.keySet()) {
                if (rec.get(id).equals(expected)) {
                    actual = expected;
                }
            }

        Assert.assertEquals(expected, actual);
    }
}