package car.rental.service.com.example.car.rental.service.services.interfaces;

import org.springframework.stereotype.Service;
import car.rental.service.com.example.car.rental.service.entities.User;
import car.rental.service.com.example.car.rental.service.entities.Rental;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public interface RentalService {
    Optional <Rental> findById(Long id);

    List <Rental> getAll();

    List <Rental> getAllByUser(User user);

    void deleteById(Long id);

    boolean exist(Long id);

    void save(Rental rental);

    void updateEndDate(Long rentalId, LocalDate newEndDate);
}
