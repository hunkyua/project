/**
 * Created by paso4ka7 on 07.04.17.
 */
public class Main {
    public static void main(String[] args) {

        House house = new House(16, 4);
        Owner owner1 = new Owner("Stiven.Pupkin");
        Owner owner2 = new Owner("Selena.Pupkina");

        house.settle(owner1);
        house.settle(owner2);

        System.out.println(house.toString());


        //        System.out.println("Test - Main Work");
//        Vehicle bmw = new Vehicle("BMW", "Germany", 2016);
//        System.out.println(bmw.toString());
    }
}
