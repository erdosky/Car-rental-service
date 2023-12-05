package car.rental.service.com.example.car.rental.service.controllers;

import car.rental.service.com.example.car.rental.service.entities.User;
import car.rental.service.com.example.car.rental.service.exceptions.EmailTakenException;
import car.rental.service.com.example.car.rental.service.exceptions.UsernameTakenException;
import car.rental.service.com.example.car.rental.service.services.interfaces.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static car.rental.service.com.example.car.rental.service.Constants.*;
import static car.rental.service.com.example.car.rental.service.utils.UserUtils.validateUser;

@RestController
@RequestMapping("/auth")
@Tag(name = "Authorization", description = "Authorization operations")
public class AuthorizationController {
    @Autowired
    private UserService userService;

    @Operation(summary = "new user")
    @PostMapping("/register")
    public ResponseEntity<String> createUser(@RequestParam String username, @RequestParam String email, @RequestParam String password) {

        if (!validateUser(email, username, password)) {
            throw new ValidationException(INVALID_DATA);
        }

        if (userService.exist(username)) {
            throw new UsernameTakenException(USERNAME_TAKEN);
        }

        if (userService.findByEmail(email) != null) {
            throw new EmailTakenException(EMAIL_TAKEN);
        }

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(password);

        User user = new User(username, email, hashedPassword);
        userService.save(user);

        return new ResponseEntity<>(USER_CREATED, HttpStatus.CREATED);
    }

    @Operation(summary = "User Login")
    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestParam String username, @RequestParam String password, HttpSession session) {
        User user = userService.findByUsername(username);

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            session.setAttribute("username", username);
            session.setAttribute("password", password);
            return new ResponseEntity<>("User logged in", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("wrong username or password", HttpStatus.UNAUTHORIZED);
        }
    }

    @Operation(summary = "User Logout")
    @PostMapping("/logout")
    public ResponseEntity<String> logoutUser(HttpSession session) {
        session.invalidate();
        return new ResponseEntity<>("User logged out.", HttpStatus.OK);
    }
}