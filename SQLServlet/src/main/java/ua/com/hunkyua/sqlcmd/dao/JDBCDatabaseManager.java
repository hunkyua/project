package ua.com.hunkyua.sqlcmd.dao;

import java.sql.*;
import java.util.*;

/**
 * Created by oktopus on 07.10.15.
 */
public class JDBCDatabaseManager implements DatabaseManager {

    private Connection connection;

    private static final String DATABASE_DRIVER = "org.postgresql.Driver";
    private static final String DATABASE_URL = "jdbc:postgresql://localhost:5432/";

    public static String doesNotExist = "";
    public static String er_connect = "";
    public static String error = "";
    public static String error_connect = "";
    public static String size = "";
    public static String[] tables = {"*Please enter Show tables*"};
    public static List<String> list = new ArrayList<>();
    public static List<String> result = new ArrayList<>();
    public static String line2 = "";
    public static Map<Integer, String> line1 = new HashMap<>();

    @Override
    public void insertRecordInTable(String tableName, String username, String surname) {
        try (Statement statement = connection.createStatement()) {
            doesNotExist = String.format("Record username:\"%s\" surname:\"%s\" in table:\"%s\" inserted", username, surname, tableName);
            error = "";
            String insertInTable = "INSERT INTO " + tableName
                    + " (USERNAME, SURNAME) " + "VALUES"
                    + "('" + username + "', '" + surname + "')";
            statement.executeUpdate(insertInTable);
        } catch (SQLException e) {
            doesNotExist = String.format("Sorry, but record username:\"%s\" surname:\"%s\" in table:\"%s\" can't be inserted." +
                    " Try again", username, surname, tableName);
            if (doesNotExist.equals("Сервер запросил парольную аутентификацию, но пароль не был указан.")) {
                error = ">>> Please Connect to DB";
            }
        }
    }

    @Override
    public void selectRecordInTable(String tableName, String select) {

        try (Statement statement = connection.createStatement()) {
            doesNotExist = String.format("Record in table:\"%s\"", tableName);
            error = "";
            String selectInTable = "SELECT " + select + " FROM " + tableName;
            statement.execute(selectInTable);
            ResultSet rs = statement.executeQuery(selectInTable);
            line2 = "<td>ID</td><td>USERNAME</td><td>SURNAME</td><tr></tr>";
            list.add(line2);

            while (rs.next()) {
                String id = rs.getString("ID");
                String username = rs.getString("USERNAME");
                String surname = rs.getString("SURNAME");
                line1.put(Integer.parseInt(id), username + ", " + surname);
                String line = "<p align=\"center\">" + "<td>" + id + "</td>" + " " + "<td>" + username + "</td>" + " "
                        + "<td>" + surname + "</td>" + "<tr>" + "</tr>" + "</p>";

                list.add(line);
            }
        } catch (SQLException e) {
            doesNotExist = String.format("Sorry, but record in table:\"%s\" can't be selected. Try again", tableName);
            if (doesNotExist.equals("Сервер запросил парольную аутентификацию, но пароль не был указан.")) {
                error = ">>> Please Connect to DB";
            }
        }
    }

    @Override
    public void deleteRecordInTable(String tableName, int id) {
        try (Statement statement = connection.createStatement()) {
            doesNotExist = String.format("Record id:\"%s\" in table:\"%s\" deleted", id, tableName);
            error = "";
            String deleteInTable = "DELETE FROM " + tableName + " WHERE ID =" + id;
            statement.execute(deleteInTable);
        } catch (SQLException e) {
            doesNotExist = String.format("Sorry, but record id:\"%s\" in table:\"%s\" can't be deleted. Try again", id, tableName);
            if (doesNotExist.equals("Сервер запросил парольную аутентификацию, но пароль не был указан.")) {
                error = ">>> Please Connect to DB";
            }
        }
    }

    @Override
    public void updateRecordInTable(String tableName, int id, String username, String surname) {
        try (Statement statement = connection.createStatement()) {
            doesNotExist = String.format("Record id:\"%s\" in table:\"%s\" updated", id, tableName);
            error = "";
            String updateInTable = "UPDATE " + tableName
                    + " SET USERNAME = '" + username + "'" + ","
                    + " SURNAME = '" + surname + "' "
                    + "WHERE ID =" + id;
            statement.execute(updateInTable);
        } catch (SQLException e) {
            doesNotExist = String.format("Sorry, but record id: \"%s\" in table:\"%s\" can't be updated. Try again", id, tableName);
            if (doesNotExist.equals("Сервер запросил парольную аутентификацию, но пароль не был указан.")) {
                error = ">>> Please Connect to DB";
            }
        }
    }

