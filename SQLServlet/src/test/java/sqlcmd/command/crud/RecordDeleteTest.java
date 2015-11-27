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
public class RecordDeleteTest {
    private RecordDelete recordDelete;
    private RecordSelect recordSelect;

    @Before
    public void setup () throws SQLException, ClassNotFoundException {
        recordDelete = new RecordDelete();
        recordSelect = new RecordSelect();
        new JDBCConnector().getConnection();
    }

    @Test
    public void testDeleteRecordInTable() throws Exception {
        String recordName = "abdula, mahmud";
        String actual = "";
        recordSelect.selectRecordInTable("people", "*");
        Map<Integer, String> rec = recordSelect.line1;

            for (int id : rec.keySet()){
                if(rec.get(id).equals(recordName)) {
                    recordDelete.deleteRecordInTable("people", id);
                    actual = "";
            }
            }
        String expected = "";
        Assert.assertEquals(expected, actual);
    }
    }
