package baleksab.pdsatari.bean;

import baleksab.pdsatari.entity.UserCart;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class GameBean {

    private int id;

    private String name;

    private String path;

    @NotNull(message = "Price must not be null!")
    @DecimalMin(value = "0.0", message = "Price must not be lower than 0.0!")
    private float price;

    @NotBlank(message = "Game must have a description!")
    private String description;

    private float rating;

    @NotNull(message = "Stock can not be null!")
    @Min(value = 0, message = "Minimum stock is 0!")
    private int stock;

    private List<Integer> customerCarts;

    private List<Integer> customerInventories;

}
