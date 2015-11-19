package sqlcmd;

import sqlcmd.command.crud.DeleteRecord;
import sqlcmd.command.crud.InsertRecord;
import sqlcmd.command.crud.SelectRecord;
import sqlcmd.command.crud.UpdateRecord;
import sqlcmd.command.db.CreateDB;
import sqlcmd.command.db.CreateUser;
import sqlcmd.command.table.TableCreate;
import sqlcmd.command.table.TableDelete;
import sqlcmd.command.table.TableNames;
import sqlcmd.command.table.TableSize;

import java.util.ArrayList;

/**
 * Created by Hunky on 15.11.2015.
 */
public class ClearAllData {
    public void clear() {
        JDBCConnector.er_connect = "";
        JDBCConnector.error = "";

        CreateDB.error = "";
        CreateDB.doesNotExist = "";

        CreateUser.error = "";
        CreateUser.doesNotExist = "";

        SelectRecord.error = "";
        SelectRecord.doesNotExist = "";
        SelectRecord.list = new ArrayList<>() ;

        DeleteRecord.error = "";
        DeleteRecord.doesNotExist = "";

        InsertRecord.error = "";
        InsertRecord.doesNotExist = "";

        UpdateRecord.error = "";
        UpdateRecord.doesNotExist = "";

        TableCreate.error = "";
        TableCreate.doesNotExist = "";

        TableDelete.error = "";
        TableDelete.doesNotExist = "";

        TableNames.error = "";
        TableNames.doesNotExist = "";
        TableNames.tables = new String[]{"*Please enter Show tables*"};

        TableSize.error = "";
        TableSize.doesNotExist = "";
        TableSize.size = "";
    }
}
