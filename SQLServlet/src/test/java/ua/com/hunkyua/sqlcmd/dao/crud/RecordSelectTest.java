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
public class RecordSelectTest {

    private JDBCDatabaseManager jdbcDatabaseManager;


    @Before
    public void setup () throws SQLException, ClassNotFoundException {
        jdbcDatabaseManager = new JDBCDatabaseManager();
        jdbcDatabaseManager.connect("postgres", "postgres", "1336");
    }

    @Test
    public void testSelectRecordInTable() throws Exception {
        jdbcDatabaseManager.selectRecordInTable("people", "*");
        Map<Integer, String> actual = jdbcDatabaseManager.line1;
        String expected = "{1=Sasha, Grey, 2=faf, faf, 3=griwa, isaev, 4=Vasiliy, Antonovich, 7=Anatoliy, Griwaev, 8=Lolka, Petrovna, 9=Victoriya, Lipsnis, 10=aaaa, aaaa, 11=lolka, lolka, 12=lolka1, lolka2, 13=aaaa, aaaa}";
        Assert.assertEquals(expected, actual.toString());
    }
}