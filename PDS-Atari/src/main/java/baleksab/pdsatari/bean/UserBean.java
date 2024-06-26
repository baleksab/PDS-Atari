package baleksab.pdsatari.bean;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserBean {

    private int id;

    private String email;

    private String firstName;

    private String lastName;

    private boolean isAdmin;

}
