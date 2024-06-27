package baleksab.pdsatari.service;

import baleksab.pdsatari.bean.AddToCartBean;
import baleksab.pdsatari.bean.CartGamesBean;
import baleksab.pdsatari.bean.GameBean;
import baleksab.pdsatari.bean.RemoveFromCartBean;
import baleksab.pdsatari.entity.Game;
import baleksab.pdsatari.entity.User;
import baleksab.pdsatari.entity.UserCart;
import baleksab.pdsatari.entity.UserInventory;
import baleksab.pdsatari.exception.ValidationException;
import baleksab.pdsatari.repository.GameRepository;
import baleksab.pdsatari.repository.UserCartRepository;
import baleksab.pdsatari.repository.UserInventoryRepository;
import baleksab.pdsatari.repository.UserRepository;
import baleksab.pdsatari.util.BeanValidator;
import com.google.gson.Gson;
import jakarta.inject.Inject;
import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class UserCartService {

    @Inject
    private UserCartRepository userCartRepository;

    @Inject
    private UserService userService;

    @Inject
    private GameService gameService;

    @Inject
    private UserInventoryRepository userInventoryRepository;

    @Inject
    private BeanValidator beanValidator;

    public boolean addToUserCart(AddToCartBean bean) {
        try {
            beanValidator.validate(bean);

            User user = userService.getByUserId(bean.getUserId());
            Game game = gameService.getByGameId(bean.getGameId());

            if (game.getStock() < 1) {
                return false;
            }

            UserCart userCart = new UserCart();
            userCart.setUser(user);
            userCart.setGame(game);

            userCartRepository.add(userCart);
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    public List<GameBean> getAllGamesInCart(CartGamesBean bean) {
        Set<String> violations = beanValidator.validate(bean);

        if (!violations.isEmpty()) {
            throw new ValidationException("User is invalid", null);
        }

        List<Game> games = userCartRepository.getAllGamesInCart(bean.getUserId());
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

    public boolean removeFromUserCart(RemoveFromCartBean bean) {
        try {
            beanValidator.validate(bean);

            User user = userService.getByUserId(bean.getUserId());
            Game game = gameService.getByGameId(bean.getGameId());

            UserCart userCart = new UserCart();
            userCart.setUser(user);
            userCart.setGame(game);

            userCartRepository.delete(userCart);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }

        return true;
    }

    public boolean purchaseAllGamesInCart(CartGamesBean bean) {
        try {
            beanValidator.validate(bean);

            User user = userService.getByUserId(bean.getUserId());
            List<Game> inventoryGames = userInventoryRepository.getAllInventoryGames(user.getId());
            List<Game> cartGames = userCartRepository.getAllGamesInCart(user.getId());

            for (Game cartGame : cartGames) {
                if (inventoryGames.stream().anyMatch(game -> game.getId() == cartGame.getId())) {
                    System.out.println("fail in inventory");
                    return false;
                }
            }

            float totalCost = 0;

            for (Game game : cartGames) {
                totalCost += game.getPrice();
            }

            if (totalCost > user.getBudget()) {
                System.out.println("fail no money");
                return false;
            }

            for (Game game : cartGames) {
                UserCart userCart = new UserCart();
                userCart.setUser(user);
                userCart.setGame(game);

                userCartRepository.delete(userCart);

                UserInventory userInventory = new UserInventory();
                userInventory.setUser(user);
                userInventory.setGame(game);

                userInventoryRepository.save(userInventory);
            }

            user.setBudget(user.getBudget() - totalCost);
            userService.updateUser(user);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return false;
        }

        return true;
    }

}
