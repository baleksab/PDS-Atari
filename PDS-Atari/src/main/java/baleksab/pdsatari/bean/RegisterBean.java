package baleksab.pdsatari.bean;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RegisterBean {

    @NotBlank(message = "Email must not be blank!")
    @Email
    private String email;

    @NotBlank(message = "First name must not be blank!")
    private String firstName;

    @NotBlank(message = "Last name must not be blank!")
    private String lastName;

    @NotBlank(message = "Password must not be blank!")
    @Size(min = 6, max = 32, message = "Password must be between 6 and 32 characters long!")
    private String password;

    @NotBlank(message = "Password must not be blank!")
    @Size(min = 6, max = 32, message = "Password must be between 6 and 32 characters long!")
    private String confirmPassword;

    @NotNull
    private boolean isAdmin;

    @NotNull(message = "Budget must not be null!")
    @DecimalMin(value = "10.0", message = "Minimum budget is 10 dollars!")
    private float budget;

}
