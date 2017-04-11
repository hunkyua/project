package apartment;

/**
 * Created by Opanasiuk Valentyn on 11.04.17.
 */
public abstract class Apartment {

    private int number;

    public Apartment(int number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + " number " + number + "\n";
    }

    public boolean isFree() {
        return false;
    }

    public int getNumber() {
        return number;
    }

}
