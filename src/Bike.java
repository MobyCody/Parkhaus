public class Bike extends Vehicle {
    public Bike(String licensePlate) {
        super(licensePlate);
    }

    @Override
    public double spaceRequired() {
        return 0.5; // A bike takes half the space of a car
    }
}