package car.rental.service.com.example.car.rental.service.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import jakarta.validation.ValidationException;
import car.rental.service.com.example.car.rental.service.exceptions.WrongDatesException;

public class RentalUtils {

    public static void validateRentalRequest(LocalDate endDate, String carModel, String userEmail) throws ValidationException {
        LocalDate startDate = LocalDate.now();

        if (!isValidCarModel(carModel)) {
            throw new ValidationException("Book title is not valid");
        }

        if (!isValidEmail(userEmail)) {
            throw new ValidationException("Email is not valid");
        }

        if (!isSameFormat(endDate.toString())) {
            throw new WrongDatesException("Wrong end date format.");
        }

        if (!endDate.isAfter(startDate)) {
            throw new WrongDatesException("Start date should be before end date.");
        }
    }

    public static boolean isValidEmail(String email) {
        if (email == null) {
            return false;
        }

        if (email.length() > 320) {
            return false;
        }
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private static boolean isSameFormat(String date) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate parsedDate = LocalDate.parse(date, formatter);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean isValidCarModel(String carModel) {
        if (carModel == null) {
            return false;
        }
        if (carModel.length() > 50) {
            return false;
        }
        return carModel.matches("[a-zA-Z0-9 \\-\\/]+");
    }
}
