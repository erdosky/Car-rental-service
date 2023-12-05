package car.rental.service.com.example.car.rental.service.repositories;

import car.rental.service.com.example.car.rental.service.entities.Rental;
import car.rental.service.com.example.car.rental.service.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RentalRepository extends JpaRepository<Rental, Long> {
    Optional<Rental> findById(Long id);

    List<Rental> getAllByUser(User user);

    void deleteById(Long id);
}
