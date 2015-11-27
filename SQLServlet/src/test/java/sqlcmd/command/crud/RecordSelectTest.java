package sqlcmd.command.crud;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import sqlcmd.JDBCConnector;

import java.sql.SQLException;
import java.util.Map;

/**
 * Created by Hunky on 22.11.2015.
 */
public class RecordSelectTest {

    private RecordSelect recordSelect;

    @Before
    public void setup () throws SQLException, ClassNotFoundException {
        recordSelect = new RecordSelect();
        new JDBCConnector().getConnection();
    }

    @Test
    public void testSelectRecordInTable() throws Exception {
        recordSelect.selectRecordInTable("people", "*");
        Map<Integer, String> actual = recordSelect.line1;
        String expected = "{1=Sasha, Grey, 2=faf, faf, 3=griwa, isaev, 4=Vasiliy, Antonovich, 7=Anatoliy, Griwaev, 8=Lolka, Petrovna, 9=Victoriya, Lipsnis, 10=aaaa, aaaa, 11=lolka, lolka, 12=lolka1, lolka2, 13=aaaa, aaaa}";
        Assert.assertEquals(expected, actual.toString());
    }
}