/**
 * Created by Opanasiuk Valentyn on 07.04.17.
 */
public class Floor {
    private int number;
    private Apartment[] apartments;

    public Floor(int number, int floorsCount) {
        this.number = number;
        this.apartments = new Apartment[floorsCount];
        for (int index = 0; index < floorsCount; index++) {
            apartments[index] = new Apartment(index + 1);
        }
    }

    public Apartment getFreeApartment() {
        return null; //TODO implement me
    }

    @Override
    public String toString() {
        String result = "============================\n";
        result += "Floor number " + number + "\n";
        result += "----------------------------\n";
        for (Apartment apartment: apartments) {
            result += apartment.toString() + "\n";
        }
        result += "============================\n";
        return result;
    }
}
