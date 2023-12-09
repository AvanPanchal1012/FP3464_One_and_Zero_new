enum VehicleType {
    RACE, NOT_FOR_RACE, Family
}

enum Gear {
    Manual, Automatic
}

enum CarType {
    Sport, SUV, Hatchback, Sedan
}

class Vehicle {
    private String make;
    private String plate;
    private String color;
    private VehicleType category;

    public Vehicle(String make, String plate, String color, VehicleType category) {
        this.make = make;
        this.plate = plate;
        this.color = color;
        this.category = category;
    }

    @Override
    public String toString() {
        return "\t- make: " + make + "\n\t- plate: " + plate + "\n\t- colour: " + color + "\n\t- category: " + category;
    }
}

class Car extends Vehicle {
    private Gear gear;
    private CarType type;

    public Car(String make, String plate, String color, VehicleType category, Gear gear, CarType type) {
        super(make, plate, color, category);
        this.gear = gear;
        this.type = type;
    }

    @Override
    public String toString() {
        return "Employee has a car \n"+ super.toString() + "\n\t- gear type: " + gear + "\n\t- type: " + type+"\n";
    }
}

class Motorcycle extends Vehicle {
    private boolean hasSidecar;

    public Motorcycle(String make, String plate, String color, VehicleType category, boolean hasSidecar) {
        super(make, plate, color, category);
        this.hasSidecar = hasSidecar;
    }

    @Override
    public String toString() {
        return "Employee has a motorcycle \n"+ super.toString() + "\n\t- " + (hasSidecar ? "with sidecar" : "without sidecar") + "\n";
    }

}