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
    private String title;
    private String author;

    private String publisher;

    public Book(String title, String author,  String publisher) {
        this.title = title;
        this.author = author;
        this.publisher = publisher;
    }
}