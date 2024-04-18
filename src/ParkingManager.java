import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class ParkingManager {

    private final List<ParkingDeck> decks;
    private Map<Vehicle, ParkingSpot> vehicleToSpotMap; //track parked vehicles
    private final int freeSpots = ConfigManager.getIntProperty("numberOfDecks") * ConfigManager.getIntProperty("spotsPerDeck");
    private int parkedVehicles;

    //method returning free spots
    public int freeSpots() {
        return (freeSpots - parkedVehicles);
    }

    public ParkingManager(List<ParkingDeck> decks) {
        this.decks = decks;
        this.vehicleToSpotMap = new HashMap<>();
    }

    public ParkingSpot findParkingSpot() {
        for (ParkingDeck deck : decks) {
            for (ParkingSpot spot : deck.getSpots()) {
                if (!spot.isOccupied()) {
                    return spot;
                }
            }
        }
        return null; //returns null if no free spots are available
    }

    public boolean unparkVehicle(Vehicle vehicle) {
        ParkingSpot spot = vehicleToSpotMap.get(vehicle);
        if (spot != null && spot.isOccupied()) {
            spot.setOccupied(false);
            vehicleToSpotMap.remove(vehicle); //remove vehicle from map
            parkedVehicles--; //decrement counter for parked vehicle tracking
            return true;
        }
        return false;
    }

    public boolean freeSpot(int deckNumber, int spotNumber) {
        for (ParkingDeck deck : decks) {
            if (deck.getDeckNumber() == deckNumber) {
                for (ParkingSpot spot : deck.getSpots()) {
                    if (spot.getSpotNumber() == spotNumber && spot.isOccupied()) {
                        spot.setOccupied(false);
                        vehicleToSpotMap.values().remove(spot); //clear the vehicle link
                        return true;
                    }
                }
            }
        }
        return false; //return false if no spot was found or the spot was not occupied
    }

    public ParkingSpot parkVehicle(Vehicle vehicle) {
        ParkingSpot freeSpot = findParkingSpot();
        if (freeSpot != null) {
            freeSpot.setOccupied(true);
            vehicleToSpotMap.put(vehicle, freeSpot); //track vehicle
            parkedVehicles++; //increment counter for parked vehicle tracking
            return freeSpot;
        }
        return null;
    }

    public ParkingSpot findVehicle(String licensePlate) {
        for (Map.Entry<Vehicle, ParkingSpot> entry : vehicleToSpotMap.entrySet()) {
            if (entry.getKey().getLicensePlate().equals(licensePlate)) {
                return entry.getValue();
            }
        }
        return null; //return null if no vehicle with that license plate is found
    }
}
