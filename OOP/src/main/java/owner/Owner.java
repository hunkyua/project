package owner;

/**
 * Created by Opanasiuk Valentyn on 07.04.17.
 */
public class Owner {

    protected final String name;

    public Owner(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
