package car.rental.service.com.example.car.rental.service.exceptions;

public class CarAlreadyExist extends RuntimeException {
    public CarAlreadyExist(String errorMessage) {
        super(errorMessage);
    }
}
