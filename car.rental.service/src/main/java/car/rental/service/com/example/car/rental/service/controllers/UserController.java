package car.rental.service.com.example.car.rental.service.controllers;


import car.rental.service.com.example.car.rental.service.entities.User;
import car.rental.service.com.example.car.rental.service.services.interfaces.UserService;
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

    @GetMapping("/byEmail")
    public ResponseEntity<User> getUserByEmail(@RequestParam String email, HttpSession session) {
        validateSession(session, userService);

        User user = userService.findByEmail(email);
        return user != null ? ResponseEntity.ok(user) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping("/byUsername")
    public ResponseEntity<User> getUserByUsername(@RequestParam String username, HttpSession session) {
        validateSession(session, userService);

        User user = userService.findByUsername(username);
        return user != null ? ResponseEntity.ok(user) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers(HttpSession session) {
        validateSession(session, userService);
        List<User> users = userService.findAll();
        return ResponseEntity.ok(users);
    }

    @DeleteMapping("/byEmail")
    public ResponseEntity<String> deleteByEmail(@RequestParam String email, HttpSession session) {
        validateSession(session, userService);
        userService.deleteByEmail(email);
        return ResponseEntity.ok("User with email: " + email + " removed");


    }

    @DeleteMapping("/byUsername")
    public ResponseEntity<String> deleteByUsername(@RequestParam String username, HttpSession session) {
        validateSession(session, userService);
        userService.deleteByUsername(username);
        return ResponseEntity.ok("User with username: " + username + " removed");

    }
}
