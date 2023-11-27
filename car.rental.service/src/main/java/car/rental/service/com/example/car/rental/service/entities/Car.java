package car.rental.service.com.example.car.rental.service.entities;


import car.rental.service.com.example.car.rental.service.enums.CarStatus;
import car.rental.service.com.example.car.rental.service.enums.CarType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "cars")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String brand;
    private String model;
    private int year;
    private CarStatus status;
    private CarType type;

    public Car(String brand, String model, CarStatus status, CarType type) {
        this.brand = brand;
        this.model = model;
        this.status = status;
        this.type = type;
    }
}