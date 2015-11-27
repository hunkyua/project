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
public class RecordInsertTest {
    private RecordInsert recordInsert;
    private RecordSelect recordSelect;

    @Before
    public void setup () throws SQLException, ClassNotFoundException {
        recordInsert = new RecordInsert();
        recordSelect = new RecordSelect();
        new JDBCConnector().getConnection();
    }

    @Test
    public void testInsertRecordInTable() throws Exception {
        String actual = "";
        String expected = "abdula, mahmud";
        recordInsert.insertRecordInTable("people", "abdula", "mahmud");
        recordSelect.selectRecordInTable("people", "*");
        Map<Integer, String> rec = recordSelect.line1;
            for (int id : rec.keySet()) {
                if (rec.get(id).equals(expected)) {
                    actual = expected;
                }
            }

        Assert.assertEquals(expected, actual);
    }
}