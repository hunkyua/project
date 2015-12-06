package ua.com.hunkyua.sqlcmd;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ua.com.hunkyua.sqlcmd.dao.JDBCDatabaseManager;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Hunky on 20.11.2015.
 */
public class JDBCDatabaseManagerTest {
    private JDBCDatabaseManager jdbcDatabaseManager;

    @Before
    public void setup() throws IOException {
        String db_name = "postgres";
        String username = "postgres";
        String password = "1336";
        jdbcDatabaseManager = new JDBCDatabaseManager();
        jdbcDatabaseManager.connect(db_name, username,password);
    }

    @Test
    public void testGetConnection() throws Exception {
        Assert.assertEquals(true,true);
    }

    @Test
    public void testIsConnected() throws Exception {
        Assert.assertEquals(true, jdbcDatabaseManager.isConnected());
    }

    @Test
    public void testCreateTable() throws Exception {
        String tableName = "test"; //The table what you want create
        String actual = "";
        jdbcDatabaseManager.createTable(tableName);
        tableName = "test2";
        jdbcDatabaseManager.createTable(tableName);
        jdbcDatabaseManager.getAllTableNames();
        String[] tablesName = jdbcDatabaseManager.getAllTableNames();
        for (String res : tablesName){
            res.split(",");
            if (res.equals(tableName)){
                actual = tableName;
            }
        }
        String expected = tableName ;
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testInsertRecordInTable() throws Exception {
        String actual = "";
        String expected = "abdula mahmud";
        jdbcDatabaseManager.insertRecordInTable("test", "abdula", "mahmud");
        jdbcDatabaseManager.insertRecordInTable("test", "abdula", "mahmud");
        jdbcDatabaseManager.insertRecordInTable("test", "abdula", "mahmud");
        Map<Integer, String> rec = jdbcDatabaseManager.selectRecordInTableForTest("test", "*");
        for (int id : rec.keySet()) {
            if (rec.get(id).equals(expected)) {
                actual = expected;
            }
        }
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testUpdateRecordInTable() throws Exception {
        jdbcDatabaseManager.updateRecordInTable("test", 1, "Sasha", "Grey");
        jdbcDatabaseManager.selectRecordInTable("test", "*");
        String expected = "Sasha Grey";
        String actual = jdbcDatabaseManager.selectRecordInTableForTest("test", "*").get(1);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testSelectRecordInTable() throws Exception {
        Map<Integer, String> actual = jdbcDatabaseManager.selectRecordInTableForTest("test", "*");
        String expected = "{1=Sasha Grey 2=abdula mahmud, 3=abdula mahmud}";
        Assert.assertEquals(expected, actual.toString());
    }

    @Test
    public void testDeleteRecordInTable() throws Exception {
        String recordName = "abdula mahmud";
        String actual = "";
        Map<Integer, String> rec = jdbcDatabaseManager.selectRecordInTableForTest("test", "*");

        for (int id : rec.keySet()){
            if(rec.get(id).equals(recordName)) {
                jdbcDatabaseManager.deleteRecordInTable("test", id);
                actual = "";
            }
        }
        String expected = "";
        Assert.assertEquals(expected, actual);
    }


    @Test
    public void testDbCreate() throws Exception {
        String actual = "";
        jdbcDatabaseManager.dbCreate("test","postgres");
        jdbcDatabaseManager.dbShow();
        actual = jdbcDatabaseManager.dbShow().toString();
        String expected = "[postgres, test]";
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testDBShow() throws Exception {
        String expected = "[postgres, test]";
        jdbcDatabaseManager.dbShow();
        String actual = jdbcDatabaseManager.dbShow().toString();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testGetAllTableNames() throws Exception {
        String expected = "";
        jdbcDatabaseManager.getAllTableNames();
        String[] actual = jdbcDatabaseManager.getAllTableNames();
        expected = "[people, test, test2, users]";
        Assert.assertEquals(expected, Arrays.toString(actual));
    }

    @Test
    public void testGetTableSize() throws Exception {
        String actual = jdbcDatabaseManager.getTableSize("test");
        Assert.assertEquals("Size of table:\"test\" = 1", actual);
    }
    @Test
    public void testDeleteTable() throws Exception {
        String tableName = "test";  //The table what you want create
        String actual = "";
        jdbcDatabaseManager.deleteTable(tableName);
        tableName = "test2";
        jdbcDatabaseManager.deleteTable(tableName);
        jdbcDatabaseManager.getAllTableNames();
        String[] tablesName = jdbcDatabaseManager.getAllTableNames();
        for (String res : tablesName){
            res.split(",");
            if (res.equals(tableName)){
                actual = tableName;
            }
        }
        String expected = "" ;
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testToString() throws Exception {
        String expected = "[users, roots, people]";
        String actual = jdbcDatabaseManager.toString(new String[] {"users", "roots", "people"});
        Assert.assertEquals(expected, actual);
    }

}