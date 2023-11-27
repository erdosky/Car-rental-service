package car.rental.service.com.example.car.rental.service.services.interfaces;


import org.springframework.stereotype.Service;
import car.rental.service.com.example.car.rental.service.entities.Car;
import car.rental.service.com.example.car.rental.service.enums.CarType;
import java.util.List;

public interface CarService {

    Car findByModel(String model);

    List <Car> findAllByType(CarType type);

    List <Car> findAllByBrand(String brand);

    void deleteByModel(String model);

    boolean exist(String model);

    void save(Car car);

    void updateModel(String oldModel, String newModel);

}
