package baleksab.pdsbattleship.bean;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class LoginBean {

    @NotBlank(message = "Username must not be blank!")
    private String username;

    @NotBlank(message = "Password must not be blank!")
    private String password;

}
