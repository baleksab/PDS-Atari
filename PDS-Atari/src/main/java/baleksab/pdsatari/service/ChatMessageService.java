package baleksab.pdsatari.service;

import baleksab.pdsatari.bean.ChatMessageBean;
import baleksab.pdsatari.bean.SendChatMessageBean;
import baleksab.pdsatari.bean.UserBean;
import baleksab.pdsatari.entity.ChatMessage;
import baleksab.pdsatari.entity.User;
import baleksab.pdsatari.exception.ValidationException;
import baleksab.pdsatari.repository.ChatMessageRepository;
import baleksab.pdsatari.util.BeanValidator;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import lombok.val;
import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ChatMessageService {

    public List<ChatMessageBean> getAllChatMessages() {
        ChatMessageRepository chatMessageRepository = new ChatMessageRepository();

        List<ChatMessage> chatMessages = chatMessageRepository.getAll();
        chatMessageRepository.close();

        List<ChatMessageBean> chatMessageBeans = new ArrayList<>();

        for (ChatMessage chatMessage : chatMessages) {
            ChatMessageBean bean = new ChatMessageBean();
            User user = chatMessage.getUser();
            UserBean userBean = new UserBean();

            try {
                BeanUtils.copyProperties(bean, chatMessage);
                BeanUtils.copyProperties(userBean, user);
            } catch (IllegalAccessException | InvocationTargetException e) {
                System.out.println(e.getMessage());
            }

            bean.setUserBean(userBean);
            chatMessageBeans.add(bean);
        }


        return chatMessageBeans;
    }

    public ChatMessageBean sendChatMessage(SendChatMessageBean bean) {
        BeanValidator beanValidator = new BeanValidator();
        ChatMessageRepository chatMessageRepository = new ChatMessageRepository();

        Set<String> violations = beanValidator.validate(bean);

        if (!violations.isEmpty()) {
            throw new ValidationException("Failed to send message, validator constraints failed!", violations);
        }

        User user = chatMessageRepository.getUserById(bean.getSenderId());
        ChatMessage chatMessage = new ChatMessage();

        try {
            BeanUtils.copyProperties(chatMessage, bean);
        } catch (IllegalAccessException | InvocationTargetException e) {
            System.out.println(e.getMessage());
        }

        chatMessage.setUser(user);
        chatMessage = chatMessageRepository.add(chatMessage);

        chatMessageRepository.close();

        ChatMessageBean chatMessageBean = new ChatMessageBean();
        UserBean userBean = new UserBean();

        try {
            BeanUtils.copyProperties(chatMessageBean, chatMessage);
            BeanUtils.copyProperties(userBean, user);
        } catch (InvocationTargetException | IllegalAccessException e) {
            System.out.println(e.getMessage());
        }

        chatMessageBean.setUserBean(userBean);

        return chatMessageBean;
    }

}
