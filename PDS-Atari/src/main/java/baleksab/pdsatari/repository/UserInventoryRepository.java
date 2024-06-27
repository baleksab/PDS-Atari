package baleksab.pdsatari.repository;

import baleksab.pdsatari.entity.Game;
import baleksab.pdsatari.entity.UserInventory;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class UserInventoryRepository {

    @Inject
    private EntityManager entityManager;

    public List<Game> getAllInventoryGames(int userId) {
        TypedQuery<Game> query = entityManager.createQuery("SELECT ui.game FROM UserInventory ui WHERE ui.user.id = :userId", Game.class);
        query.setParameter("userId", userId);

        return query.getResultList();
    }

    public void save(UserInventory userInventory) {
        entityManager.getTransaction().begin();
        entityManager.persist(userInventory);
        entityManager.getTransaction().commit();
    }

    public List<Integer> getAllUsersWithGameInInventory(int gameId) {
        TypedQuery<Integer> query = entityManager.createQuery("SELECT c.user.id FROM UserInventory c WHERE c.game.id = :gameId", Integer.class);
        query.setParameter("gameId", gameId);

        return query.getResultList();
    }

}
