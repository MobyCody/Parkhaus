import java.util.Scanner;

public class UIHandler {
    private ParkingManager parkingManager;
    private Scanner scanner;

    public UIHandler(ParkingManager parkingManager) {
        this.parkingManager = parkingManager;
        this.scanner = new Scanner(System.in);
    }

    public void menu() {
        System.out.println("""
                    ----- Menu -----
                    What do you want to do?
                    Please choose one option by entering the respective number.
                    
                    0: Exit Program
                    1: Park my Vehicle
                    2: Find my Vehicle
                    3: Check out
                    
                    """);

        int menuInput = scanner.nextInt();

        switch (menuInput) {
            case 0:
                System.out.println("Exiting program...");
                System.exit(0);
                break;
            case 1:
                System.out.println("Please enter your vehicle's license plate:");
                String plate = scanner.next();
                Vehicle vehicle = new Vehicle(plate);
                if (parkingManager.findVehicleSpot(plate) == null) { //check if vehicle is already in map
                    ParkingSpot spot = parkingManager.parkVehicle(vehicle);
                    if (spot != null) {
                        System.out.println("Your vehicle has been parked in Spot " + spot.getSpotNumber() + " on Deck " + spot.getDeckNumber());
                    } else {
                        System.out.println("Sorry, no spots available.");
                    }
                } else {
                    System.out.println("This vehicle is already parked.");
                }
                break;
            case 2:
                System.out.println("Please enter your vehicle's license plate to find it:");
                String findPlate = scanner.next();
                ParkingSpot foundSpot = parkingManager.findVehicleSpot(findPlate);
                if (foundSpot != null) {
                    System.out.println("Your vehicle is located at: Deck " + foundSpot.getDeckNumber() + " in Spot " + foundSpot.getSpotNumber());
                } else {
                    System.out.println("Vehicle not found.");
                }
                break;
            case 3:
                System.out.println("Please enter your vehicle's license plate to check out:");
                String checkoutPlate = scanner.next();
                if (parkingManager.unparkVehicle(new Vehicle(checkoutPlate))) {
                    System.out.println("Thank you for visiting, your vehicle has been checked out.");
                } else {
                    System.out.println("Vehicle check out failed or vehicle not found.");
                }
                break;
        }
    }

    public void closeScanner() {
        scanner.close();
    }
}
