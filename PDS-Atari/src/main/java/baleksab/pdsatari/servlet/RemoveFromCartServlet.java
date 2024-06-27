package baleksab.pdsatari.servlet;

import baleksab.pdsatari.bean.AddToCartBean;
import baleksab.pdsatari.bean.RemoveFromCartBean;
import baleksab.pdsatari.service.UserCartService;
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
import java.util.Map;

@WebServlet(name = "RemoveFromCartServlet", value = "/removeFromCart")
public class RemoveFromCartServlet extends HttpServlet {

    @Inject
    private UserCartService userCartService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RemoveFromCartBean bean = new RemoveFromCartBean();
        boolean success = false;

        try {
            BeanUtils.populate(bean, req.getParameterMap());
            success = userCartService.removeFromUserCart(bean);
        } catch (IllegalAccessException | InvocationTargetException e) {
            System.out.println(e.getMessage());
        }

        Map<String, Object> response = new HashMap<>();
        response.put("success", success);

        String json = new Gson().toJson(response);

        resp.setContentType("application/json");
        resp.getWriter().write(json);
    }

}
