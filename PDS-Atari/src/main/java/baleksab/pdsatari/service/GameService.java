package baleksab.pdsatari.service;

import baleksab.pdsatari.bean.GameBean;
import baleksab.pdsatari.bean.PaginationBean;
import baleksab.pdsatari.bean.UserBean;
import baleksab.pdsatari.entity.Game;
import baleksab.pdsatari.entity.User;
import baleksab.pdsatari.repository.GameRepository;
import jakarta.inject.Inject;
import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class GameService {

    @Inject
    private GameRepository gameRepository;

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

    private List<GameBean> getGameBeans(List<Game> games) {
        List<GameBean> gameBeans = new ArrayList<>();

        for (Game game : games) {
            GameBean gameBean = new GameBean();

            try {
                BeanUtils.copyProperties(gameBean, game);
            } catch (IllegalAccessException | InvocationTargetException e) {
                System.out.println(e.getMessage());
            }

            gameBeans.add(gameBean);
        }

        return gameBeans;
    }

}
