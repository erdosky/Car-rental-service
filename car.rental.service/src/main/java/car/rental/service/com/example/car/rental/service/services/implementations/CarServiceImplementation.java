

package car.rental.service.com.example.car.rental.service.services.implementations;

import car.rental.service.com.example.car.rental.service.entities.Car;
import car.rental.service.com.example.car.rental.service.enums.CarStatus;
import car.rental.service.com.example.car.rental.service.enums.CarType;
import car.rental.service.com.example.car.rental.service.exceptions.CarAlreadyExist;
import car.rental.service.com.example.car.rental.service.exceptions.CarNotFoundException;
import car.rental.service.com.example.car.rental.service.repositories.CarRepository;
import car.rental.service.com.example.car.rental.service.services.interfaces.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CarServiceImplementation implements CarService {

    @Autowired
    private CarRepository carRepository;

    @Override
    @Transactional
    public Car createCar(String brand, String model, String status, String type, String year) {
        if (exist(model)) {
            throw new CarAlreadyExist("Car already exists.");
        }

        CarType typeEnum = CarType.valueOf(type.toUpperCase());
        Car car = new Car(brand, model, year, CarStatus.valueOf(status.toUpperCase()), typeEnum);
        carRepository.save(car);
        return car;
    }

    @Override
    public Car findByModel(String model) {
        return carRepository.findByModel(model);
    }

    @Override
    public List<Car> findAllByType(CarType type) {
        return carRepository.findAllByType(String.valueOf(CarType.valueOf(type.getStringValue())));
    }

    @Override
    public List<Car> findAllByBrand(String brand) {
        return carRepository.findAllByBrand(brand);
    }

    @Override
    @Transactional
    public void deleteByModel(String model) {
        carRepository.deleteByModel(model);
    }

    @Override
    public boolean exist(String brand, String model, String year) {
        return false;
    }

    @Override
    public void save(Car car) {

    }

    @Override
    public boolean exist(String model) {
        return carRepository.findByModel(model) != null;
    }

    @Override
    @Transactional
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
