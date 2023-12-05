package car.rental.service.com.example.car.rental.service.services.implementations;


import car.rental.service.com.example.car.rental.service.entities.Car;
import car.rental.service.com.example.car.rental.service.entities.Rental;
import car.rental.service.com.example.car.rental.service.entities.User;
import car.rental.service.com.example.car.rental.service.exceptions.CarNotFoundException;
import car.rental.service.com.example.car.rental.service.exceptions.RentalNotFoundException;
import car.rental.service.com.example.car.rental.service.exceptions.UserNotFoundException;
import car.rental.service.com.example.car.rental.service.repositories.RentalRepository;
import car.rental.service.com.example.car.rental.service.services.interfaces.CarService;
import car.rental.service.com.example.car.rental.service.services.interfaces.RentalService;
import car.rental.service.com.example.car.rental.service.services.interfaces.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static car.rental.service.com.example.car.rental.service.Constants.CAR_NOT_FOUND;
import static car.rental.service.com.example.car.rental.service.Constants.USER_WITH_GIVEN_EMAIL_NOT_FOUND;

@Service
@Transactional
public class RentalServiceImplementation implements RentalService {

    @Autowired
    private RentalRepository rentalRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private CarService carService;

    @Override
    public Rental createRental(LocalDate endDate, String model, String email) {
        User user = userService.findByEmail(email);
        Car car = carService.findByModel(model);

        if (user == null) {
            throw new UserNotFoundException(USER_WITH_GIVEN_EMAIL_NOT_FOUND);
        }
        if (car == null) {
            throw new CarNotFoundException(CAR_NOT_FOUND);
        }

        LocalDate startDate = LocalDate.now();
        Rental rental = new Rental(startDate, endDate, car, user);
        rentalRepository.save(rental);
        return rental;
    }

    @Override
    public Optional<Rental> findById(Long id) {
        return rentalRepository.findById(id);
    }

    @Override
    public List<Rental> getAll() {
        return rentalRepository.findAll();
    }

    @Override
    public List<Rental> getAllByUser(User user) {
        userService.findByUsername(String.valueOf(user));
        if (user != null) {
            return rentalRepository.getAllByUser(user);
        }
        throw new UserNotFoundException(USER_WITH_GIVEN_EMAIL_NOT_FOUND);
    }

    @Override
    public void deleteById(Long id) {
        rentalRepository.deleteById(id);
    }


    @Override
    public void updateEndDate(Long rentalId, LocalDate newEndDate) {
        Optional<Rental> optionalRental = rentalRepository.findById(rentalId);

        if (optionalRental.isPresent()) {
            Rental rentalToUpdate = optionalRental.get();
            rentalToUpdate.setEndDate(newEndDate);
            rentalRepository.save(rentalToUpdate);
        } else {
            throw new RentalNotFoundException("Rental with ID " + rentalId + " not found");
        }
    }
}
