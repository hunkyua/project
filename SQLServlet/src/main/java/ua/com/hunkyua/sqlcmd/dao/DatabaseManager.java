package ua.com.hunkyua.sqlcmd.dao;

/**
 * Created by Hunky on 30.11.2015.
 */
public interface DatabaseManager {
//    List<DataSet> getTableData(String tableName);
//    void clear(String tableName);
//    void create(String tableName, DataSet input);
//    void update(String tableName, int id, DataSet newValue);
//    Set<String> getTableColumns(String tableName);

    void connect(String database, String userName, String password);

    void insertRecordInTable(String tableName, String username, String surname);
    void deleteRecordInTable(String tableName, int id);
    void selectRecordInTable(String tableName, String select);
    void updateRecordInTable(String tableName, int id, String username, String surname);

    String[] getAllTableNames();
    void createTable(String tableName);
    void deleteTable(String tableName);
    int getTableSize(String tableName);
    void userCreate(String user, String password);
    boolean isConnected();
    String toString(String[] tables);
    void dbShow();
    void dbCreate(String db_name, String db_user);
}
