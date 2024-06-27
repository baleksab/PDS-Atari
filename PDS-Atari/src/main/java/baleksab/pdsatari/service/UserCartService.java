package baleksab.pdsatari.service;

import baleksab.pdsatari.bean.AddToCartBean;
import baleksab.pdsatari.bean.RemoveFromCartBean;
import baleksab.pdsatari.entity.Game;
import baleksab.pdsatari.entity.User;
import baleksab.pdsatari.entity.UserCart;
import baleksab.pdsatari.exception.ValidationException;
import baleksab.pdsatari.repository.GameRepository;
import baleksab.pdsatari.repository.UserCartRepository;
import baleksab.pdsatari.repository.UserRepository;
import baleksab.pdsatari.util.BeanValidator;
import com.google.gson.Gson;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Map;

public class UserCartService {

    @Inject
    private UserCartRepository userCartRepository;

    @Inject
    private UserService userService;

    @Inject
    private GameService gameService;

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
            return false;
        }

        return true;
    }

}
