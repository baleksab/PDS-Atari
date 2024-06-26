package baleksab.pdsatari.repository;

import baleksab.pdsatari.entity.ChatMessage;
import baleksab.pdsatari.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
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

    @Transactional
    public User getUserById(int id) {
        return entityManager.find(User.class, id);
    }

    public List<ChatMessage> getAll() {
        TypedQuery<ChatMessage> query = entityManager.createQuery("SELECT cm FROM ChatMessage cm", ChatMessage.class);
        return query.getResultList();
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
