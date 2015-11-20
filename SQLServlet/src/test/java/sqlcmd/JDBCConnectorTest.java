package sqlcmd;


import org.junit.Assert;
import org.junit.Test;

import java.sql.Connection;

/**
 * Created by Hunky on 20.11.2015.
 */
public class JDBCConnectorTest {

    @Test
    public void testGetConnection() throws Exception {
        String db_name = "postgres";
        String username = "postgres";
        String password = "1336";
        JDBCConnector jdbc = new JDBCConnector(db_name, username,password);
        Connection connection = (Connection) jdbc.getConnection();
        Assert.assertEquals(connection,"lol");
    }
}