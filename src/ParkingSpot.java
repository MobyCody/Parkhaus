public class ParkingSpot {
    private int spotNumber;
    private int deckNumber;
    private boolean occupied;

    // Constructor to initialize the ParkingSpot with its number and deck number
    public ParkingSpot(int spotNumber, int deckNumber) {
        this.spotNumber = spotNumber;
        this.deckNumber = deckNumber;
        this.occupied = false; // By default, the spot is not occupied
    }

    // Getter for spotNumber
    public int getSpotNumber() {
        return spotNumber;
    }

    // Setter for spotNumber - consider if changing spot number is necessary, typically this might not be changed
    public void setSpotNumber(int spotNumber) {
        this.spotNumber = spotNumber;
    }

    // Getter for deckNumber
    public int getDeckNumber() {
        return deckNumber;
    }

    // Setter for deckNumber - as with spot number, consider if this should be changeable
    public void setDeckNumber(int deckNumber) {
        this.deckNumber = deckNumber;
    }

    // Check if the spot is occupied
    public boolean isOccupied() {
        return occupied;
    }

    // Set the occupied status
    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }
}
