package sqlcmd;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by oktopus on 07.10.15.
 */
public class JDBCConnector {
//    private final String DB_DRIVER = "com.mysql.jdbc.Driver";
//    private final String DB_URL = "jdbc:mysql://localhost:3306/";
    private static final String DB_DRIVER = "org.postgresql.Driver";
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/";
    public static String db_name;
    public static String db_user;
    public static String db_password;
    public static String error = "";
    public JDBCConnector(String n, String u, String p) {
        db_name = n;
        db_user = u;
        db_password = p;
    }
    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        error = "";
        Class.forName(DB_DRIVER);
        return DriverManager.getConnection(DB_URL + db_name, db_user, db_password);
    }

    public static void SelectRecordInTable(String tableName) throws ClassNotFoundException, SQLException {
        Statement statement = null;
        String selectInTable = "SELECT * FROM " + tableName;

        try {
            statement = getConnection().createStatement();
            statement.execute(selectInTable);
            ResultSet rs = statement.executeQuery(selectInTable);
            List<Object> list = new ArrayList<>();

            while (rs.next()) {
                String id = rs.getString("ID");
                String username = rs.getString("USERNAME");
                String surname = rs.getString("SURNAME");
                list.add(id);
                list.add(username);
                list.add(surname);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                statement.close();
            }
        }
    }

    public static void DeleteRecordInTable(String tableName) throws SQLException, ClassNotFoundException {
        Statement statement = null;
        String deleteInTable = "DELETE FROM " + tableName + " WHERE ID = 4";

        try {
            statement = getConnection().createStatement();
            statement.execute(deleteInTable);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                statement.close();
            }
        }
    }

    public static void InsertRecordInTable(String tableName) throws SQLException, ClassNotFoundException {
        Statement statement = null;
        String insertInTable = "INSERT INTO " + tableName
                + " (USERNAME, SURNAME) " + "VALUES"
                + "('Valentin', 'Opanasyuk')";

        try {
            statement = getConnection().createStatement();

            statement.execute(insertInTable);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                statement.close();
            }
        }
    }

    public static void UpdateRecordInTable(String tableName) throws ClassNotFoundException, SQLException {
        Statement statement = null;
        String updateInTable = "UPDATE " + tableName
                + " SET USERNAME = '}|{oI7a', SURNAME = 'Pupkina' "
                + "WHERE ID = 1";

        try {
            statement = getConnection().createStatement();
            statement.execute(updateInTable);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                statement.close();
            }
        }
    }


    }



