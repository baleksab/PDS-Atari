package baleksab.pdsbattleship.service;

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

    public void addUser(Player player) {
        playerRepository.add(player);
    }

    public void registerUser(RegisterBean registerBean) throws InvocationTargetException, IllegalAccessException {
        Set<String> violations = beanValidator.validate(registerBean);

        if (!violations.isEmpty()) {
            throw new ValidationException("Failed registering player, validation violation!", violations);
        }

        if (getUserByUsername(registerBean.getUsername()) != null) {
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

        playerRepository.add(player);
    }

    public Player getUserById(int id) {
        return playerRepository.getById(id);
    }

    public Player getUserByUsername(String username) {
        return playerRepository.getByUsername(username);
    }

    public List<Player> getAllUsers() {
        return playerRepository.getAll();
    }

    public void updateUser(Player player) {
        playerRepository.update(player);
    }

    public void deleteUser(Player player) {
        playerRepository.delete(player);
    }

}
