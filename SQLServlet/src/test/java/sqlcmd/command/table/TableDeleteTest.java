package sqlcmd.command.table;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import sqlcmd.JDBCConnector;

import java.sql.SQLException;

/**
 * Created by Hunky on 22.11.2015.
 */
public class TableDeleteTest {

    private TableNames tableNames;
    private TableDelete tableDelete;

    @Before
    public void setup () throws SQLException, ClassNotFoundException {
        tableDelete = new TableDelete();
        tableNames = new TableNames();
        new JDBCConnector().getConnection();
    }

    @Test
    public void testDeleteTable() throws Exception {
        String tableName = "test";  //The table what you want create
        String actual = "";
        tableDelete.deleteTable(tableName);
        tableNames.getAllTableNames();
        String[] tablesName = tableNames.tables;
        for (String res : tablesName){
            res.split(",");
            if (res.equals(tableName)){
                actual = tableName;
            }
        }
        String expected = "" ;
        Assert.assertEquals(expected, actual);
    }
}