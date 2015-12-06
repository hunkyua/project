package ua.com.hunkyua.sqlcmd.service;

import ua.com.hunkyua.sqlcmd.dao.DatabaseManager;
import ua.com.hunkyua.sqlcmd.dao.JDBCDatabaseManager;

/**
 * Created by Hunky on 01.12.2015.
 */
public class TableService {
    DatabaseManager dao = new JDBCDatabaseManager();

    public void tableCreate(String tableName) {
        dao.createTable(tableName);
    }
}