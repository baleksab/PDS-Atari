package baleksab.pdsatari.bean;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SendChatMessageBean {

    @NotBlank(message = "Message must not be blank!")
    @Size(max = 255, message = "Message must not be greater than 255 characters!")
    private String message;

    @NotNull(message = "Message date must not be null!")
    private Date date;

    @NotNull(message = "Sender id must not be null!")
    private int senderId;

}
