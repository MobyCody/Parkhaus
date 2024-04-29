import java.util.Scanner;

public class UIHandler {
    private static volatile UIHandler instance;
    private ParkingManager parkingManager;
    private final Scanner scanner;

    public UIHandler(ParkingManager parkingManager) {
        this.parkingManager = parkingManager;
        this.scanner = new Scanner(System.in);
    }

    public static UIHandler getInstance(ParkingManager parkingManager) {
        if (instance == null) {
            synchronized (UIHandler.class) {
                if (instance == null) {
                    instance = new UIHandler(parkingManager);
                }
            }
        }
        return instance;
    }

    private void pressEnter() {
        System.out.println("Press Enter key to continue...");
        scanner.nextLine();
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
                    4: Count free spots
                    5: Enter Setup
                    
                    """);


        int menuInput = scanner.nextInt();

        switch (menuInput) {
            case 0:
                System.out.println("Exiting program...");
                System.exit(0);
                break;
            case 1:
                handleParkVehicle();
                break;
            case 2:
                handleFindVehicle();
                break;
            case 3:
                handleCheckoutVehicle();
                break;
            case 4:
                handleCountFreeSpots();
                break;
            case 5:
                handleSetUp();
                break;
        }
    }

    private void handleParkVehicle() {
        System.out.println("Please enter your vehicle's license plate:");
        String plate = scanner.next();
        Vehicle vehicle = new Car(plate);
        if (parkingManager.findVehicle(plate) == null) { //check if vehicle is already in map
            ParkingSpot spot = parkingManager.parkVehicle(vehicle);
            if (spot != null) {
                System.out.println("Your vehicle has been parked in Spot " + spot.getSpotNumber() +
                        " on Deck " + spot.getDeckNumber());
                pressEnter();
            } else {
                System.out.println("Sorry, no spots available.");
                pressEnter();
            }
        } else {
            System.out.println("This vehicle is already parked.");
            pressEnter();
        }
    }

    private void handleFindVehicle() {
        System.out.println("Please enter your vehicle's license plate to find it:");
        String findPlate = scanner.next();
        ParkingSpot foundSpot = parkingManager.findVehicle(findPlate);
        if (foundSpot != null) {
            System.out.println("Your vehicle is located at: Deck " + foundSpot.getDeckNumber() +
                    " in Spot " + foundSpot.getSpotNumber());
            pressEnter();
        } else {
            System.out.println("Vehicle not found.");
            pressEnter();
        }
    }

    private void handleCheckoutVehicle() {
        System.out.println("Please enter your vehicle's license plate to check out:");
        String checkoutPlate = scanner.next();
        if (parkingManager.unparkVehicle(new Car(checkoutPlate))) {
            System.out.println("Thank you for visiting, your vehicle has been checked out.");
            pressEnter();
        } else {
            System.out.println("Vehicle check out failed or vehicle not found.");
            pressEnter();
        }
    }

    private void handleCountFreeSpots() {
        System.out.println("There are " + parkingManager.freeSpots() + " free parking spots.");
        pressEnter();
    }

    private void handleSetUp() {
        System.out.println("How many decks does the garage have?");
        String decks = scanner.nextLine();
        ConfigManager.getInstance().setProperty("numberOfDecks", decks);
        System.out.println("How many spots per deck?");
        String spots = scanner.nextLine();
        ConfigManager.getInstance().setProperty("spotsPerDeck", spots);
    }

//    public void closeScanner() {
//        scanner.close();
//    }
}
