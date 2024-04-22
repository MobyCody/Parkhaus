import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        ConfigManager configManager = ConfigManager.getInstance(); // makes sure properties are loaded

        int numberOfDecks = configManager.getIntProperty("numberOfDecks");
        int spotsPerDeck = configManager.getIntProperty("spotsPerDeck");
//        boolean twoBikesPerSpot = ConfigManager.getBooleanProperty("twoBikesPerSpot"); //not used yet

        //create parking spots and decks
        List<ParkingDeck> decks = new ArrayList<>();

        //add decks according to config
        for (int i = 1; i <= numberOfDecks; i++) {
            ParkingDeck deck = new ParkingDeck(i);
            for (int j = 1; j <= spotsPerDeck; j++) {
                deck.addSpot(new ParkingSpot(j, i));
            }
            decks.add(deck);
        }

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
