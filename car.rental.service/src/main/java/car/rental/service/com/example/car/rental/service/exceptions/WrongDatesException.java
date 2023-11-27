package car.rental.service.com.example.car.rental.service.exceptions;

public class WrongDatesException extends RuntimeException {
    public WrongDatesException(String errorMessage) {
        super(errorMessage);
    }
}
