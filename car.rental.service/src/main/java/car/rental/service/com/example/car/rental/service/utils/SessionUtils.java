package car.rental.service.com.example.car.rental.service.utils;

import car.rental.service.com.example.car.rental.service.entities.User;
import car.rental.service.com.example.car.rental.service.services.interfaces.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class SessionUtils {
    public static void validateSession(HttpSession session, UserService userService) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String username = (String) session.getAttribute("username");
        String sessionPassword = (String) session.getAttribute("password");

        if (username == null || sessionPassword == null) {
            throw new IllegalStateException("You have to be logged in to perform this operation.");
        }

        User user = userService.findByUsername(username);
        if (user == null || !passwordEncoder.matches(sessionPassword, user.getPassword())) {
            throw new IllegalStateException("Not valid user.");
        }
    }
}
