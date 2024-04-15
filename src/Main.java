import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Create parking spots and decks
        List<ParkingDeck> decks = new ArrayList<>();

        // Example: Create two decks
        ParkingDeck deck1 = new ParkingDeck(1);
        ParkingDeck deck2 = new ParkingDeck(2);

        // Add spots to deck 1
        for (int i = 1; i <= 10; i++) {
            deck1.addSpot(new ParkingSpot(i, 1));
        }

        // Add spots to deck 2
        for (int i = 11; i <= 20; i++) {
            deck2.addSpot(new ParkingSpot(i, 2));
        }

        // Add decks to the list
        decks.add(deck1);
        decks.add(deck2);

        // Initialize the ParkingManager with these decks
        ParkingManager parkingManager = new ParkingManager(decks);

        // Initialize the UIHandler with the ParkingManager
        UIHandler uiHandler = new UIHandler(parkingManager);

        // Start the menu system
        while (true) {
            uiHandler.menu();
        }
    }
}