    @Override
    public void connect(String name, String user, String password) {
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
            er_connect = "If you not have account: Register please!";
            connection = null;
            throw new RuntimeException(
               String.format("Can't get connection to database:\"%s\" user:\"%s\"", name, user), e);
        }
    }

    @Override
    public String toString(String[] tables) {
        if (tables == null)
            return "null";

        int iMax = tables.length - 1;
        if (iMax == -1)
            return "[]";

        StringBuilder b = new StringBuilder();
        b.append('[');
        for (int i = 0; ; i++) {
            b.append(String.valueOf(tables[i]));
            if (i == iMax)
                return b.append(']').toString();
            b.append(", ");
        }
    }

    @Override
    public String[] getAllTableNames() {
        try (Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery("Select table_name FROM information_schema.tables " +
                     "WHERE table_schema = 'public' ORDER BY table_name ASC")) {
            doesNotExist = "";
            error = "";
            int index = 0;
            tables = new String[100];
            while (rs.next()) {
                tables[index++] = rs.getString("table_name");
            }
            tables = Arrays.copyOf(tables, index, String[].class);
            return tables;
        } catch (SQLException e) {
            doesNotExist = e.getMessage();
            if (doesNotExist.equals("Сервер запросил парольную аутентификацию, но пароль не был указан.")) {
                error = ">>> Please Connect to DB";
                return tables;
            }
        }
        return tables;
    }

    @Override
    public int getTableSize(String tableName) {
        try (Statement statement = connection.createStatement();
             ResultSet rsCount = statement.executeQuery("SELECT COUNT (*) FROM " + tableName)) {
            doesNotExist = "";
            error = "";
            rsCount.next();
            int a = rsCount.getInt(1);
            size = String.format("Size of table:\"%s\" = ", tableName) + Integer.toString(a);
            return a;
        } catch (SQLException e) {
            doesNotExist = String.format("Sorry, but table:\"%s\" doesn't exist. Try again", tableName);
            if (doesNotExist.equals("Сервер запросил парольную аутентификацию, но пароль не был указан.")) {
                error = ">>> Please Connect to DB";
            }
            return 0;
        }
    }

    @Override
    public void createTable(String tableName) {
        try (Statement statement = connection.createStatement()) {
            doesNotExist = String.format("Table:\"%s\" created", tableName);
            error = "";
            String createTableSQL = "CREATE TABLE " + tableName + "("
                    + "ID serial PRIMARY KEY,"
                    + "USERNAME VARCHAR(20) NOT NULL, "
                    + "SURNAME VARCHAR(20) NOT NULL "
                    + ")";
            statement.execute(createTableSQL);
        } catch (SQLException e) {
            doesNotExist = String.format("Sorry, but table:\"%s\" can't be created. Try again", tableName);
            if (doesNotExist.equals("Сервер запросил парольную аутентификацию, но пароль не был указан.")) {
                error = ">>> Please Connect to DB";
            }
        }
    }

    @Override
    public void deleteTable(String tableName) {
        try (Statement statement = connection.createStatement()) {
            doesNotExist = String.format("Table:\"%s\" deleted", tableName);
            error = "";
            String deleteTableSQL = "DROP TABLE " + tableName;
            statement.execute(deleteTableSQL);
        } catch (SQLException e) {
            doesNotExist = String.format("Sorry, but table:\"%s\" can't be deleted. Try again", tableName);
            if (doesNotExist.equals("Сервер запросил парольную аутентификацию, но пароль не был указан.")) {
                error = ">>> Please Connect to DB";
            }
        }
    }

    @Override
    public void dbShow() {
        try (Statement statement = connection.createStatement()){
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
            if (doesNotExist.equals("Сервер запросил парольную аутентификацию, но пароль не был указан.")){
                error = ">>> Please Connect to DB";
            }
        }
    }

    @Override
    public void userCreate(String username, String password) {
        try (Statement statement = connection.createStatement()) {
            doesNotExist = "";
            error = "";
            String createUser = "CREATE USER " + username + " PASSWORD " + "'" + password + "'";
            statement.execute(createUser);
        } catch (SQLException e) {
            doesNotExist = e.getMessage();
            if (doesNotExist.equals("Сервер запросил парольную аутентификацию, но пароль не был указан.")){
                error = ">>> Please Connect to DB";
            }
        }
    }
    @Override
    public void dbCreate(String dbName, String username) {
        try (Statement statement = connection.createStatement()) {
            doesNotExist = "";
            error = "";
            String createDB = "CREATE DATABASE " + dbName + " OWNER " + username;
            statement.execute(createDB);
        } catch (SQLException e) {
            doesNotExist = e.getMessage();
            if (doesNotExist.equals("Сервер запросил парольную аутентификацию, но пароль не был указан.")){
                error = ">>> Please Connect to DB";
            }
        }
    }

    @Override
    public boolean isConnected() {
        return connection != null;
    }
}



