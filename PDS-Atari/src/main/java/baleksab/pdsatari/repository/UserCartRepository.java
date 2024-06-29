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

    public List<Game> getAllGamesInCart(int userId) {
        TypedQuery<Game> query = entityManager.createQuery("SELECT c.game FRom UserCart c WHERE c.user.id = :userId", Game.class);
        query.setParameter("userId", userId);

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

    public void delete(UserCart userCart, boolean incrementGameStock) {
        entityManager.getTransaction().begin();

        TypedQuery<UserCart> query = entityManager.createQuery("SELECT c FROM UserCart c WHERE c.game.id = :gameId and c.user.id = :userId", UserCart.class);
        query.setParameter("gameId", userCart.getGame().getId());
        query.setParameter("userId", userCart.getUser().getId());
        UserCart cart =  query.getSingleResult();

        if (cart != null) {
            Game game = cart.getGame();
            entityManager.remove(cart);

            if (incrementGameStock) {
                game.setStock(game.getStock() + 1);
                entityManager.merge(game);
            }
        }

        entityManager.getTransaction().commit();
    }

}
