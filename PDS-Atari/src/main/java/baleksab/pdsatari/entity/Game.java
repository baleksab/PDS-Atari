package baleksab.pdsatari.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
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

}
