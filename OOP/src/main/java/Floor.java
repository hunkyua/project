/**
 * Created by Opanasiuk Valentyn on 07.04.17.
 */
public class Floor {
    private static final int DEFAULT_APARTMENT_CAPACITY = 4;
    private int number;
    private AbstractApartment[] apartments;

    public Floor(int number, int apartmentsCount, NumberGenerator numbers) {
        this.number = number;
        this.apartments = new AbstractApartment[apartmentsCount];
        this.apartments[0] = new TechnicalApartment(numbers.getNext());
        for (int index = 1; index < apartmentsCount; index++) {
            apartments[index] = new LivingApartment(numbers.getNext(), DEFAULT_APARTMENT_CAPACITY);
        }
    }

    public LivingApartment getFreeApartment() {
       for (AbstractApartment apartment : apartments) {
           if (apartment instanceof LivingApartment && apartment.isFree()) {
               return (LivingApartment) apartment;
           }
       }
       return null;
    }

    @Override
    public String toString() {
        String result = "============================\n";
        result += "Floor number " + number + "\n";
        result += "----------------------------\n";
        for (AbstractApartment apartment: apartments) {
            result += apartment.toString() + "\n";
        }
        result += "============================\n";
        return result;
    }
}
