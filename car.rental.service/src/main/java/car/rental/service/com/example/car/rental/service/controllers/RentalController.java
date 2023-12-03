package car.rental.service.com.example.car.rental.service.controllers;

import car.rental.service.com.example.car.rental.service.entities.Rental;
import car.rental.service.com.example.car.rental.service.entities.User;
import car.rental.service.com.example.car.rental.service.exceptions.CarNotFoundException;
import car.rental.service.com.example.car.rental.service.exceptions.UserNotFoundException;
import car.rental.service.com.example.car.rental.service.services.interfaces.CarService;
import car.rental.service.com.example.car.rental.service.services.interfaces.RentalService;
import car.rental.service.com.example.car.rental.service.services.interfaces.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

import static car.rental.service.com.example.car.rental.service.utils.SessionUtils.validateSession;

@RestController
@RequestMapping("/rental")
@Tag(name = "Rentals", description = "Rentals operations")
public class RentalController {

    @Autowired
    private UserService userService;
    @Autowired
    private CarService carService;
    @Autowired
    private RentalService rentalService;

    @PostMapping("/create")
    public ResponseEntity<String> createRental(@RequestParam LocalDate endDate, @RequestParam String model, @RequestParam String email, HttpSession session) {

        validateSession(session, userService);

        try {
            Rental rental = rentalService.createRental(endDate, model, email);
            return ResponseEntity.ok("Rental created: " + rental.toString());
        } catch (ValidationException | CarNotFoundException | UserNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/byId")
    public ResponseEntity<Rental> getRental(@RequestParam Long id, HttpSession session) {
        validateSession(session, userService);
        return rentalService.findById(id).map(rental -> ResponseEntity.ok(rental)).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping("/allByUser")
    public ResponseEntity<List<Rental>> getAllByUser(@RequestParam User user, HttpSession session) {
        validateSession(session, userService);
        List<Rental> rentals = rentalService.getAllByUser(user);
        return rentals.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(rentals);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Rental>> getAllRentals(HttpSession session) {
        validateSession(session, userService);
        List<Rental> rentals = rentalService.getAll();
        return ResponseEntity.ok(rentals);
    }

    @DeleteMapping("/byId")
    public ResponseEntity<String> deleteRentalById(@RequestParam Long id, HttpSession session) {
        validateSession(session, userService);
        rentalService.deleteById(id);
        return ResponseEntity.ok("Rental with id " + id + " removed");
    }

    @PutMapping("/updateEndDate")
    public ResponseEntity<String> updateRentalEndDate(@RequestParam Long id, @RequestParam LocalDate newEndDate, HttpSession session) {

        validateSession(session, userService);
        rentalService.updateEndDate(id, newEndDate);
        return ResponseEntity.ok("Rental end date updated for id: " + id);
    }
}
