package baleksab.pdsbattleship.service;

import baleksab.pdsbattleship.bean.LoginBean;
import baleksab.pdsbattleship.bean.PlayerBean;
import baleksab.pdsbattleship.bean.RegisterBean;
import baleksab.pdsbattleship.entity.Player;
import baleksab.pdsbattleship.exception.ValidationException;
import baleksab.pdsbattleship.repository.PlayerRepository;
import baleksab.pdsbattleship.util.BeanValidator;
import baleksab.pdsbattleship.util.PasswordEncoder;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Named
public class PlayerService {

    @Inject
    private PlayerRepository playerRepository;

    @Inject
    private BeanValidator beanValidator;

    @Inject
    private PasswordEncoder passwordEncoder;

    public void addPlayer(Player player) {
        playerRepository.add(player);
    }

    public PlayerBean loginPlayer(LoginBean loginBean) throws InvocationTargetException, IllegalAccessException {
        Set<String> violations = beanValidator.validate(loginBean);

        if (!violations.isEmpty()) {
            throw new ValidationException("Failed logging player, validation violation!", violations);
        }

        Player player = getPlayerByUsername(loginBean.getUsername());

        if (player == null) {
            violations.add("Player with given username does not exist!");
            throw new ValidationException("Failed logging player, player with given username doesn't exist!", violations);
        }

        if (!passwordEncoder.checkPassword(loginBean.getPassword(), player.getPassword())) {
            violations.add("Wrong password!");
            throw new ValidationException("Failed logging player, wrong password!", violations);
        }

        PlayerBean playerBean = new PlayerBean();
        BeanUtils.copyProperties(playerBean, player);

        return playerBean;
    }

    public void registerPlayer(RegisterBean registerBean) throws InvocationTargetException, IllegalAccessException {
        Set<String> violations = beanValidator.validate(registerBean);

        if (!violations.isEmpty()) {
            throw new ValidationException("Failed registering player, validation violation!", violations);
        }

        if (getPlayerByUsername(registerBean.getUsername()) != null) {
            violations.add("Username is already taken!");
            throw new ValidationException("Failed registering player, username already taken!", violations);
        }

        if (!registerBean.getPassword().equals(registerBean.getConfirmPassword())) {
            violations.add("Passwords must match!");
            throw new ValidationException("Failed registering player, passwords must match!", violations);
        }

        Player player = new Player();
        BeanUtils.copyProperties(player, registerBean);

        player.setPassword(passwordEncoder.hashPassword(player.getPassword()));

        addPlayer(player);
    }

    public Player getPlayerById(int id) {
        return playerRepository.getById(id);
    }

    public Player getPlayerByUsername(String username) {
        return playerRepository.getByUsername(username);
    }

    public List<PlayerBean> getAllPlayers() {
        List<Player> players = playerRepository.getAll();
        List<PlayerBean> playerBeans = new ArrayList<>();

        for (Player player : players) {
            PlayerBean playerBean = new PlayerBean();

            try {
                BeanUtils.copyProperties(playerBean, player);
            } catch (IllegalAccessException | InvocationTargetException e) {
                System.out.println(e.getMessage());
            }

            playerBeans.add(playerBean);
        }

        return playerBeans;
    }

    public void updatePlayer(Player player) {
        playerRepository.update(player);
    }

    public void deletePlayer(Player player) {
        playerRepository.delete(player);
    }

}
