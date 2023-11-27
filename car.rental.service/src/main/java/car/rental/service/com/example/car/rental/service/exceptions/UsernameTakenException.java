package car.rental.service.com.example.car.rental.service.exceptions;

public class UsernameTakenException extends RuntimeException {
    public UsernameTakenException(String errorMessage) {
        super(errorMessage);
    }
}
