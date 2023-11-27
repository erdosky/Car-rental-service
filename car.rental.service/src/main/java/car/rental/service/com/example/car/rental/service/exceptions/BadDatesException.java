package car.rental.service.com.example.car.rental.service.exceptions;

public class BadDatesException extends RuntimeException {
    public BadDatesException(String errorMessage) {
        super(errorMessage);
    }
}
