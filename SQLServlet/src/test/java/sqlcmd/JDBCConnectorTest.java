package sqlcmd;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;

/**
 * Created by Hunky on 20.11.2015.
 */
public class JDBCConnectorTest {
    private JDBCConnector jdbcConnector;

    @Before
    public void setup() {
        String db_name = "postgres";
        String username = "postgres";
        String password = "1336";
        jdbcConnector = new JDBCConnector(db_name, username,password);
    }
    @Test
    public void testGetConnection() throws Exception {

        Connection connection = (Connection) jdbcConnector.getConnection();
        Assert.assertEquals(connection,"lol");
    }
}