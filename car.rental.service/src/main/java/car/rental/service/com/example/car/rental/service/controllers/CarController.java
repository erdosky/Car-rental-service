package car.rental.service.com.example.car.rental.service.controllers;

import car.rental.service.com.example.car.rental.service.entities.Car;
import car.rental.service.com.example.car.rental.service.enums.CarStatus;
import car.rental.service.com.example.car.rental.service.enums.CarType;
import car.rental.service.com.example.car.rental.service.exceptions.CarAlreadyExist;
import car.rental.service.com.example.car.rental.service.exceptions.CarNotFoundException;
import car.rental.service.com.example.car.rental.service.exceptions.WrongEnumValueException;
import car.rental.service.com.example.car.rental.service.services.interfaces.CarService;
import car.rental.service.com.example.car.rental.service.services.interfaces.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static car.rental.service.com.example.car.rental.service.Constants.*;
import static car.rental.service.com.example.car.rental.service.utils.CarUtils.validType;
import static car.rental.service.com.example.car.rental.service.utils.SessionUtils.validateSession;

@RestController
@RequestMapping("/car")
@Tag(name = "Cars", description = "Cars operations")
public class CarController {
    @Autowired
    private CarService carService;

    @Autowired
    private UserService userService;

    @Operation(summary = "new car")
    @PostMapping("/create")
    public ResponseEntity<String> createCar(
            @RequestParam String brand,
            @RequestParam String model,
            @Parameter(description = "Status. Values to choose : Available, Unavailable, Rented ")
            @RequestParam String status,
            @Parameter(description = "Type. Values to choose : Sedan, SUV, Coupe, Convertible, Hatchback, Station Wagon, Pickup Truck, Sports Car, Minivan, Electric")
            @RequestParam String type,
            @RequestParam String year,
            HttpSession session
    ) {
        validateSession(session, userService);
        try {
            CarType typeEnum = CarType.valueOf(type.toUpperCase());
            CarStatus statusEnum = CarStatus.valueOf(status.toUpperCase());

            if (carService.exist(brand, model, year)) {
                throw new CarAlreadyExist(CAR_ALREADY_EXIST);
            }
            Car car = new Car(brand, model, statusEnum.getStringValue(), typeEnum.getStringValue(), year);
            carService.save(car);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>("wrong status or type.", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Car created", HttpStatus.CREATED);
    }


    @Operation(summary = "Get all cars by brand")
    @GetMapping("/allByBrand")
    public ResponseEntity <List<Car>> getAllByBrand(
            @RequestParam String brand, HttpSession session) {
        validateSession(session, userService);
        List <Car> cars = carService.findAllByBrand(brand);

        if (cars.isEmpty()) {
            return new ResponseEntity <>(cars, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity <>(cars, HttpStatus.OK);
    }

    @Operation(summary = "Get all cars by type")
    @GetMapping("/allByType")
    public ResponseEntity <List <Car>> getAllByType(
            @RequestParam String type, HttpSession session) {
        validateSession(session, userService);
        if (validType(type)) {
            CarType typeEnum = CarType.valueOf(type.toUpperCase());
            List <Car> cars = carService.findAllByType(typeEnum);
            if (cars.isEmpty()) {
                return new ResponseEntity <>(cars, HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity <>(cars, HttpStatus.OK);
        }
        throw new WrongEnumValueException(TYPE_ENUM_MESSAGE);
    }

    @Operation(summary = "delete car by model")
    @DeleteMapping("/byModel")
    public ResponseEntity<String> deleteByModel(
            @RequestParam String model, HttpSession session) {
        validateSession(session, userService);
        if (carService.findByModel(model) != null) {
            carService.deleteByModel(model);
            return new ResponseEntity<>("Car model: " + model + "removed", HttpStatus.OK);
        }
        throw new CarNotFoundException("Car model " + model + " not found.");
    }

    @Operation(summary = "update car model")
    @PutMapping("/updateModel")
    public ResponseEntity<String> updateModel(
            @RequestParam String oldModel,
            @RequestParam String newModel,
            HttpSession session) {
        validateSession(session, userService);
        carService.updateModel(oldModel, newModel);
        return new ResponseEntity<>("Car model updated.", HttpStatus.OK);
    }

}
