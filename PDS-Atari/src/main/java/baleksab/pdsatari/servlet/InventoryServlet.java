package baleksab.pdsatari.servlet;

import baleksab.pdsatari.bean.*;
import baleksab.pdsatari.entity.User;
import baleksab.pdsatari.service.UserInventoryService;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "InventoryServlet", value = "/inventory")
public class InventoryServlet extends HttpServlet {

    @Inject
    private UserInventoryService userInventoryService;

    @Inject
    private UserService userService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        InventoryGamesBean bean = new InventoryGamesBean();
        List<GameBean> gameBeans = new ArrayList<>();

        try {
            BeanUtils.populate(bean, req.getParameterMap());
            gameBeans = userInventoryService.getAllGamesInInventory(bean);
        } catch (InvocationTargetException | IllegalAccessException e) {
            System.out.println(e.getMessage());
        }

        String json = new Gson().toJson(gameBeans);

        resp.setContentType("application/json");
        resp.getWriter().write(json);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SellInventoryGameBean bean = new SellInventoryGameBean();
        boolean success = false;

        try {
            BeanUtils.populate(bean, req.getParameterMap());
            success = userInventoryService.sellFromInventory(bean);
        } catch (IllegalAccessException | InvocationTargetException e) {
            System.out.println(e.getMessage());
        }

        if (success) {
            User user = userService.getByUserId(bean.getUserId());
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
