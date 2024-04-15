import java.util.ArrayList;
import java.util.List;

public class ParkingDeck {

    private List<ParkingSpot> spots;
    private int deckNumber;

    public ParkingDeck(int deckNumber) {
        this.deckNumber = deckNumber;
        this.spots = new ArrayList<>(); // Initialize the list here
    }

    public void addSpot(ParkingSpot spot) {
        spots.add(spot);
    }

    public List<ParkingSpot> getSpots() {
        return spots;
    }

    public int getDeckNumber() {
        return deckNumber;
    }

    // Consider if setting the deck number post-creation is necessary; if not, remove this method
    public void setDeckNumber(int deckNumber) {
        this.deckNumber = deckNumber;
    }
}
