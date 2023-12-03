package car.rental.service.com.example.car.rental.service.unitTests;

import car.rental.service.com.example.car.rental.service.entities.Rental;
import car.rental.service.com.example.car.rental.service.entities.User;
import car.rental.service.com.example.car.rental.service.exceptions.RentalNotFoundException;
import car.rental.service.com.example.car.rental.service.repositories.RentalRepository;
import car.rental.service.com.example.car.rental.service.services.implementations.RentalServiceImplementation;
import car.rental.service.com.example.car.rental.service.services.interfaces.RentalService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class RentalTests {

    @Mock
    private RentalRepository rentalRepository;


    @InjectMocks
    private RentalService rentalService = new RentalServiceImplementation();

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindById() {
        Long id = 1L;
        Rental expectedRental = new Rental();
        when(rentalRepository.findById(id)).thenReturn(Optional.of(expectedRental));

        Optional<Rental> result = rentalService.findById(id);

        assertTrue(result.isPresent());
        assertEquals(expectedRental, result.get());
        verify(rentalRepository, times(1)).findById(id);
    }

    @Test
    public void testFindByIdNotFound() {
        Long id = 1L;
        when(rentalRepository.findById(id)).thenReturn(Optional.empty());

        Optional<Rental> result = rentalService.findById(id);

        assertFalse(result.isPresent());
        verify(rentalRepository, times(1)).findById(id);
    }

    @Test
    public void testGetAll() {
        List<Rental> expectedRentals = Arrays.asList(new Rental(), new Rental());
        when(rentalRepository.findAll()).thenReturn(expectedRentals);

        List<Rental> result = rentalService.getAll();

        assertEquals(expectedRentals, result);
        verify(rentalRepository, times(1)).findAll();
    }

    @Test
    public void testGetAllByUser() {
        User user = new User();
        List<Rental> expectedRentals = Arrays.asList(new Rental(), new Rental());
        when(rentalRepository.getAllByUser(user)).thenReturn(expectedRentals);

        List<Rental> result = rentalService.getAllByUser(user);

        assertEquals(expectedRentals, result);
        verify(rentalRepository, times(1)).getAllByUser(user);
    }

    @Test
    public void testDeleteById() {
        Long id = 1L;

        rentalService.deleteById(id);

        verify(rentalRepository, times(1)).deleteById(id);
    }

    @Test
    public void testExist() {
        Long id = 1L;
        when(rentalRepository.findById(id)).thenReturn(Optional.of(new Rental()));

        assertTrue(rentalService.exist(id));
        verify(rentalRepository, times(1)).findById(id);
    }

    @Test
    public void testNotExist() {
        Long id = 1L;
        when(rentalRepository.findById(id)).thenReturn(Optional.empty());

        assertFalse(rentalService.exist(id));
        verify(rentalRepository, times(1)).findById(id);
    }

    @Test
    public void testSave() {
        Rental rental = new Rental();

        rentalService.save(rental);

        verify(rentalRepository, times(1)).save(rental);
    }

    @Test
    public void testUpdateEndDate() {
        Long rentalId = 1L;
        LocalDate newEndDate = LocalDate.now().plusDays(7);
        Rental existingRental = new Rental();
        existingRental.setId(rentalId);

        when(rentalRepository.findById(rentalId)).thenReturn(Optional.of(existingRental));

        assertDoesNotThrow(() -> rentalService.updateEndDate(rentalId, newEndDate));
        assertEquals(newEndDate, existingRental.getEndDate());
        verify(rentalRepository, times(1)).save(existingRental);
    }

    @Test
    public void testUpdateEndDateThrowsNotFoundException() {
        Long rentalId = 1L;
        LocalDate newEndDate = LocalDate.now().plusDays(7);

        when(rentalRepository.findById(rentalId)).thenReturn(Optional.empty());

        assertThrows(RentalNotFoundException.class, () -> rentalService.updateEndDate(rentalId, newEndDate));
        verify(rentalRepository, never()).save(any());
    }
}
