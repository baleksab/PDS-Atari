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

}
