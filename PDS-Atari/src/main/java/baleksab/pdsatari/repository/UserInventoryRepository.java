package baleksab.pdsatari.repository;

import baleksab.pdsatari.entity.Game;
import baleksab.pdsatari.entity.UserCart;
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

    public void delete(UserInventory userInventory) {
        entityManager.getTransaction().begin();

        TypedQuery<UserInventory> query = entityManager.createQuery("SELECT c FROM UserInventory c WHERE c.game.id = :gameId and c.user.id = :userId", UserInventory.class);
        query.setParameter("gameId", userInventory.getGame().getId());
        query.setParameter("userId", userInventory.getUser().getId());
        UserInventory cart =  query.getSingleResult();

        if (cart != null) {
            Game game = cart.getGame();
            entityManager.remove(cart);
            game.setStock(game.getStock() + 1);
            entityManager.merge(game);
        }

        entityManager.getTransaction().commit();
    }

}
