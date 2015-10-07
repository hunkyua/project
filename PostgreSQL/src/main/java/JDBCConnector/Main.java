package JDBCConnector;

import JDBCConnector.ActionWithRecordTable.DeleteInTable;
import JDBCConnector.ActionWithRecordTable.InsertInTable;
import JDBCConnector.ActionWithRecordTable.SelectInTable;
import JDBCConnector.ActionWithRecordTable.UpdateInTable;
import JDBCConnector.ActionWithTable.CreateTable;
import JDBCConnector.ActionWithTable.DeleteTable;

import java.sql.SQLException;

/**
 * Created by oktopus on 06.10.15.
 */
public class Main {

    public static void main(String[] args) throws SQLException {
        CreateTable createTable = new CreateTable();
        InsertInTable insertInTable = new InsertInTable();
        DeleteTable deleteTable = new DeleteTable();
        UpdateInTable updateInTable = new UpdateInTable();
        DeleteInTable deleteInTable = new DeleteInTable();
        SelectInTable selectInTable = new SelectInTable();


//        createTable.createDBUserTable();
//        insertInTable.insertRecordInTable();
//        deleteTable.deleteTable();
//        updateInTable.updateInTable();
//        deleteInTable.deleteInTable();
//        selectInTable.selectInTable();


    }
}

