import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class StartDivision {
    public static void main(String[] args) throws IOException {
        
        BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Введите делимое: ");
        String division=buffer.readLine();
        System.out.println("Введите делитель: ");
        String secondParam=buffer.readLine();
        int divider=Integer.valueOf(secondParam);


        Division example = new Division();
        example.printDivision(division, divider);


    }
}
