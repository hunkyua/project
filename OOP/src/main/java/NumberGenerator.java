/**
 * Created by Opanasiuk Valentyn on 07.04.17.
 */
public class NumberGenerator {
    private int current;

    public NumberGenerator() {
        current = 1;
    }

    public int getNext() {
        int result = current;
        current++;
        return result;
    }
}
