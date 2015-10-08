package JDBCConnector;

import JDBCConnector.ActionWithRecordTable.DeleteInTable;
import JDBCConnector.ActionWithRecordTable.InsertInTable;
import JDBCConnector.ActionWithRecordTable.SelectInTable;
import JDBCConnector.ActionWithRecordTable.UpdateInTable;
import JDBCConnector.ActionWithTable.CreateTable;
import JDBCConnector.ActionWithTable.DeleteTable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.SQLException;

/**
 * Created by oktopus on 06.10.15.
 */
public class Main {

    public static void main(String[] args) throws SQLException, IOException {
        CreateTable createTable = new CreateTable();
        InsertInTable insertInTable = new InsertInTable();
        DeleteTable deleteTable = new DeleteTable();
        UpdateInTable updateInTable = new UpdateInTable();
        DeleteInTable deleteInTable = new DeleteInTable();
        SelectInTable selectInTable = new SelectInTable();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Welcome to: ");
        System.out.println("     __________________________       ");
        System.out.println("    /  ssss   qqqq      ll    /       ");
        System.out.println("   / ss     qq  qq     ll    /      ");
        System.out.println("  /  sss   qq  qq     ll    /        ");
        System.out.println(" /    ss  qq  qq     ll    /       ");
        System.out.println("/  ssss   qqqq qqq  lllll /        ");
        System.out.println("--------------------------        ");
        while (true) {
            System.out.println("You can choose next command:");
            System.out.println("****************************        ");
            System.out.println("1: Create table");
            System.out.println("2: Delete table");
            System.out.println("3: Insert record in table");
            System.out.println("4: Update record in table");
            System.out.println("5: Delete record in table");
            System.out.println("6: Select record in table");
            System.out.println("7: Exit");
            System.out.println("Please choose command:");

            int choose = Integer.parseInt(reader.readLine());
            switch (choose) {
                case 1:
                    createTable.createDBUserTable();
                    break;
                case 2:
                    deleteTable.deleteTable();
                    break;
                case 3:
                    insertInTable.insertRecordInTable();
                    break;
                case 4:
                    updateInTable.updateInTable();
                    break;
                case 5:
                    deleteInTable.deleteInTable();
                    break;
                case 6:
                    selectInTable.selectInTable();
                    break;
                case 7:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Неверная команда");
            }
        }
    }
}

