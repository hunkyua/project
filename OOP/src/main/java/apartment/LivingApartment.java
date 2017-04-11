package apartment;

import owner.Owner;
/**
 * Created by Opanasiuk Valentyn on 07.04.17.
 */
public class LivingApartment extends Apartment {

    private Owner[] owners;

    public LivingApartment(int number, int capacity) {
        super(number);
        this.owners = new Owner[capacity];
    }

    public void addOwner(Owner owner) {
        owners[getFreeRoomIndex()] = owner;
    }

    @Override
    public String toString() {
        String result = super.toString();
        for (int index = 0; index < owners.length; index++) {
            Owner owner = owners[index];
            if (owner != null ){
                result += "***********\n";
                result += "owner.Owner: " + owner.toString() + "\n";
                result += "***********\n";
            }
        }
        return result;
    }

    private int getFreeRoomIndex() {
        for (int index = 0; index < owners.length; index++) {
            if (owners[index] == null) {
                return index;
            }
        }
        throw new RuntimeException("No free rooms for owner");
    }

    @Override
    public boolean isFree() {
        return owners[owners.length - 1] == null;
    }
}
