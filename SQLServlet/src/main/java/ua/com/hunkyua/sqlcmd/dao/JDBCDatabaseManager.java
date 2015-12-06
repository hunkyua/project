package ua.com.hunkyua.sqlcmd.dao;

import java.io.IOException;
import java.sql.*;
import java.util.*;

/**
 * Created by oktopus on 07.10.15.
 */
public class JDBCDatabaseManager implements DatabaseManager {

    private Connection connection;

    private static final String DATABASE_DRIVER = "org.postgresql.Driver";
    private static final String DATABASE_URL = "jdbc:postgresql://localhost:5432/";

    public static String error = "";
    public static String doesNotExist = "";

    public static String error_connect = "";


    @Override
    public void connect(String name, String user, String password) throws IOException {
        try {
            Class.forName(DATABASE_DRIVER);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Please add jdbc jar to project.", e);
        }
        try {
            if (connection != null) {
                connection.close();
            }
            connection = DriverManager.getConnection(
                    DATABASE_URL + name, user, password);
        } catch (SQLException e) {
            connection = null;
            throw new RuntimeException(
                    String.format("Can't get connection to database:\"%s\" user:\"%s\"", name, user), e);
        }
    }

    @Override
    public void insertRecordInTable(String tableName, String username, String surname) {
        try (Statement statement = connection.createStatement()) {
            String insertInTable = "INSERT INTO " + tableName
                    + " (USERNAME, SURNAME) " + "VALUES"
                    + "('" + username + "', '" + surname + "')";
            statement.executeUpdate(insertInTable);
        } catch (SQLException e) {
            throw new RuntimeException(">>> Please Connect to DB", e);
        }
    }

    @Override
    public List<String> selectRecordInTable(String tableName, String select) {
        List<String> list = new LinkedList<>();
        String line2 = "<td>ID</td><td>USERNAME</td><td>SURNAME</td><tr></tr>";
        try (Statement statement = connection.createStatement()) {
            String selectInTable = "SELECT " + select + " FROM " + tableName + " ORDER BY id";
            statement.execute(selectInTable);
            ResultSet rs = statement.executeQuery(selectInTable);
            list.add(line2);
            while (rs.next()) {
                String id = rs.getString("ID");
                String username = rs.getString("USERNAME");
                String surname = rs.getString("SURNAME");
                String line = "<p align=\"center\">" + "<td>" + id + "</td>" + " " + "<td>" + username + "</td>" + " "
                        + "<td>" + surname + "</td>" + "<tr>" + "</tr>" + "</p>";
                list.add(line);
            }
        } catch (SQLException e) {
            throw new RuntimeException(">>> Please Connect to DB", e);
        }
        return list;
    }

    @Override
    public Map<Integer, String> selectRecordInTableForTest(String tableName, String select) {
        Map<Integer, String> line1 = new HashMap<>();
        try (Statement statement = connection.createStatement()) {
            String selectInTable = "SELECT " + select + " FROM " + tableName + " ORDER BY id";
            statement.execute(selectInTable);
            ResultSet rs = statement.executeQuery(selectInTable);
            while (rs.next()) {
                String id = rs.getString("ID");
                String username = rs.getString("USERNAME");
                String surname = rs.getString("SURNAME");
                line1.put(Integer.parseInt(id), username + " " + surname);
            }
        } catch (SQLException e) {

        }
        return line1;
    }

    @Override
    public void deleteRecordInTable(String tableName, int id) {
        try (Statement statement = connection.createStatement()) {
            String deleteInTable = "DELETE FROM " + tableName + " WHERE ID =" + id;
            statement.execute(deleteInTable);
        } catch (SQLException e) {
            throw new RuntimeException(">>> Please Connect to DB", e);
        }
    }

    @Override
    public void updateRecordInTable(String tableName, int id, String username, String surname) {
        try (Statement statement = connection.createStatement()) {
            String updateInTable = "UPDATE " + tableName
                    + " SET USERNAME = '" + username + "'" + ","
                    + " SURNAME = '" + surname + "' "
                    + "WHERE ID =" + id;
            statement.executeUpdate(updateInTable);
        } catch (SQLException e) {
            throw new RuntimeException(">>> Please Connect to DB", e);
        }
    }

