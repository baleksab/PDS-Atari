package baleksab.pdsatari.bean;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class AddToCartBean {

    @NotNull
    @Min(value = 1, message = "Invalid game id")
    private int gameId;

    @NotNull
    @Min(value = 1, message = "Invalid user id")
    private int userId;

}
