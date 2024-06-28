package baleksab.pdsatari.bean;

import jakarta.persistence.Column;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class UserBean {

    private int id;

    @NotBlank(message = "Email must not be blank!")
    @Email
    private String email;

    @NotBlank(message = "First name must not be blank!")
    private String firstName;

    @NotBlank(message = "Last name must not be blank!")
    private String lastName;

    @NotNull(message = "I admin must not be null!")
    private boolean isAdmin;

    @NotNull(message = "Budget must not be null!")
    @DecimalMin(value = "0.0", message = "Budget must not be lower than 0.0!")
    private float budget;

}
