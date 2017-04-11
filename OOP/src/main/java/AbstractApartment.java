/**
 * Created by Opanasiuk Valentyn on 11.04.17.
 */
public class AbstractApartment {

    private int number;

    public AbstractApartment(int number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + " number " + number + "\n";
    }

    public boolean isFree() {
        return false;
    }
}
