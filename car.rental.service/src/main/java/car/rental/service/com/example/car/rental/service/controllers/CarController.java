package car.rental.service.com.example.car.rental.service.controllers;

import car.rental.service.com.example.car.rental.service.entities.Car;
import car.rental.service.com.example.car.rental.service.enums.CarType;
import car.rental.service.com.example.car.rental.service.services.interfaces.CarService;
import car.rental.service.com.example.car.rental.service.services.interfaces.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static car.rental.service.com.example.car.rental.service.utils.SessionUtils.validateSession;

@RestController
@RequestMapping("/car")
@Tag(name = "Cars", description = "Cars operations")
public class CarController {

    @Autowired
    private CarService carService;

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public ResponseEntity<String> createCar(@RequestParam String brand, @RequestParam String model, @RequestParam String status, @RequestParam String type, @RequestParam String year, HttpSession session) {

        validateSession(session, userService);

        try {
            Car car = carService.createCar(brand, model, status, type, year);
            return ResponseEntity.ok("Car created: " + car.toString());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/allByBrand")
    public ResponseEntity<List<Car>> getAllByBrand(@RequestParam String brand, HttpSession session) {
        validateSession(session, userService);
        List<Car> cars = carService.findAllByBrand(brand);
        return ResponseEntity.ok(cars);
    }

    @GetMapping("/allByType")
    public ResponseEntity<List<Car>> getAllByType(@RequestParam String type, HttpSession session) {
        validateSession(session, userService);
        CarType typeEnum = CarType.valueOf(type.toUpperCase());
        List<Car> cars = carService.findAllByType(typeEnum);
        return ResponseEntity.ok(cars);
    }

    @DeleteMapping("/byModel")
    public ResponseEntity<String> deleteByModel(@RequestParam String model, HttpSession session) {
        validateSession(session, userService);
        carService.deleteByModel(model);
        return ResponseEntity.ok("Car model: " + model + " removed");
    }

    @PutMapping("/updateModel")
    public ResponseEntity<String> updateModel(@RequestParam String oldModel, @RequestParam String newModel, HttpSession session) {

        validateSession(session, userService);
        carService.updateModel(oldModel, newModel);
        return ResponseEntity.ok("Car model updated from " + oldModel + " to " + newModel);
    }
}
