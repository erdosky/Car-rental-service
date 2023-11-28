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
    @Column(name="year_of_production")
    private String year;
    private String status;
    private String type;


   public Car(String brand, String model, String year, CarStatus status, CarType type) {
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.status = status.getStringValue();
        this.type = type.getStringValue();
    }
}