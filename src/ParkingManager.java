import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class ParkingManager {

    private final List<ParkingDeck> decks;
    private final Map<Vehicle, ParkingSpot> vehicleToSpotMap; //initialize map to track parked vehicles
    private int parkedVehicles; //number of parked Vehicles

    public ParkingManager(List<ParkingDeck> decks) {
        this.decks = decks;
        this.vehicleToSpotMap = new HashMap<>();
    }

    //method returning free spots
    public int freeSpots() {
        ConfigManager configManager = ConfigManager.getInstance();
        int numberOfDecks = configManager.getIntProperty("numberOfDecks");
        int spotsPerDeck = configManager.getIntProperty("spotsPerDeck");
        int totalSpots = numberOfDecks * spotsPerDeck;
        return totalSpots - calculateOccupiedSpots();
    }

    private int calculateOccupiedSpots() {
        int occupiedSpots = 0;
        for (ParkingDeck deck : decks) {
            for (ParkingSpot spot : deck.getSpots()) {
                if (spot.isFullyOccupied()) {
                    occupiedSpots ++;
                }
            }
        }
        return occupiedSpots;
    }

    public ParkingSpot findParkingSpot() {
        for (ParkingDeck deck : decks) {
            for (ParkingSpot spot : deck.getSpots()) {
                if (!spot.isFullyOccupied()) {
                    return spot;
                }
            }
        }
        return null; //returns null if no free spots are available
    }

    public ParkingSpot parkVehicle(Vehicle vehicle) {
        ParkingSpot freeSpot = findParkingSpot();
        if (freeSpot != null && freeSpot.parkVehicle(vehicle)) {
            vehicleToSpotMap.put(vehicle, freeSpot); //track vehicle
            return freeSpot;
        }
        return null;
    }

    public boolean unparkVehicle(Vehicle vehicle) {
        ParkingSpot spot = vehicleToSpotMap.get(vehicle);
        if (spot != null) {
            spot.removeVehicle(vehicle);
            vehicleToSpotMap.remove(vehicle); //remove vehicle from map
            return true;
        }
        return false;
    }

    public ParkingSpot findVehicle(String licensePlate) {
        for (Map.Entry<Vehicle, ParkingSpot> entry : vehicleToSpotMap.entrySet()) {
            if (entry.getKey().getLicensePlate().equals(licensePlate)) {
                return entry.getValue();
            }
        }
        return null; //return null if no vehicle with that license plate is found
    }

//    public boolean freeSpot(int deckNumber, int spotNumber) {
//        for (ParkingDeck deck : decks) {
//            if (deck.getDeckNumber() == deckNumber) {
//                for (ParkingSpot spot : deck.getSpots()) {
//                    if (spot.getSpotNumber() == spotNumber && spot.isOccupied()) {
//                        spot.setOccupied(false);
//                        vehicleToSpotMap.values().remove(spot); //clear the vehicle link
//                        return true;
//                    }
//                }
//            }
//        }
//        return false; //return false if no spot was found or the spot was not occupie
//    }
}
