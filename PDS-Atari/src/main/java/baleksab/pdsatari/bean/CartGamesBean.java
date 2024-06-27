package baleksab.pdsatari.bean;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CartGamesBean {

    @NotNull(message = "User id must not be null")
    @Min(value = 1, message = "User id must be valid")
    private int userId;

}
