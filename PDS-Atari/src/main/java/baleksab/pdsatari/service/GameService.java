package baleksab.pdsatari.service;

import baleksab.pdsatari.bean.GameBean;
import baleksab.pdsatari.bean.PaginationBean;
import baleksab.pdsatari.bean.UserBean;
import baleksab.pdsatari.entity.Game;
import baleksab.pdsatari.entity.User;
import baleksab.pdsatari.entity.UserCart;
import baleksab.pdsatari.entity.UserInventory;
import baleksab.pdsatari.repository.GameRepository;
import baleksab.pdsatari.repository.UserCartRepository;
import baleksab.pdsatari.repository.UserInventoryRepository;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class GameService {

    @Inject
    private GameRepository gameRepository;

    @Inject
    private UserCartRepository userCartRepository;

    @Inject
    private UserInventoryRepository userInventoryRepository;

    public List<GameBean> getAllGames() {
        List<Game> games = gameRepository.getAll();
        return getGameBeans(games);
    }

    public List<GameBean> getAllGamesWithPagination(PaginationBean paginationBean) {
        int pageNumber = paginationBean.getPage();
        int pageSize = paginationBean.getSize();

        List<Game> games = gameRepository.getAllGamesPaginated(pageNumber, pageSize);

        return getGameBeans(games);
    }

    public Game getByGameId(int id) {
        return gameRepository.getById(id);
    }

    private List<GameBean> getGameBeans(List<Game> games) {
        List<GameBean> gameBeans = new ArrayList<>();

        for (Game game : games) {
            GameBean gameBean = new GameBean();

            try {
                BeanUtils.copyProperties(gameBean, game);
            } catch (IllegalAccessException | InvocationTargetException e) {
                System.out.println(e.getMessage());
            }

            List<Integer> userCarts = userCartRepository.getAllUsersWithGameInCart(gameBean.getId());
            gameBean.setCustomerCarts(userCarts);

            List<Integer> userInventories = userInventoryRepository.getAllUsersWithGameInInventory(gameBean.getId());
            gameBean.setCustomerInventories(userInventories);

            gameBeans.add(gameBean);
        }

        return gameBeans;
    }

}
