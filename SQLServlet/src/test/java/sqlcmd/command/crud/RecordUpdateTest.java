package sqlcmd.command.crud;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import sqlcmd.JDBCConnector;

import java.sql.SQLException;

/**
 * Created by Hunky on 22.11.2015.
 */
public class RecordUpdateTest {

    private RecordUpdate recordUpdate;
    private RecordSelect recordSelect;

    @Before
    public void setup () throws SQLException, ClassNotFoundException {
        recordUpdate = new RecordUpdate();
        recordSelect = new RecordSelect();
        new JDBCConnector().getConnection();
    }

    @Test
    public void testUpdateRecordInTable() throws Exception {
        recordUpdate.updateRecordInTable("people", 1, "Sasha", "Grey");
        recordSelect.selectRecordInTable("people", "*");
        String expected = "Sasha, Grey";
        String actual = recordSelect.line1.get(1);

        Assert.assertEquals(expected, actual);
    }
}