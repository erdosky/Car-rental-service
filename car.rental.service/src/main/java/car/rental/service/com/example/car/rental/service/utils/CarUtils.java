package car.rental.service.com.example.car.rental.service.utils;

import car.rental.service.com.example.car.rental.service.enums.CarType;

public class CarUtils {

    public static boolean validType(String value) {
        try {
            CarType.valueOf(CarType.class, value);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}
