import java.util.ArrayList;
import java.util.List;

public class ParkingSpot {
    private final int spotNumber;
    private final int deckNumber;
    private List<Vehicle> parkedVehicles;
    private final double capacity; //normal sized parking spot gets 1

    public ParkingSpot(int spotNumber, int deckNumber) {
        this.spotNumber = spotNumber;
        this.deckNumber = deckNumber;
        this.parkedVehicles = new ArrayList<>();
        this.capacity = 1.0;
    }

    public boolean canPark(Vehicle vehicle) {
        double usedCapacity = parkedVehicles.stream().mapToDouble(Vehicle::spaceRequired).sum();
        return (usedCapacity + vehicle.spaceRequired()) <= capacity; //returns true if fits, false if it doesn't
    }

    public boolean parkVehicle(Vehicle vehicle) {
        if (canPark(vehicle)) {
            parkedVehicles.add(vehicle);
            return true;
        }
        return false;
    }

    private double getCurrentUsage() {
        return parkedVehicles.stream()
                .mapToDouble(Vehicle::spaceRequired)
                .sum();
    }

    public boolean isFullyOccupied() {
        return getCurrentUsage() >= capacity;
    }

    public void removeVehicle(Vehicle vehicle) {
        parkedVehicles.remove(vehicle);
    }

    public int getSpotNumber() {
        return spotNumber;
    }

    public int getDeckNumber() {
        return deckNumber;
    }

    public List<Vehicle> getParkedVehicles() {
        return parkedVehicles;
    }
}
