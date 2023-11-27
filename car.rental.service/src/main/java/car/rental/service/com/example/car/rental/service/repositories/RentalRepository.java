package car.rental.service.com.example.car.rental.service.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import car.rental.service.com.example.car.rental.service.entities.User;
import car.rental.service.com.example.car.rental.service.entities.Rental;
import java.util.List;
import java.util.Optional;

public interface RentalRepository extends JpaRepository <Rental, Long> {
    Optional <Rental> findById(Long id);

    Rental findByUser(User user);

    List <Rental> getAllByUser(User user);

    void deleteById(Long id);
}
