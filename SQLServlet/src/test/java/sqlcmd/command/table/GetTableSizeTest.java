package sqlcmd.command.table;

import org.junit.Assert;
import org.junit.Test;
import sqlcmd.JDBCConnector;

/**
 * Created by Hunky on 20.11.2015.
 */
public class GetTableSizeTest {
    TableSize tableSize;

    @Test
    public void testGetTableSize() throws Exception {
        new JDBCConnector("postgres", "postgres", "1336").getConnection();
        tableSize = new TableSize();
        tableSize.GetTableSize("people");
        String actual = tableSize.size;
        Assert.assertEquals("Size of table = 7", actual);
    }
}