package car.rental.service.com.example.car.rental.service.exceptions;

public class EmailTakenException extends RuntimeException{
    public EmailTakenException(String errorMessage) {
        super(errorMessage);
    }
}
