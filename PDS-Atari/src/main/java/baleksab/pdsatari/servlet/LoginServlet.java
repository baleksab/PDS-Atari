package baleksab.pdsatari.servlet;

import baleksab.pdsatari.bean.LoginBean;
import baleksab.pdsatari.bean.UserBean;
import baleksab.pdsatari.exception.ValidationException;
import baleksab.pdsatari.service.UserService;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.commons.beanutils.BeanUtils;

import java.io.IOException;
import java.util.Set;

@WebServlet(name = "LoginServlet", value = "/login")
public class LoginServlet extends HttpServlet {

    @Inject
    private UserService userService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LoginBean loginBean = new LoginBean();

        try {
            BeanUtils.populate(loginBean, req.getParameterMap());

            UserBean userBean = userService.loginPlayer(loginBean);

            HttpSession session = req.getSession();
            session.setAttribute("playerBean", userBean);

            resp.sendRedirect("index.jsp");
        } catch (ValidationException e) {
            Set<String> violations = e.getViolations();

            req.setAttribute("violations", violations);
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
