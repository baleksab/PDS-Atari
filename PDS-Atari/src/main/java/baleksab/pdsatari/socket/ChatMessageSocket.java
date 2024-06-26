package baleksab.pdsatari.socket;

import baleksab.pdsatari.bean.ChatMessageBean;
import baleksab.pdsatari.bean.SendChatMessageBean;
import baleksab.pdsatari.exception.ValidationException;
import baleksab.pdsatari.repository.ChatMessageRepository;
import baleksab.pdsatari.service.ChatMessageService;
import com.google.gson.Gson;
import jakarta.ejb.Stateful;
import jakarta.enterprise.inject.spi.CDI;
import jakarta.inject.Inject;
import jakarta.websocket.*;
import jakarta.websocket.server.PathParam;
import jakarta.websocket.server.ServerEndpoint;

import java.io.IOException;
import java.util.*;

@ServerEndpoint(value = "/chat/{userId}")
public class ChatMessageSocket {

    private static final Map<Session, Integer> sessions = Collections.synchronizedMap(new HashMap<>());

    @OnOpen
    public void onOpen(Session session, @PathParam("userId") String userId) {
        sessions.put(session, Integer.parseInt(userId));
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        int id = sessions.get(session);

        SendChatMessageBean bean = new SendChatMessageBean();
        bean.setMessage(message);
        bean.setDate(new Date());
        bean.setSenderId(id);

        ChatMessageService chatMessageService = new ChatMessageService();

        try {
            ChatMessageBean messageBean = chatMessageService.sendChatMessage(bean);
            Gson gson = new Gson();
            broadcast(gson.toJson(messageBean));
        } catch (ValidationException e) {
            System.out.println(e.getMessage());
        }
    }

    @OnClose
    public void onClose(Session session) {
        sessions.remove(session);
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        // Handle errors
        System.err.println("Error on session " + session.getId() + ": " + throwable.getMessage());
    }

    // Utility method to broadcast messages to all connected clients
    private void broadcast(String message) {
        sessions.forEach((session, integer) -> {
            if (session.isOpen()) {
                try {
                    session.getBasicRemote().sendText(message);
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        });
    }

}
