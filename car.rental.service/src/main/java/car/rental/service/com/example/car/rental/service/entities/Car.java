package car.rental.service.com.example.car.rental.service.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "books")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
<<<<<<< 85f5bce44117e8fb270e0b8d61ab2e13a402ff28
    private String brand;
    private String model;
    @Column(name="year_of_production")
    private int year;
    private String status;
    private String type;
=======
    private String title;
    private String author;
>>>>>>> 8003c62b44c41e20ba868e1fa8c0189712115487

    private String publisher;

    public Book(String title, String author,  String publisher) {
        this.title = title;
        this.author = author;
        this.publisher = publisher;
    }
}