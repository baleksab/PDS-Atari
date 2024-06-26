package baleksab.pdsatari.repository;

import baleksab.pdsatari.entity.ChatMessage;
import baleksab.pdsatari.entity.User;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;

import java.util.List;

public class ChatMessageRepository {

    private final EntityManagerFactory entityManagerFactory;
    private final EntityManager entityManager;

    public ChatMessageRepository() {
        entityManagerFactory = Persistence.createEntityManagerFactory("PDS-Atari");
        entityManager = entityManagerFactory.createEntityManager();
    }

    @Transactional
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
        if (entityManagerFactory != null) {
            entityManagerFactory.close();
        }
    }
}
