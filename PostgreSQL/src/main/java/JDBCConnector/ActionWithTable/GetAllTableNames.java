package JDBCConnector.ActionWithTable;

import JDBCConnector.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;

/**
 * Created by oktopus on 09.10.15.
 */
public class GetAllTableNames {
    JDBCConnector jdbc = new JDBCConnector();
    Connection connection;

    public void getTableNames() {
        try {
            connection = jdbc.isConnected();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("Select table_name FROM information_schema.tables " +
                    "WHERE table_schema = 'public'");
            String[] tables = new String[100];
            int index = 0;
            while (rs.next()) {
                tables[index++] = rs.getString("table_name");
            }
            tables = Arrays.copyOf(tables, index, String[].class);
            System.out.println(Arrays.toString(tables));
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
