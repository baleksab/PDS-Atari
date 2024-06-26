package baleksab.pdsatari.bean;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ChatMessageBean {

    private int id;

    private String message;

    private UserBean userBean;

    private Date date;

}
