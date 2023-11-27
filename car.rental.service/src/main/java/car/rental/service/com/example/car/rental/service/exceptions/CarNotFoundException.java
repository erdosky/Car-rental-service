package car.rental.service.com.example.car.rental.service.exceptions;

public class CarNotFoundException extends RuntimeException {
    public CarNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
