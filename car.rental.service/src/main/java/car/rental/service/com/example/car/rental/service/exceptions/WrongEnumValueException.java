package car.rental.service.com.example.car.rental.service.exceptions;

public class WrongEnumValueException extends RuntimeException {
    public WrongEnumValueException(String errorMessage) {
        super(errorMessage);
    }
}
