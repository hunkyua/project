package ua.com.hunkyua.sqlcmd.dao.table;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ua.com.hunkyua.sqlcmd.dao.JDBCDatabaseManager;

import java.sql.SQLException;

/**
 * Created by Hunky on 22.11.2015.
 */
public class TableCreateTest {

    private JDBCDatabaseManager jdbcDatabaseManager;


    @Before
    public void setup () throws SQLException, ClassNotFoundException {
        jdbcDatabaseManager = new JDBCDatabaseManager();
        jdbcDatabaseManager.connect("postgres", "postgres", "1336");
    }

    @Test
    public void testCreateTable() throws Exception {
        String tableName = "test"; //The table what you want create
        String actual = "";
        jdbcDatabaseManager.createTable(tableName);
        jdbcDatabaseManager.getAllTableNames();
        String[] tablesName = jdbcDatabaseManager.tables;
        for (String res : tablesName){
            res.split(",");
            if (res.equals(tableName)){
                actual = tableName;
            }
        }
        String expected = tableName ;
        Assert.assertEquals(expected, actual);
    }
}