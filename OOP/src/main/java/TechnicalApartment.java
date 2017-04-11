import equipment.Bucket;
import equipment.Equipment;
import equipment.Extinguisher;
import equipment.Swob;

import java.util.Arrays;

/**
 * Created by Opanasiuk Valentyn on 11.04.17.
 */
public class TechnicalApartment extends AbstractApartment {

    private Equipment[] equipments;

    public TechnicalApartment(int number) {
        super(number);
        equipments = new Equipment[10];
        equipments[0] = new Extinguisher();
        equipments[1] = new Bucket();
        equipments[2] = new Swob();
    }

    @Override
    public String toString() {
        return super.toString() + "\n" +
                "With objects: " + Arrays.toString(equipments);
    }
}
