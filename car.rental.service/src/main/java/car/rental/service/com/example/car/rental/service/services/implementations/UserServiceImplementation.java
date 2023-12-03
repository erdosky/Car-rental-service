

package car.rental.service.com.example.car.rental.service.services.implementations;


import car.rental.service.com.example.car.rental.service.entities.User;
import car.rental.service.com.example.car.rental.service.repositories.UserRepository;
import car.rental.service.com.example.car.rental.service.services.interfaces.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class UserServiceImplementation implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAllBy();
    }

    @Override
    public void deleteByUsername(String username) {
        if (userRepository.findByUsername(username) != null) {
            userRepository.deleteByUsername(username);
        }
    }

    @Override
    public void deleteByEmail(String email) {
        if (userRepository.findByEmail(email) != null) {
            userRepository.deleteByEmail(email);
        }

    }

    @Override
    public boolean exist(String username) {
        return userRepository.findByUsername(username) != null;
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }
}
