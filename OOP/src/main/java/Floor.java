import apartment.Apartment;
import apartment.LivingApartment;
import apartment.TechnicalApartment;
import staff.Cleaner;

/**
 * Created by Opanasiuk Valentyn on 07.04.17.
 */
public class Floor {
    private static final int DEFAULT_APARTMENT_CAPACITY = 4;
    private int number;
    private Apartment[] apartments;
    private Cleaner cleaner;

    public Floor(int number, int apartmentsCount, NumberGenerator numbers) {
        this.number = number;
        this.apartments = new Apartment[apartmentsCount];
        this.apartments[0] = new TechnicalApartment(numbers.getNext());
        for (int index = 1; index < apartmentsCount; index++) {
            apartments[index] = new LivingApartment(numbers.getNext(), DEFAULT_APARTMENT_CAPACITY);
        }
    }

    public LivingApartment getFreeApartment() {
       for (Apartment apartment : apartments) {
           if (apartment instanceof LivingApartment && apartment.isFree()) {
               LivingApartment livingApartment = (LivingApartment) apartment;
               if (!livingApartment.isSettled()) {
                   cleaner.clean(apartment);
               }
               return livingApartment;
           }
       }
       return null;
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

    public void setCleaner(Cleaner cleaner) {
        this.cleaner = cleaner;
    }
}
