package baleksab.pdsbattleship.repository;

import baleksab.pdsbattleship.entity.Player;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class PlayerRepository implements Repository<Player> {

    @Inject
    private EntityManager entityManager;

    @Override
    public void add(Player entity) {
        entityManager.getTransaction().begin();
        entityManager.persist(entity);
        entityManager.getTransaction().commit();
    }

    @Override
    public Player getById(int id) {
        return entityManager.find(Player.class, id);
    }

    public Player getByUsername(String username) {
        TypedQuery<Player> query = entityManager.createQuery("SELECT p FROM Player p WHERE p.username = :username", Player.class);
        query.setParameter("username", username);

        List<Player> result = query.getResultList();

        if (result.isEmpty()) {
            return null;
        } else {
            return result.get(0);
        }
    }

    @Override
    public List<Player> getAll() {
        TypedQuery<Player> query = entityManager.createQuery("SELECT p FROM Player p", Player.class);
        return query.getResultList();
    }

    @Override
    public void update(Player entity) {
        entityManager.getTransaction().begin();
        entityManager.merge(entity);
        entityManager.getTransaction().commit();
    }

    @Override
    public void delete(Player entity) {
        entityManager.getTransaction().begin();
        Player managedEntity = entityManager.merge(entity);
        entityManager.remove(managedEntity);
        entityManager.getTransaction().commit();
    }

}
