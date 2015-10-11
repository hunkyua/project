package JDBCConnector;

import JDBCConnector.ActionWithRecordTable.DeleteRecordInTable;
import JDBCConnector.ActionWithRecordTable.InsertRecordInTable;
import JDBCConnector.ActionWithRecordTable.SelectRecordInTable;
import JDBCConnector.ActionWithRecordTable.UpdateRecordInTable;
import JDBCConnector.ActionWithTable.CreateTable;
import JDBCConnector.ActionWithTable.DeleteTable;
import JDBCConnector.ActionWithTable.GetAllTableNames;
import JDBCConnector.ActionWithTable.GetTableSize;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by oktopus on 06.10.15.
 */
public class Main {
    static JDBCConnector jdbc = new JDBCConnector();
    static Connection connection;

    public static void main(String[] args) throws SQLException, IOException {
        Help help = new Help();
        CreateTable createTable = new CreateTable();
        DeleteTable deleteTable = new DeleteTable();
        InsertRecordInTable insertRecordInTable = new InsertRecordInTable();
        UpdateRecordInTable updateRecordInTable = new UpdateRecordInTable();
        DeleteRecordInTable deleteRecordInTable = new DeleteRecordInTable();
        SelectRecordInTable selectRecordInTable = new SelectRecordInTable();
        GetAllTableNames getAllTableNames = new GetAllTableNames();
        GetTableSize getTableSize = new GetTableSize();

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Welcome to: ");
        System.out.println("     __________________________   ");
        System.out.println("    /  ssss   qqqq      ll    /   ");
        System.out.println("   / ss     qq  qq     ll    /    ");
        System.out.println("  /  sss   qq  qq     ll    /     ");
        System.out.println(" /    ss  qq  qq     ll    /      ");
        System.out.println("/  ssss   qqqq qqq  lllll /       ");
        System.out.println("--------------------------        ");
        jdbc.getDBConnection();

        while (true) {
            System.out.println("Введи команду 1...9 (или 0 для помощи) :");
            int choose = Integer.parseInt(reader.readLine());
            switch (choose) {
                case 0:
                    help.help();
                    break;
                case 1:
                    createTable.createDBUserTable();
                    break;
                case 2:
                    deleteTable.deleteTable();
                    break;
                case 3:
                    insertRecordInTable.insertRecordInTable();
                    break;
                case 4:
                    updateRecordInTable.updateInTable();
                    break;
                case 5:
                    deleteRecordInTable.deleteInTable();
                    break;
                case 6:
                    selectRecordInTable.selectInTable();
                    break;
                case 7:
                    getAllTableNames.getTableNames();
                    break;
                case 8:
                    getTableSize.getTableSize();
                    break;
                case 9:
                    if (connection != null) {
                        connection.close();
                    }
                    System.out.println("*****************************");
                    System.out.println("*********  Goodbye  *********");
                    System.out.println("*****************************");
                    System.exit(0);
                    break;
                default:
                    System.out.println("******************************");
                    System.out.println("*****  Неверная команда  *****");
                    System.out.println("******************************\n");
            }
        }
    }
}

