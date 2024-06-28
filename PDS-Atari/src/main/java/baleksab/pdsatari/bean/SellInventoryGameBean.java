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
public class SellInventoryGameBean {

    @NotNull
    @Min(value = 1, message = "Invalid game id")
    private int gameId;

    @NotNull
    @Min(value = 1, message = "Invalid user id")
    private int userId;

}
