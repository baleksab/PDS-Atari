package baleksab.pdsbattleship.servlet;

import baleksab.pdsbattleship.bean.LoginBean;
import baleksab.pdsbattleship.bean.PlayerBean;
import baleksab.pdsbattleship.exception.ValidationException;
import baleksab.pdsbattleship.service.PlayerService;
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
    private PlayerService playerService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LoginBean loginBean = new LoginBean();

        try {
            BeanUtils.populate(loginBean, req.getParameterMap());

            PlayerBean playerBean = playerService.loginPlayer(loginBean);

            HttpSession session = req.getSession();
            session.setAttribute("playerBean", playerBean);

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
