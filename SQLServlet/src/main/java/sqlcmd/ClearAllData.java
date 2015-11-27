package sqlcmd;

import sqlcmd.command.crud.RecordDelete;
import sqlcmd.command.crud.RecordInsert;
import sqlcmd.command.crud.RecordSelect;
import sqlcmd.command.crud.RecordUpdate;
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

        RecordSelect.error = "";
        RecordSelect.doesNotExist = "";
        RecordSelect.list = new ArrayList<>() ;

        RecordDelete.error = "";
        RecordDelete.doesNotExist = "";

        RecordInsert.error = "";
        RecordInsert.doesNotExist = "";

        RecordUpdate.error = "";
        RecordUpdate.doesNotExist = "";

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
