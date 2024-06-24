package baleksab.pdsbattleship.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Email(message = "User must have a valid email!")
    @NotBlank(message = "Email must not be blank!")
    @Column(unique = true)
    private String email;

    @NotBlank(message = "Username must not be blank!")
    @Size(min = 3, max = 15, message = "Username must be between 3 and 15 characters long!")
    @Column(unique = true)
    private String username;

    @NotBlank(message = "Password must not be blank!")
    @Size(min = 6, max = 32, message = "Password must be between 6 and 32 characters long!")
    private String password;

}
