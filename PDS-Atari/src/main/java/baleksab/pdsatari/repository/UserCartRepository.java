package baleksab.pdsatari.repository;

import baleksab.pdsatari.entity.User;
import baleksab.pdsatari.entity.UserCart;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class UserCartRepository {

    @Inject
    private EntityManager entityManager;

    public List<Integer> getAllUsersWithGameInCart(int gameId) {
        TypedQuery<Integer> query = entityManager.createQuery("SELECT c.user.id FROM UserCart c WHERE c.game.id = :gameId", Integer.class);
        query.setParameter("gameId", gameId);
        return query.getResultList();
    }

    public void add(UserCart userCart) {
        entityManager.getTransaction().begin();
        entityManager.persist(userCart);
        entityManager.getTransaction().commit();
    }

    public void delete(UserCart userCart) {
        entityManager.getTransaction().begin();
        Query query = entityManager.createQuery("DELETE FROM UserCart c WHERE c.user.id = :userId AND c.game.id = :gameId");
        query.setParameter("userId", userCart.getUser().getId());
        query.setParameter("gameId", userCart.getGame().getId());
        query.executeUpdate();
        entityManager.getTransaction().commit();
    }

}
