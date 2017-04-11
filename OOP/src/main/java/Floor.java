/**
 * Created by Opanasiuk Valentyn on 07.04.17.
 */
public class Floor {
    private static final int DEFAULT_APARTMENT_CAPACITY = 4;
    private int number;
    private Apartment[] apartments;

    public Floor(int number, int apartmentssCount, NumberGenerator numbers) {
        this.number = number;
        this.apartments = new Apartment[apartmentssCount];
        for (int index = 0; index < apartmentssCount; index++) {
            apartments[index] = new Apartment(numbers.getNext());
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
