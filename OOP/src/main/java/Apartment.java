/**
 * Created by Opanasiuk Valentyn on 07.04.17.
 */
public class Apartment {

    private int number;
    private Owner[] owners;

    public Apartment(int number, int capacity) {
        this.number = number;
        this.owners = new Owner[capacity];
    }

    public void addOwner(Owner owner) {
        owners[getFreeRoomIndex()] = owner;
    }

    @Override
    public String toString() {
        String result = "Apartment number " + number + "\n";
        for (int index = 0; index < owners.length; index++) {
            Owner owner = owners[index];
            if (owner != null ){
                result += "***********\n";
                result += "Owner: " + owner.toString() + "\n";
                result += "***********\n";
            }
        }
        return result;
    }

    public int getFreeRoomIndex() {
        for (int index = 0; index < owners.length; index++) {
            if (owners[index] == null) {
                return index;
            }
        }
        throw new RuntimeException("No free rooms for owner");
    }

    public boolean isFree() {
        return owners[owners.length - 1] == null;
    }
}
