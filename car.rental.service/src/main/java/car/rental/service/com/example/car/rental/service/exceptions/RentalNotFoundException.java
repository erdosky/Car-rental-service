package car.rental.service.com.example.car.rental.service.exceptions;

public class RentalNotFoundException extends RuntimeException {
    public RentalNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}