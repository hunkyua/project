package JDBCConnector;

import JDBCConnector.ActionWithRecordTable.DeleteInTable;
import JDBCConnector.ActionWithRecordTable.InsertInTable;
import JDBCConnector.ActionWithRecordTable.SelectInTable;
import JDBCConnector.ActionWithRecordTable.UpdateInTable;
import JDBCConnector.ActionWithTable.CreateTable;
import JDBCConnector.ActionWithTable.DeleteTable;

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
        jdbc.getDBConnection();

        while (true) {
            System.out.println("Введи команду 1...7 (или 0 для помощи) :");
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

