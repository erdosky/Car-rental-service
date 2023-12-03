package car.rental.service.com.example.car.rental.service.repositories;

import car.rental.service.com.example.car.rental.service.entities.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarRepository extends JpaRepository<Car, Long> {

    Car findByModel(String model);

    List<Car> findAllByType(String type);

    List<Car> findAllByBrand(String brand);

    void deleteByModel(String model);
}
