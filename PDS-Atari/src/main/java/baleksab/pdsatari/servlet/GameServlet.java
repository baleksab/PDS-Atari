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
import org.apache.commons.beanutils.BeanUtils;

import java.beans.JavaBean;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "GameServlet", value = "/game")
public class GameServlet extends HttpServlet {

    @Inject
    private GameService gameService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int gameId = Integer.parseInt(req.getParameter("gameId"));

        GameBean gameBean = gameService.getGameBeanById(gameId);

        String json = new Gson().toJson(gameBean);

        resp.setContentType("application/json");
        resp.getWriter().write(json);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        GameBean gameBean = new GameBean();

        try {
            BeanUtils.populate(gameBean, req.getParameterMap());
        } catch (InvocationTargetException | IllegalAccessException e) {
            System.out.println(e.getMessage());
        }

        boolean success = gameService.updateGame(gameBean);

        Map<String, Object> response = new HashMap<>();
        response.put("success", success);

        String json = new Gson().toJson(response);

        resp.setContentType("application/json");
        resp.getWriter().write(json);
    }

}
