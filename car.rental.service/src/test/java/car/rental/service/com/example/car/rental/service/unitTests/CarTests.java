package car.rental.service.com.example.car.rental.service.unitTests;

import car.rental.service.com.example.car.rental.service.entities.Car;
import car.rental.service.com.example.car.rental.service.enums.CarType;
import car.rental.service.com.example.car.rental.service.exceptions.CarNotFoundException;
import car.rental.service.com.example.car.rental.service.repositories.CarRepository;
import car.rental.service.com.example.car.rental.service.services.implementations.CarServiceImplementation;
import car.rental.service.com.example.car.rental.service.services.interfaces.CarService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class CarTests {
    @Mock
    private CarRepository carRepository;

    @InjectMocks
    private CarService carService = new CarServiceImplementation();

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindByModel() {
        String model = "Test car";
        Car expectedCar = new Car();
        when(carRepository.findByModel(model)).thenReturn(expectedCar);

        Car result = carService.findByModel(model);

        assertEquals(expectedCar, result);
        verify(carRepository, times(1)).findByModel(model);
    }

    @Test
    public void testFindAllByType() {
        CarType type = CarType.SEDAN;
        List<Car> expectedCars = Arrays.asList(new Car(), new Car());
        when(carRepository.findAllByType(type.getStringValue())).thenReturn(expectedCars);

        List <Car> result = carService.findAllByType(type);

        assertEquals(expectedCars, result);
        verify(carRepository, times(1)).findAllByType(type.getStringValue());
    }

    @Test
    public void testFindAllByBrand() {
        String brand = "Test brand";
        List <Car> expectedCars = Arrays.asList(new Car(), new Car());
        when(carRepository.findAllByBrand(brand)).thenReturn(expectedCars);

        List <Car> result = carService.findAllByBrand(brand);

        assertEquals(expectedCars, result);
        verify(carRepository, times(1)).findAllByBrand(brand);
    }

    @Test
    public void testDeleteByModel() {
        String model = "Test model";

        carService.deleteByModel(model);

        verify(carRepository, times(1)).deleteByModel(model);
    }


    @Test
    public void testSave() {
        Car car = new Car();

        carService.save(car);

        verify(carRepository, times(1)).save(car);
    }

    @Test
    public void testUpdateModel() {
        String oldModel = "Old Model";
        String newModel = "New Model";
        Car existingCar = new Car();
        existingCar.setModel(oldModel);

        when(carRepository.findByModel(oldModel)).thenReturn(existingCar);

        assertDoesNotThrow(() -> carService.updateModel(oldModel, newModel));
        assertEquals(newModel, existingCar.getModel());
        verify(carRepository, times(1)).save(existingCar);
    }

    @Test
    public void testUpdateModelThrowsNotFoundException() {
        String oldModel = "Donotexist Model";
        String newModel = "New Model";

        when(carRepository.findByModel(oldModel)).thenReturn(null);

        assertThrows(CarNotFoundException.class, () -> carService.updateModel(oldModel, newModel));
        verify(carRepository, never()).save(any());
    }
}
