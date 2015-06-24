package division;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class StartDivision {
    public static void main(String[] args) throws IOException {
        BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Введите делимое: ");
        String division=buffer.readLine();
        System.out.println("Введите делитель: ");
        String secondParam=buffer.readLine();
        int divider=Integer.valueOf(secondParam);

        String out = "  " + division + " |" + divider;
        System.out.println(out);

        Division example = new Division();
        List<String> stringsToOutput = example.printDivision(division, divider);

        String space = " ";
        for (int i = 0; i < stringsToOutput.size(); i++) {
            System.out.println(space + stringsToOutput.get(i));
            if (i % 3 == 0) {
                space += "   ";
            }
        }
    }
}
