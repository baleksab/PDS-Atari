package baleksab.pdsbattleship.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "player1_id", referencedColumnName = "id")
    private Player player1;

    @ManyToOne
    @JoinColumn(name = "player2_id", referencedColumnName = "id")
    private Player player2;

    private int shipsSunkByPlayer1;
    private int shipsSunkByPlayer2;

    @ManyToOne
    @JoinColumn(name = "winner_player_id", referencedColumnName = "id")
    private Player winnerPlayer;

}
