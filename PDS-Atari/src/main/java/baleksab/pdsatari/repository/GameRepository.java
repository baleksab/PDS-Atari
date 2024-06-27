package baleksab.pdsatari.repository;

import baleksab.pdsatari.entity.Game;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

import java.util.List;

public class GameRepository {

    @Inject
    private EntityManager entityManager;

    public List<Game> getAll() {
        TypedQuery<Game> query = entityManager.createQuery("SELECT g FROM Game g", Game.class);
        return query.getResultList();
    }

    public List<Game> getAllGamesPaginated(int pageNumber, int pageSize) {
        TypedQuery<Game> query = entityManager.createQuery("SELECT g FROM Game g", Game.class);
        query.setFirstResult((pageNumber - 1) * pageSize);
        query.setMaxResults(pageSize);
        return query.getResultList();
    }

    public Game getById(int id) {
        return entityManager.find(Game.class, id);
    }

    public void update(Game game) {
        entityManager.getTransaction().begin();
        entityManager.merge(game);
        entityManager.getTransaction().commit();
    }

}
