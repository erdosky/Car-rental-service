package car.rental.service.com.example.car.rental.service.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserUtils {
    public static boolean validateUser(String email, String username, String password) {
        String emailPattern = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailPattern);
        Matcher matcher = pattern.matcher(email);
        return (matcher.matches() && username.length() >= 3 && password.length() >= 6);
    }
}
