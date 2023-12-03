package car.rental.service.com.example.car.rental.service.enums;

public enum CarStatus {
    AVAILABLE("Available"), UNAVAILABLE("Unavailable"), RENTED("Rented");

    private final String stringValue;

    CarStatus(String stringValue) {
        this.stringValue = stringValue;
    }

    public String getStringValue() {
        return stringValue;
    }
}
