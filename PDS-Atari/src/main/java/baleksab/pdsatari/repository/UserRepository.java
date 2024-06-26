package baleksab.pdsatari.repository;

import baleksab.pdsatari.entity.User;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class UserRepository implements Repository<User> {

    @Inject
    private EntityManager entityManager;

    @Override
    public void add(User entity) {
        entityManager.getTransaction().begin();
        entityManager.persist(entity);
        entityManager.getTransaction().commit();
    }

    @Override
    public User getById(int id) {
        return entityManager.find(User.class, id);
    }

    public User getByEmail(String email) {
        TypedQuery<User> query = entityManager.createQuery("SELECT u FROM User u WHERE u.email = :email", User.class);
        query.setParameter("email", email);

        List<User> result = query.getResultList();

        if (result.isEmpty()) {
            return null;
        } else {
            return result.get(0);
        }
    }

    @Override
    public List<User> getAll() {
        TypedQuery<User> query = entityManager.createQuery("SELECT p FROM User p", User.class);
        return query.getResultList();
    }

    @Override
    public void update(User entity) {
        entityManager.getTransaction().begin();
        entityManager.merge(entity);
        entityManager.getTransaction().commit();
    }

    @Override
    public void delete(User entity) {
        entityManager.getTransaction().begin();
        User managedEntity = entityManager.merge(entity);
        entityManager.remove(managedEntity);
        entityManager.getTransaction().commit();
    }

}
