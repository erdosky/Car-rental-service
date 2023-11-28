<<<<<<< 85f5bce44117e8fb270e0b8d61ab2e13a402ff28
package car.rental.service.com.example.car.rental.service.repositories;

import car.rental.service.com.example.car.rental.service.entities.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarRepository extends JpaRepository <Car, Long> {

    Car findByModel(String model);
    List <Car> findAllByType(String type);

    List <Car> findAllByBrand(String brand);

    void deleteByModel(String model);
=======
package car.rental.service.com.example.car.rental.service.repositories;public interface CarRepository {
>>>>>>> 8003c62b44c41e20ba868e1fa8c0189712115487
}
