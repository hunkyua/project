import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Hunky_ua on 16.05.2016.
 */
public class Even {
    public static void main(String[] args) {
    even();
    }

    public static void even (){
        try {
            System.out.println("Write a number");
            int number = reader();
            for (int i = 1; i <= number; i++) {
                if ((number % i) == 0) {
                    System.out.println(i);
                }
            }
        } catch (Exception e) {
            System.out.println("Error");
        }
    }
    public static Integer reader() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int number = Integer.parseInt(reader.readLine());
        return number;
    }
}
