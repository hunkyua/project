package sqlcmd;

import java.util.Scanner;

/**
 * Created by oktopus on 10.10.2015.
 */
public class ViewImpl implements View {

    @Override
    public void write(String string) {
        System.out.println(string);
    }

    @Override
    public String read() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }
}
