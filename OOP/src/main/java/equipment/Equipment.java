package equipment;

/**
 * Created by Opanasiuk Valentyn on 11.04.17.
 */
public abstract class Equipment {
    @Override
    public String toString() {
        return "Equipment " + this.getClass().getSimpleName();
    }
}
