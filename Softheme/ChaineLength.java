import java.io.*;

/**
 * Created by Hunky_ua on 16.05.2016.
 */
public class ChaineLength {
    public static void main(String[] args) throws IOException {
        ChaineLength chaineLength = new ChaineLength();
        chaineLength.start();
    }

    void start() throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader input = null;
        BufferedWriter output = null;
        try {
            input = new BufferedReader(new FileReader("E://input.txt"));
            output = new BufferedWriter(new FileWriter("E://output.txt"));
            int ch;
            while((ch = input.read()) != -1){
                sb.append((char) ch);
            }
            int maxPoint = 0;
            int currentPoint = 0;

            for (int i = 0; i < sb.length(); i++) {
                if ((int) sb.charAt(i) == 49) {
                    currentPoint++;
                }
                if ((int) sb.charAt(i) == 48 && currentPoint != 0) {
                    if (currentPoint > maxPoint) {
                        maxPoint = currentPoint;
                    }
                    currentPoint = 0;
                }
            }
            output.write(maxPoint + "");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            if (input != null) {
                input.close();
            }
            if (output != null) {
                output.close();
            }
        }
    }
}