package sqlcmd;

/**
 * Created by oktopus on 10.10.2015.
 */
public interface View {
    void write(String string);

    String read();
}
