package car.rental.service.com.example.car.rental.service.services.interfaces;

import org.springframework.stereotype.Service;
import car.rental.service.com.example.car.rental.service.entities.User;
import java.util.List;

@Service
public interface UserService {
    User findByEmail(String email);

    User findByUsername(String username);

    List <User> findAll();

    void deleteByUsername(String username);

    void deleteByEmail(String email);

    void save(User user);

    boolean exist(String username);
}
