package baleksab.pdsatari.bean;

import baleksab.pdsatari.entity.UserCart;
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

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class GameBean {

    private int id;

    private String name;

    private String path;

    private float price;

    private String description;

    private float rating;

    private int stock;

    private List<Integer> customerCarts;

    private List<Integer> customerInventories;

}
