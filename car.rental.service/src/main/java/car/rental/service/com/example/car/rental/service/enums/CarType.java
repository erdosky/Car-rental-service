package car.rental.service.com.example.car.rental.service.enums;

public enum CarType {
    SEDAN("SEDAN"), SUV("SUV"), COUPE("COUPE"), CONVERTIBLE("CONVERTIBLE"), HATCHBACK("HATCHBACK"), STATION_WAGON("STATION WAGON"), PICKUP_TRUCK("PICKUP TRUCK"), SPORTS_CAR("SPORTS CAR"), MINIVAN("MINIVAN"), ELECTRIC("ELECTRIC");

    private final String stringValue;

    CarType(String stringValue) {
        this.stringValue = stringValue;
    }

    public String getStringValue() {
        return stringValue;
    }
}
