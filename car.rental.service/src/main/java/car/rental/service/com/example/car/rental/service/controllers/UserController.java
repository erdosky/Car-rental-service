package car.rental.service.com.example.car.rental.service.controllers;

import car.rental.service.com.example.car.rental.service.entities.User;
import car.rental.service.com.example.car.rental.service.exceptions.UserNotFoundException;
import car.rental.service.com.example.car.rental.service.services.interfaces.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static car.rental.service.com.example.car.rental.service.utils.SessionUtils.validateSession;

@RestController
@RequestMapping("/user")
@Tag(name = "Users", description = "Users operations")
public class UserController {
    @Autowired
    private UserService userService;

    @Operation(summary ="user by given email")
    @GetMapping("/byEmail")
    public ResponseEntity<User> getUserByEmail(
            @RequestParam String email, HttpSession session) {
        validateSession(session, userService);
        User user = userService.findByEmail(email);

        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "user by given username")
    @GetMapping("/byUsername")
    public ResponseEntity<User> getUserByUsername(
            @RequestParam String username, HttpSession session) {
        validateSession(session, userService);
        User user = userService.findByUsername(username);

        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Get all users", description = "all users.")
    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers(HttpSession session) {
        validateSession(session, userService);
        List<User> users = userService.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @Operation(summary ="deletes user by given email")
    @DeleteMapping("/byEmail")
    public ResponseEntity<String> deleteByEmail(
            @RequestParam String email, HttpSession session) {
        validateSession(session, userService);
        if (userService.findByEmail(email) != null) {
            userService.deleteByEmail(email);
            return new ResponseEntity<>("User with email: " + email + "removed", HttpStatus.OK);
        }
        throw new UserNotFoundException("User with email " + email + " not found.");
    }

    @Operation(summary ="deletes user by given username")
    @DeleteMapping("/byUsername")
    public ResponseEntity<String> deleteByUsername(
            @RequestParam String username, HttpSession session) {
        validateSession(session, userService);
        if (userService.findByUsername(username) != null) {
            userService.deleteByUsername(username);
            return new ResponseEntity<>("User with username: " + username + "removed", HttpStatus.OK);
        }
        throw new UserNotFoundException("User with username " + username + " not found.");
    }
}
