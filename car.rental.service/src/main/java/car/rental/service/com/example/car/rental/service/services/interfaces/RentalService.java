package car.rental.service.com.example.car.rental.service.services.interfaces;

import car.rental.service.com.example.car.rental.service.entities.Rental;
import car.rental.service.com.example.car.rental.service.entities.User;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public interface RentalService {
    Optional<Rental> findById(Long id);

    List<Rental> getAll();

    List<Rental> getAllByUser(User user);

    void deleteById(Long id);

    boolean exist(Long id);

    void save(Rental rental);

    void updateEndDate(Long rentalId, LocalDate newEndDate);

    Rental createRental(LocalDate endDate, String model, String email);
}
