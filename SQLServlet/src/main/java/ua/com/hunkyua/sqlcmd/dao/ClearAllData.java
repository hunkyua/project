package ua.com.hunkyua.sqlcmd.dao;

import java.util.ArrayList;

/**
 * Created by Hunky on 15.11.2015.
 */
public class ClearAllData {
    public void clear() {
        JDBCDatabaseManager.error_connect = "";
        JDBCDatabaseManager.error = "";
        JDBCDatabaseManager.doesNotExist = "";

        JDBCDatabaseManager.list = new ArrayList<>() ;
        JDBCDatabaseManager.tables = new String[]{"*Please enter Show tables*"};
        JDBCDatabaseManager.size = "";
    }
}
