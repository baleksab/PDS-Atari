package baleksab.pdsbattleship.bean;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PlayerBean {

    private int id;

    private String username;

    private boolean isAdmin;

}
