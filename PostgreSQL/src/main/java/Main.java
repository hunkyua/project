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


//        createTable.createDBUserTable();
//        insertInTable.insertRecordInTable();
//        deleteTable.deleteTable();
          updateInTable.updateInTable();

    }
}

