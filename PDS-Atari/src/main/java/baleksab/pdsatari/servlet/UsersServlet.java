package baleksab.pdsatari.servlet;

import baleksab.pdsatari.bean.UserBean;
import baleksab.pdsatari.service.UserService;
import baleksab.pdsatari.util.BeanValidator;
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

@WebServlet(name = "UsersServlet", value = "/users")
public class UsersServlet extends HttpServlet {

    @Inject
    private UserService userService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<UserBean> userBeans = userService.getAllUsers();

        String json = new Gson().toJson(userBeans);

        resp.setContentType("application/json");
        resp.getWriter().write(json);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserBean userBean = new UserBean();

        try {
            BeanUtils.populate(userBean, req.getParameterMap());

            if (req.getParameter("isAdmin") != null && req.getParameter("isAdmin").equals("true")) {
                userBean.setAdmin(true);
            } else if (req.getParameter("isAdmin") != null && req.getParameter("isAdmin").equals("false")) {
                userBean.setAdmin(false);
            }

        } catch (IllegalAccessException | InvocationTargetException e) {
            System.out.println(e.getMessage());
        }

        boolean success = userService.updateUserByBean(userBean);
        UserBean bean = (UserBean) req.getSession().getAttribute("userBean");

        if (success && bean.getId() == userBean.getId()) {
            req.getSession().setAttribute("userBean", userBean);
        }

        Map<String, Object> response = new HashMap<>();
        response.put("success", success);

        String json = new Gson().toJson(response);

        resp.setContentType("application/json");
        resp.getWriter().write(json);
    }
}
