public class ParkingSpot {
    private int spotNumber;
    private int deckNumber;
    private boolean occupied;

    public ParkingSpot(int spotNumber, int deckNumber) {
        this.spotNumber = spotNumber;
        this.deckNumber = deckNumber;
        this.occupied = false; // By default, the spot is not occupied
    }

    public int getSpotNumber() {
        return spotNumber;
    }
    public void setSpotNumber(int spotNumber) { //probably not needed
        this.spotNumber = spotNumber;
    }

    public int getDeckNumber() {
        return deckNumber;
    }
    public void setDeckNumber(int deckNumber) { //probably not needed
        this.deckNumber = deckNumber;
    }

    public boolean isOccupied() {
        return occupied;
    }
    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }
}
