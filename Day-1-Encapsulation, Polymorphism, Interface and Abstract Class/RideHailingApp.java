interface GPS {
    String getCurrentLocation();
    void updateLocation(String location);
}
abstract class Vehicle {
    private String vehicleId;
    private String driverName;
    private double ratePerKm;
    public Vehicle(String vehicleId, String driverName, double ratePerKm) {
        this.vehicleId = vehicleId;
        this.driverName = driverName;
        this.ratePerKm = ratePerKm;
    }
    public String getVehicleId() {
        return vehicleId;
    }
    public String getDriverName() {
        return driverName;
    }
    public double getRatePerKm() {
        return ratePerKm;
    }

    
    public abstract double calculateFare(double distance);
    public void getVehicleDetails() {
        System.out.println("Vehicle ID: " + vehicleId + ", Driver: " + driverName + ", Rate/km: ₹" + ratePerKm);
    }
}


class Car extends Vehicle implements GPS {
    private String currentLocation;
    public Car(String vehicleId, String driverName, double ratePerKm) {
        super(vehicleId, driverName, ratePerKm);
        this.currentLocation = "Unknown";
    }
    public double calculateFare(double distance) {
        return distance * getRatePerKm();
    }
    public String getCurrentLocation() {
        return currentLocation;
    }
    public void updateLocation(String location) {
        currentLocation = location;
    }
}
class Bike extends Vehicle implements GPS {
    private String currentLocation;

    public Bike(String vehicleId, String driverName, double ratePerKm) {
        super(vehicleId, driverName, ratePerKm);
        this.currentLocation = "Unknown";
    }

    @Override
    public double calculateFare(double distance) {
        return distance * getRatePerKm();
    }

    @Override
    public String getCurrentLocation() {
        return currentLocation;
    }

    @Override
    public void updateLocation(String location) {
        currentLocation = location;
    }
}


class Auto extends Vehicle implements GPS {
    private String currentLocation;
    public Auto(String vehicleId, String driverName, double ratePerKm) {
        super(vehicleId, driverName, ratePerKm);
        this.currentLocation = "Unknown";
    }
    public double calculateFare(double distance) {
        return 20 + (distance * getRatePerKm());
    }
    public String getCurrentLocation() {
        return currentLocation;
    }
    public void updateLocation(String location) {
        currentLocation = location;
    }
}
public class RideHailingApp {
    public static void processRides(Vehicle[] rides, double distance) {
        System.out.println("Ride Summary");
        for (Vehicle ride : rides) {
            ride.getVehicleDetails();
            if (ride instanceof GPS) {
                System.out.println("Current Location: " + ((GPS) ride).getCurrentLocation());
            }
            System.out.println("Estimated Fare for " + distance + " km: " + ride.calculateFare(distance));
            System.out.println("");
        }
    }
    public static void main(String[] args) {
        Vehicle v1 = new Car("C001", "Alice", 15.0);
        Vehicle v2 = new Bike("B001", "Bob", 8.0);
        Vehicle v3 = new Auto("A001", "Charlie", 10.0);
        ((GPS) v1).updateLocation("MG Road");
        ((GPS) v2).updateLocation("City Center");
        ((GPS) v3).updateLocation("Main Market");
        Vehicle[] rides = { v1, v2, v3 };
        processRides(rides, 12.5); 
    }
}
