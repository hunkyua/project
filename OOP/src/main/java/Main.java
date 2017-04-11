import equipment.Owner;

/**
 * Created by Opanasiuk Valentyn on 07.04.17.
 */
public class Main {
    public static void main(String[] args) {

        House house = new House(16, 4);
        Owner owner1 = new Owner("Stiven.Pupkin");
        Owner owner2 = new Owner("Selena.Pupkina");
        Owner owner3 = new Owner("Adam.Pupkina");
        Owner owner4 = new Owner("Flora.Pupkina");
        Owner owner5 = new Owner("Bob.Marley");


        house.settle(owner1);
        house.settle(owner2);
        house.settle(owner3);
        house.settle(owner4);
        house.settle(owner5);

        System.out.println(house.toString());

    }
}
