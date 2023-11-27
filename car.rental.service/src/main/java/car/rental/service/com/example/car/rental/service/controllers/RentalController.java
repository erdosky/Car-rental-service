package car.rental.service.com.example.car.rental.service.controllers;


import car.rental.service.com.example.car.rental.service.entities.Car;
import car.rental.service.com.example.car.rental.service.entities.Rental;
import car.rental.service.com.example.car.rental.service.entities.User;
import car.rental.service.com.example.car.rental.service.exceptions.CarNotFoundException;
import car.rental.service.com.example.car.rental.service.exceptions.UserNotFoundException;
import car.rental.service.com.example.car.rental.service.exceptions.RentalNotFoundException;
import car.rental.service.com.example.car.rental.service.services.interfaces.CarService;
import car.rental.service.com.example.car.rental.service.services.interfaces.RentalService;
import car.rental.service.com.example.car.rental.service.services.interfaces.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static car.rental.service.com.example.car.rental.service.Constants.*;
import static car.rental.service.com.example.car.rental.service.utils.RentalUtils.validateRentalRequest;
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

    @Operation(summary = "new rental")
    @PostMapping("/create")
    public ResponseEntity<String> createRental(
            @RequestParam LocalDate endDate,
            @RequestParam String model,
            @RequestParam String email,
            HttpSession session) {
        validateSession(session, userService);
        try {
            validateRentalRequest(endDate, model, email);

            User user = userService.findByEmail(email);
            Car car = carService.findByModel(model);
            LocalDate startDate = LocalDate.now();

            if (user != null) {
                if (car != null) {
                    Rental rental = new Rental(startDate, endDate, car, user);
                    rentalService.save(rental);
                    return new ResponseEntity <>("Rental created", HttpStatus.CREATED);
                }
                throw new CarNotFoundException(CAR_NOT_FOUND);
            }
            throw new UserNotFoundException(USER_WITH_GIVEN_EMAIL_NOT_FOUND);

        } catch (ValidationException ve) {
            return new ResponseEntity <>(ve.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "rental with given id")
    @GetMapping("/byId")
    public ResponseEntity <Rental> getRental(@RequestParam Long id, HttpSession session) {
        validateSession(session, userService);
        Optional<Rental> rental = rentalService.findById(id);

        return rental.map(value -> new ResponseEntity <>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity <>(null, HttpStatus.NOT_FOUND));
    }

    @Operation(summary = "all users rentals")
    @GetMapping("/allByUser")
    public ResponseEntity <List<Rental>> getAllByUser(@RequestParam String username, HttpSession session) {
        validateSession(session, userService);
        User user = userService.findByUsername(username);
        List <Rental> rentals = rentalService.getAllByUser(user);
        return new ResponseEntity <>(rentals, HttpStatus.OK);
    }

    @Operation(summary = "all rentals")
    @GetMapping("/all")
    public List <Rental> getAll(HttpSession session) {
        validateSession(session, userService);
        return rentalService.getAll();
    }

    @Operation(summary = "delete rental by given id")
    @DeleteMapping("/byId")
    public ResponseEntity <String> deleteById(@RequestParam Long id, HttpSession session) {
        validateSession(session, userService);
        if (rentalService.exist(id)) {
            rentalService.deleteById(id);
            return new ResponseEntity <>("Rental with id: " + id + "removed", HttpStatus.OK);
        }
        throw new RentalNotFoundException(RENTAL_NOT_FOUND);
    }

    @Operation(summary = "update rental end date")
    @PutMapping("/updateEndDate")
    public ResponseEntity <String> updateEndDate(@RequestParam Long id, @RequestParam LocalDate newEndDate, HttpSession session) {
        validateSession(session, userService);
        if (rentalService.exist(id)) {
            rentalService.updateEndDate(id, newEndDate);
            return new ResponseEntity <>("Rental with id: " + id + " updated", HttpStatus.OK);
        } else {
            throw new RentalNotFoundException(RENTAL_NOT_FOUND);
        }
    }
}
