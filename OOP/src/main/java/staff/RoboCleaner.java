package staff;

import apartment.Apartment;

/**
 * Created by Opanasiuk Valentyn on 11.04.17.
 */
public class RoboCleaner implements Cleaner {
    private String nick;

    public RoboCleaner(String nick) {
        this.nick = nick;
    }

    public void clean(Apartment apartment) {
        System.out.println("RoBoBoBo " + nick + " Cleaning...");
        System.out.println("Done!");
    }
}
