import java.util.Scanner;

public class Parking {
    public static String parkingVehicle(String toPark){
        //TODO: complete function parking vehicles
        System.out.println("----- Parking Vehicle -----");
        Scanner scnParking = new Scanner(System.in);
        System.out.println("Which vehicle would you like to park?");
        String toPark = scnParking.nextLine();
        System.out.println("Vehicle [license plate] parked in Spot [Spot Address]");
    }
}