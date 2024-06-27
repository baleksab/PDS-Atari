package baleksab.pdsatari.repository;

import baleksab.pdsatari.entity.Game;
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
        Game game = userCart.getGame();

        if (game.getStock() > 0) {
            entityManager.getTransaction().begin();
            entityManager.persist(userCart);

            game.setStock(game.getStock() - 1);

            entityManager.merge(game);
            entityManager.getTransaction().commit();
        }
    }

    public void delete(UserCart userCart) {
        entityManager.getTransaction().begin();

        TypedQuery<UserCart> query = entityManager.createQuery("SELECT c FROM UserCart c WHERE c.game.id = :gameId and c.user.id = :userId", UserCart.class);
        query.setParameter("gameId", userCart.getGame().getId());
        query.setParameter("userId", userCart.getUser().getId());
        UserCart cart =  query.getSingleResult();

        if (cart != null) {
            Game game = cart.getGame();
            entityManager.remove(cart);
            game.setStock(game.getStock() + 1);
            entityManager.merge(game);
        }

        entityManager.getTransaction().commit();
    }

}