    @Override
    public String toString(String[] tables) {
        if (tables == null) {
            return "null";
        }

        int iMax = tables.length - 1;
        if (iMax == -1) {
            return "[]";
        }

        StringBuilder b = new StringBuilder();
        b.append('[');
        for (int i = 0; ; i++) {
            b.append(String.valueOf(tables[i]));
            if (i == iMax) {
                return b.append(']').toString();
            }
            b.append(", ");
        }
    }

    @Override
    public String[] getAllTableNames() {
        String[] tables = {"*Please enter Show tables*"};
        try (Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery("Select table_name FROM information_schema.tables " +
                     "WHERE table_schema = 'public' ORDER BY table_name ASC")) {
            int index = 0;
            tables = new String[100];
            while (rs.next()) {
                tables[index++] = rs.getString("table_name");
            }
            tables = Arrays.copyOf(tables, index, String[].class);
            return tables;
        } catch (SQLException e) {
            throw new RuntimeException(">>> Please Connect to DB", e);
        }
    }

    @Override
    public String getTableSize(String tableName) {
        String size = "";
        try (Statement statement = connection.createStatement();
             ResultSet rsCount = statement.executeQuery("SELECT COUNT (*) FROM " + tableName)) {
            rsCount.next();
            int a = rsCount.getInt(1);
            size = String.format("Size of table:\"%s\" = ", tableName) + Integer.toString(a);
            return size;
        } catch (SQLException e) {
            throw new RuntimeException(">>> Please Connect to DB", e);
        }
    }

    @Override
    public void createTable(String tableName) {
        try (Statement statement = connection.createStatement()) {
            String createTableSQL = "CREATE TABLE " + tableName + "("
                    + "ID serial PRIMARY KEY,"
                    + "USERNAME VARCHAR(20) NOT NULL, "
                    + "SURNAME VARCHAR(20) NOT NULL "
                    + ")";
            statement.execute(createTableSQL);
        } catch (SQLException e) {
            throw new RuntimeException(">>> Please Connect to DB", e);
        }
    }

    @Override
    public void deleteTable(String tableName) {
        try (Statement statement = connection.createStatement()) {
            String deleteTableSQL = "DROP TABLE " + tableName;
            statement.execute(deleteTableSQL);
        } catch (SQLException e) {
            throw new RuntimeException(">>> Please Connect to DB", e);
        }
    }

    @Override
    public List<String> dbShow() {
        List<String> result = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            doesNotExist = "";
            error = "";
            String showDB = "SELECT datname FROM pg_database WHERE datistemplate = false;";
            statement.execute(showDB);
            ResultSet rs = statement.executeQuery(showDB);
            while (rs.next()) {
                String res = rs.getString("datname");
                result.add(res);
            }
        } catch (SQLException e) {
            doesNotExist = e.getMessage();
            if (doesNotExist.equals("Сервер запросил парольную аутентификацию, но пароль не был указан.")) {
                error = ">>> Please Connect to DB";
            }
        }
        return result;
    }

    @Override
    public void userCreate(String username, String password) {
        try (Statement statement = connection.createStatement()) {
            if (username.equals("") && password.equals("")){
                throw new RuntimeException(String.format("Your fields are empty!!!"));
            }
            String createUser = "CREATE USER " + username + " PASSWORD " + "'" + password + "'";
            statement.execute(createUser);
        } catch (SQLException e) {
            throw new RuntimeException(
                    String.format("Sorry your data wrong!!! Please enter DBname, Username, Password"), e);
        }
    }

    @Override
    public void dbCreate(String dbName, String username) {
        try (Statement statement = connection.createStatement()) {
            if (username.equals("") && dbName.equals("")){
                throw new SQLException(String.format("Your fields are empty!!!"));
            }
            String createDB = "CREATE DATABASE " + dbName;
            statement.execute(createDB);
        } catch (SQLException e) {
            throw new RuntimeException(
                    String.format("Sorry your data wrong!!! Please enter DBname, Username, Password"), e);
        }
    }

    @Override
    public boolean isConnected() {
        return connection != null;
    }

}



