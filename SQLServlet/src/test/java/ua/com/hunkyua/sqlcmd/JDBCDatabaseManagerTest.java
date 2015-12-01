package ua.com.hunkyua.sqlcmd;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ua.com.hunkyua.sqlcmd.dao.JDBCDatabaseManager;

/**
 * Created by Hunky on 20.11.2015.
 */
public class JDBCDatabaseManagerTest {
    private JDBCDatabaseManager jdbcDatabaseManager;

    @Before
    public void setup() {
        String db_name = "postgres";
        String username = "postgres";
        String password = "1336";
        jdbcDatabaseManager = new JDBCDatabaseManager();
        jdbcDatabaseManager.connect(db_name, username,password);
    }

    @Test
    public void testGetConnection() throws Exception {


        Assert.assertEquals(true,"lol");
    }
}