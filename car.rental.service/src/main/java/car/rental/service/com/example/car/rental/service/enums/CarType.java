package car.rental.service.com.example.car.rental.service.enums;

public enum CarType {
    SEDAN("Sedan"),
    SUV("SUV"),
    COUPE("Coupe"),
    CONVERTIBLE("Convertible"),
    HATCHBACK("Hatchback"),
    STATION_WAGON("Station Wagon"),
    PICKUP_TRUCK("Pickup Truck"),
    SPORTS_CAR("Sports Car"),
    MINIVAN("Minivan"),
    ELECTRIC("Electric");

    private final String stringValue;

    CarType(String stringValue) {
        this.stringValue = stringValue;
    }

    public String getStringValue() {
        return stringValue;
    }
}
