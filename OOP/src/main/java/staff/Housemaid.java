package staff;

import apartment.Apartment;
import owner.Owner;

/**
 * Created by Opanasiuk Valentyn on 11.04.17.
 */
public class Housemaid extends Owner implements Cleaner {

    public Housemaid(String name) {
        super(name);
    }

    public void clean(Apartment apartment) {
        System.out.println("Cleaning apartment " + apartment.getNumber());
        System.out.println("Cleaning done by " + name);
    }
}
