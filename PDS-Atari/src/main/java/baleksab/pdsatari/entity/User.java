package baleksab.pdsatari.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "Email must not be blank!")
    @Column(unique = true)
    @Email
    private String email;

    @NotBlank(message = "First name must not be blank!")
    private String firstName;

    @NotBlank(message = "Last name must not be blank!")
    private String lastName;

    @NotBlank(message = "Password must not be blank!")
    private String password;

    @NotNull(message = "Is admin must not be null")
    private boolean isAdmin;

    @NotNull(message = "Budget must not be null!")
    @DecimalMin(value = "0.0", message = "Budget must not be lower than 0.0!")
    private float budget;

}
