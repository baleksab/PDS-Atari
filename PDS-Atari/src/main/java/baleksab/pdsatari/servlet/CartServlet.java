package baleksab.pdsatari.servlet;

import baleksab.pdsatari.bean.CartGamesBean;
import baleksab.pdsatari.bean.GameBean;
import baleksab.pdsatari.bean.UserBean;
import baleksab.pdsatari.entity.User;
import baleksab.pdsatari.service.UserCartService;
import baleksab.pdsatari.service.UserService;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "CartServlet", value = "/cart")
public class CartServlet extends HttpServlet {

    @Inject
    private UserCartService userCartService;

    @Inject
    private UserService userService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CartGamesBean cartGamesBean = new CartGamesBean();

        try {
            BeanUtils.populate(cartGamesBean, req.getParameterMap());
        } catch (IllegalAccessException | InvocationTargetException e) {
            System.out.println(e.getMessage());
        }

        List<GameBean> games = userCartService.getAllGamesInCart(cartGamesBean);

        String json = new Gson().toJson(games);

        resp.setContentType("application/json");
        resp.getWriter().write(json);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CartGamesBean cartGamesBean = new CartGamesBean();

        try {
            BeanUtils.populate(cartGamesBean, req.getParameterMap());
        } catch (IllegalAccessException | InvocationTargetException e) {
            System.out.println(e.getMessage());
        }

        boolean success = userCartService.purchaseAllGamesInCart(cartGamesBean);

        if (success) {
            User user = userService.getByUserId(cartGamesBean.getUserId());
            UserBean userBean = new UserBean();

            try {
                BeanUtils.copyProperties(userBean, user);
            } catch (IllegalAccessException | InvocationTargetException e) {
                System.out.println(e.getMessage());
            }

            req.getSession().setAttribute("userBean", userBean);
        }

        Map<String, Object> response = new HashMap<>();
        response.put("success", success);

        String json = new Gson().toJson(response);

        resp.setContentType("application/json");
        resp.getWriter().write(json);
    }
}
