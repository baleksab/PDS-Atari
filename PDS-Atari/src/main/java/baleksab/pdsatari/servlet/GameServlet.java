package baleksab.pdsatari.servlet;

import baleksab.pdsatari.bean.GameBean;
import baleksab.pdsatari.service.GameService;
import com.google.gson.Gson;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "GameServlet", value = "/games")
public class GameServlet extends HttpServlet {

    @Inject
    private GameService gameService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<GameBean> games = gameService.getAllGames();
        String json = new Gson().toJson(games);
        resp.setContentType("application/json");
        resp.getWriter().write(json);
    }

}
