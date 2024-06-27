package baleksab.pdsatari.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "Name must not be blank!")
    private String name;

    @NotBlank(message = "Image path must not be blank!")
    private String path;

    @NotNull(message = "Price must not be null!")
    @DecimalMin(value = "0.0", message = "Price must not be lower than 0.0!")
    private float price;

    @NotBlank(message = "Game must have a description!")
    private String description;

    @NotNull(message = "Rating must not be null!")
    @DecimalMin(value = "0.0", message = "Rating must not be lower than 0.0!")
    @DecimalMax(value = "5.0", message = "Rating must not be greater than 5.0!")
    private float rating;

    @NotNull(message = "Stock can not be null!")
    @Min(value = 0, message = "Minimum stock is 0!")
    private int stock;

}
