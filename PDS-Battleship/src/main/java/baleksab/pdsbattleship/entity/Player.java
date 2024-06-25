package baleksab.pdsbattleship.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "Username must not be blank!")
    @Size(min = 3, max = 15, message = "Username must be between 3 and 15 characters long!")
    @Column(unique = true)
    private String username;

    @NotBlank(message = "Password must not be blank!")
    private String password;

    @NotNull
    private boolean isAdmin;

}
