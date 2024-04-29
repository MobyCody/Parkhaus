public class Car extends Vehicle {
    public Car(String licensePlate) {
        super(licensePlate);
    }

    @Override
    public double spaceRequired() {
        return 1.0; // Cars require one full spot
    }
}