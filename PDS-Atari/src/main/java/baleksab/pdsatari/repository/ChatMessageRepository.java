package baleksab.pdsatari.repository;

import baleksab.pdsatari.entity.ChatMessage;
import baleksab.pdsatari.entity.User;
import baleksab.pdsatari.util.EntityManagerProducer;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;

import java.util.List;

public class ChatMessageRepository {

    private final EntityManager entityManager;

    public ChatMessageRepository() {
        EntityManagerProducer entityManagerProducer = new EntityManagerProducer();
        entityManager = entityManagerProducer.produceEntityManager();
    }

    public ChatMessage add(ChatMessage chatMessage) {
        entityManager.getTransaction().begin();
        entityManager.persist(chatMessage);
        entityManager.getTransaction().commit();

        return chatMessage;
    }

    public User getUserById(int id) {
        return entityManager.find(User.class, id);
    }

    public List<ChatMessage> getAll() {
        TypedQuery<ChatMessage> query = entityManager.createQuery("SELECT cm FROM ChatMessage cm", ChatMessage.class);
        return query.getResultList();
    }

    public void deleteAll() {
        entityManager.getTransaction().begin();
        Query query = entityManager.createQuery("DELETE FROM ChatMessage");
        query.executeUpdate();
        entityManager.getTransaction().commit();
    }

    public void close() {
        if (entityManager != null) {
            entityManager.close();
        }
    }

}
