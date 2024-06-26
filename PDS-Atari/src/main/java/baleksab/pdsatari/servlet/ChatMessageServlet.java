package baleksab.pdsatari.servlet;

import baleksab.pdsatari.bean.ChatMessageBean;
import baleksab.pdsatari.service.ChatMessageService;
import com.google.gson.Gson;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "ChatServlet", value = "/chat")
public class ChatMessageServlet extends HttpServlet {

    @Inject
    private ChatMessageService chatMessageService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<ChatMessageBean> chatMessages =  chatMessageService.getAllChatMessages();

        String json = new Gson().toJson(chatMessages);

        resp.setContentType("application/json");
        resp.getWriter().write(json);
    }

}
