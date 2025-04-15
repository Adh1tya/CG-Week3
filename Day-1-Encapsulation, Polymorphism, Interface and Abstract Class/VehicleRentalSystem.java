
interface Insurable {
    double calculateInsurance();
    String getInsuranceDetails();
}

abstract class Vehicle {
    private String vehicleNumber;
    private String type;
    private double rentalRate;
    private String insurancePolicyNumber; 

    public Vehicle(String vehicleNumber, String type, double rentalRate, String insurancePolicyNumber) {
        this.vehicleNumber = vehicleNumber;
        this.type = type;
        this.rentalRate = rentalRate;
        this.insurancePolicyNumber = insurancePolicyNumber;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public String getType() {
        return type;
    }

    public double getRentalRate() {
        return rentalRate;
    }

    protected String getInsurancePolicyNumber() {
        return insurancePolicyNumber.substring(insurancePolicyNumber.length() - 4); 
    }

    public abstract double calculateRentalCost(int days);

    public void displayDetails() {
        System.out.println("Vehicle Number: " + vehicleNumber);
        System.out.println("Type: " + type);
        System.out.println("Rental Rate: $" + rentalRate + " per day");
    }
}

class Car extends Vehicle implements Insurable {
    public Car(String vehicleNumber, double rentalRate, String policy) {
        super(vehicleNumber, "Car", rentalRate, policy);
    }

    @Override
    public double calculateRentalCost(int days) {
        return getRentalRate() * days;
    }

    @Override
    public double calculateInsurance() {
        return getRentalRate() * 0.15; 
    }

    @Override
    public String getInsuranceDetails() {
        return "Car Insurance Policy (partial): " + getInsurancePolicyNumber();
    }
}


class Bike extends Vehicle implements Insurable {
    public Bike(String vehicleNumber, double rentalRate, String policy) {
        super(vehicleNumber, "Bike", rentalRate, policy);
    }

    @Override
    public double calculateRentalCost(int days) {
        return getRentalRate() * days;
    }

    @Override
    public double calculateInsurance() {
        return getRentalRate() * 0.05; 
    }

    @Override
    public String getInsuranceDetails() {
        return "Bike Insurance Policy (partial): " + getInsurancePolicyNumber();
    }
}


class Truck extends Vehicle implements Insurable {
    public Truck(String vehicleNumber, double rentalRate, String policy) {
        super(vehicleNumber, "Truck", rentalRate, policy);
    }

    @Override
    public double calculateRentalCost(int days) {
        return getRentalRate() * days + 100;
    }

    @Override
    public double calculateInsurance() {
        return getRentalRate() * 0.2;
    }

    @Override
    public String getInsuranceDetails() {
        return "Truck Insurance Policy (partial): " + getInsurancePolicyNumber();
    }
}

public class VehicleRentalSystem {
    public static void main(String[] args) {
        Vehicle[] vehicles = {
            new Car("CAR123", 50, "CARINS123456"),
            new Bike("BIKE456", 20, "BIKEINS789012"),
            new Truck("TRK789", 100, "TRUCKINS345678")
        };

        int days = 5;

     
        for (Vehicle v : vehicles) {
            v.displayDetails();
            System.out.println("Rental Cost for " + days + " days: $" + v.calculateRentalCost(days));

            if (v instanceof Insurable) {
                Insurable ins = (Insurable) v;
                System.out.println("Insurance Cost: $" + ins.calculateInsurance());
                System.out.println(ins.getInsuranceDetails());
            }

        }
    }
}
