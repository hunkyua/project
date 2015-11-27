package sqlcmd.command.table;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import sqlcmd.JDBCConnector;

import java.sql.SQLException;

/**
 * Created by Hunky on 22.11.2015.
 */
public class TableCreateTest {

    private TableNames tableNames;
    private TableCreate tableCreate;

    @Before
    public void setup () throws SQLException, ClassNotFoundException {
        tableCreate = new TableCreate();
        tableNames = new TableNames();
        new JDBCConnector().getConnection();
    }

    @Test
    public void testCreateTable() throws Exception {
        String tableName = "test"; //The table what you want create
        String actual = "";
        tableCreate.createTable(tableName);
        tableNames.getAllTableNames();
        String[] tablesName = tableNames.tables;
        for (String res : tablesName){
            res.split(",");
            if (res.equals(tableName)){
                actual = tableName;
            }
        }
        String expected = tableName ;
        Assert.assertEquals(expected, actual);
    }
}