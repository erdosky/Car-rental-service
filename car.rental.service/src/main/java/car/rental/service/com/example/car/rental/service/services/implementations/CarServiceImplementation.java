package car.rental.service.com.example.car.rental.service.services.implementations;

import car.rental.service.com.example.car.rental.service.entities.Car;
import car.rental.service.com.example.car.rental.service.enums.CarType;
import car.rental.service.com.example.car.rental.service.exceptions.CarNotFoundException;
import car.rental.service.com.example.car.rental.service.repositories.CarRepository;
import car.rental.service.com.example.car.rental.service.services.interfaces.CarService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class CarServiceImplementation implements CarService{

    @Autowired
    private CarRepository carRepository;


    @Override
    public Car findByModel(String model) {
        return carRepository.findByModel(model);
    }

    @Override
    public List<Car> findAllByType(CarType type) {
        return carRepository.findAllByType(type.getStringValue());
    }

    @Override
    public List<Car> findAllByBrand(String brand) {
        return carRepository.findAllByBrand(brand);
    }

    @Override
    public void deleteByModel(String model) {
        carRepository.deleteByModel(model);

    }

    @Override
    public boolean exist(String model) {
        return carRepository.findByModel(model) != null;
    }

    @Override
    public void save(Car car) {
        carRepository.save(car);

    }

    @Override
    public void updateModel(String oldModel, String newModel) {
        Car carToUpdate = carRepository.findByModel(oldModel);

        if (carToUpdate != null) {
            carToUpdate.setModel(newModel);
            carRepository.save(carToUpdate);
        } else {
            throw new CarNotFoundException("Car with model " + oldModel + " not found");
        }

    }
}
