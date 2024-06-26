package baleksab.pdsatari.bean;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
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
    @Email(message = "Email must not be blank!")
    private String email;

    @NotBlank(message = "Password must not be blank!")
    private String password;

}
