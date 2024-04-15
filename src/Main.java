import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        public void menu() {
            Scanner scn = new Scanner(System.in);
            System.out.println("""
                    ----- Menu -----
                    Please choose one option by entering the respective number.
                    
                    0: Exit Programm
                    1: Overview Parking Spots
                    2: Park a new Vehicle
                    3: Unpark a Vehicle
                    
                    """);

            String menuInput = scn.nextInt();

            switch (menuInput) {
                case 0:
                    System.out.println("Exiting program...");
                    System.exit(0);
                    break;
                case 1:
                    SpotTracking.overview();
                    break;
                case 2:
                    Parking.parkingVehicle();
                    break;
                case 3:
                    Unparking.unparkingVehicle();
                    break;
            }
        }
    }
}