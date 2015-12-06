package ua.com.hunkyua.sqlcmd.dao;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Hunky on 30.11.2015.
 */
public interface DatabaseManager {
//    List<DataSet> getTableData(String tableName);
//    void clear(String tableName);
//    void create(String tableName, DataSet input);
//    void update(String tableName, int id, DataSet newValue);
//    Set<String> getTableColumns(String tableName);

    void connect(String database, String userName, String password) throws IOException;

    void insertRecordInTable(String tableName, String username, String surname);
    void deleteRecordInTable(String tableName, int id);
    List<String> selectRecordInTable(String tableName, String select);
    Map<Integer, String> selectRecordInTableForTest(String tableName, String select);
    void updateRecordInTable(String tableName, int id, String username, String surname);

    String[] getAllTableNames();

    void createTable(String tableName);
    void deleteTable(String tableName);
    String getTableSize(String tableName);
    void userCreate(String user, String password);
    boolean isConnected();
    String toString(String[] tables);
    List<String> dbShow();
    void dbCreate(String db_name, String db_user);
}
