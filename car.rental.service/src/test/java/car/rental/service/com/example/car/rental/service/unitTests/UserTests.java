package car.rental.service.com.example.car.rental.service.unitTests;

import car.rental.service.com.example.car.rental.service.entities.User;
import car.rental.service.com.example.car.rental.service.repositories.UserRepository;
import car.rental.service.com.example.car.rental.service.services.implementations.UserServiceImplementation;
import car.rental.service.com.example.car.rental.service.services.interfaces.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class UserTests {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService = new UserServiceImplementation();

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindByEmail() {
        String email = "test@example.com";
        User expectedUser = new User();
        when(userRepository.findByEmail(email)).thenReturn(expectedUser);

        User result = userService.findByEmail(email);

        assertEquals(expectedUser, result);
        verify(userRepository, times(1)).findByEmail(email);
    }

    @Test
    public void testFindByUsername() {
        String username = "tester";
        User expectedUser = new User();
        when(userRepository.findByUsername(username)).thenReturn(expectedUser);

        User result = userService.findByUsername(username);

        assertEquals(expectedUser, result);
        verify(userRepository, times(1)).findByUsername(username);
    }

    @Test
    public void testFindAll() {
        List<User> expectedUsers = Arrays.asList(new User(), new User());
        when(userRepository.findAllBy()).thenReturn(expectedUsers);

        List <User> result = userService.findAll();

        assertEquals(expectedUsers, result);
        verify(userRepository, times(1)).findAllBy();
    }

    @Test
    public void testDeleteByUsername() {
        String username = "tester";

        userService.deleteByUsername(username);

        verify(userRepository, times(1)).deleteByUsername(username);
    }

    @Test
    public void testDeleteByEmail() {
        String email = "test@example.com";

        userService.deleteByEmail(email);

        verify(userRepository, times(1)).deleteByEmail(email);
    }

    @Test
    public void testExist() {
        String username = "tester";
        when(userRepository.findByUsername(username)).thenReturn(new User());

        assertTrue(userService.exist(username));
        verify(userRepository, times(1)).findByUsername(username);
    }

    @Test
    public void testSave() {
        User user = new User();

        userService.save(user);

        verify(userRepository, times(1)).save(user);
    }
}
