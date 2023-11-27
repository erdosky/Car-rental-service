package car.rental.service.com.example.car.rental.service.repositories;


import car.rental.service.com.example.car.rental.service.entities.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public interface UserRepository extends JpaRepository <User, Long> {
    User findByEmail(String email);

    User findByUsername(String username);

    List <User> findAllBy();

    void deleteByUsername(String username);

    void deleteByEmail(String email);
}
