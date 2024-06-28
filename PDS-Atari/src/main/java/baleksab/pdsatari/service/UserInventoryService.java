package baleksab.pdsatari.service;

import baleksab.pdsatari.bean.CartGamesBean;
import baleksab.pdsatari.bean.GameBean;
import baleksab.pdsatari.bean.InventoryGamesBean;
import baleksab.pdsatari.bean.SellInventoryGameBean;
import baleksab.pdsatari.entity.Game;
import baleksab.pdsatari.entity.User;
import baleksab.pdsatari.entity.UserInventory;
import baleksab.pdsatari.exception.ValidationException;
import baleksab.pdsatari.repository.GameRepository;
import baleksab.pdsatari.repository.UserInventoryRepository;
import baleksab.pdsatari.util.BeanValidator;
import jakarta.inject.Inject;
import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class UserInventoryService {

    @Inject
    private UserInventoryRepository userInventoryRepository;

    @Inject
    private UserService userService;

    @Inject
    private BeanValidator beanValidator;

    @Inject
    private GameRepository gameRepository;

    public List<GameBean> getAllGamesInInventory(InventoryGamesBean bean) {
        Set<String> violations = beanValidator.validate(bean);

        if (!violations.isEmpty()) {
            throw new ValidationException("User is invalid", null);
        }

        List<Game> games = userInventoryRepository.getAllInventoryGames(bean.getUserId());
        List<GameBean> gameBeans = new ArrayList<>();

        for (Game game : games) {
            GameBean gb = new GameBean();

            try {
                BeanUtils.copyProperties(gb, game);
            } catch (InvocationTargetException | IllegalAccessException e) {
                System.out.println(e.getMessage());
            }

            gameBeans.add(gb);
        }

        return gameBeans;
    }

    public boolean sellFromInventory(SellInventoryGameBean bean) {
        try {
            beanValidator.validate(bean);

            User user = userService.getByUserId(bean.getUserId());
            Game gameToSell = gameRepository.getById(bean.getGameId());
            List<Game> inventoryGames = userInventoryRepository.getAllInventoryGames(user.getId());

            if (inventoryGames.stream().noneMatch(game -> game.getId() == gameToSell.getId())) {
                System.out.println("fail not in inventory");
                return false;
            }

            float salePrice = (float) (gameToSell.getPrice() * 0.7);

            UserInventory userInventory = new UserInventory();
            userInventory.setUser(user);
            userInventory.setGame(gameToSell);
            userInventoryRepository.delete(userInventory);

            user.setBudget(user.getBudget() + salePrice);
            userService.updateUser(user);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }

        return true;

    }

}
