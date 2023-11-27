package car.rental.service.com.example.car.rental.service.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
public class Rental {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private LocalDate startDate;
    private LocalDate endDate;

    @ManyToOne
    @JoinColumn(name = "book_id")
    Car car;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Rental(LocalDate startDate, LocalDate endDate, Car car, User user) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.car = car;
        this.user = user;
    }
}