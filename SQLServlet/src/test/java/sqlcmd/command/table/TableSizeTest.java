package sqlcmd.command.table;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import sqlcmd.JDBCConnector;

import java.sql.SQLException;

/**
 * Created by Hunky on 20.11.2015.
 */
public class TableSizeTest {
    TableSize tableSize;

    @Before
    public void setup () throws SQLException, ClassNotFoundException {
        tableSize = new TableSize();
        new JDBCConnector().getConnection();
    }

    @Test
    public void testGetTableSize() throws Exception {
        tableSize.getTableSize("people");
        String actual = tableSize.size;
        Assert.assertEquals("Size of table:\"people\" = 11", actual);
    }
}