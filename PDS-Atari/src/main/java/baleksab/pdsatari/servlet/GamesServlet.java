package baleksab.pdsatari.servlet;

import baleksab.pdsatari.bean.GameBean;
import baleksab.pdsatari.bean.PaginationBean;
import baleksab.pdsatari.service.GameService;
import com.google.gson.Gson;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.beanutils.BeanUtils;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

@WebServlet(name = "GamesServlet", value = "/games")
public class GamesServlet extends HttpServlet {

    @Inject
    private GameService gameService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PaginationBean paginationBean = new PaginationBean();

        try {
            BeanUtils.populate(paginationBean, req.getParameterMap());
        } catch (InvocationTargetException | IllegalAccessException e) {
            System.out.println(e.getMessage());
        }

        List<GameBean> games = gameService.getAllGamesWithPagination(paginationBean);

        String json = new Gson().toJson(games);

        resp.setContentType("application/json");
        resp.getWriter().write(json);
    }

}
