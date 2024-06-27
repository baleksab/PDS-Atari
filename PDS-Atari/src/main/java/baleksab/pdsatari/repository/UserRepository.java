package baleksab.pdsatari.repository;

import baleksab.pdsatari.entity.User;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

import java.util.List;


public class UserRepository  {

    @Inject
    private EntityManager entityManager;

    public void add(User entity) {
        entityManager.getTransaction().begin();
        entityManager.persist(entity);
        entityManager.getTransaction().commit();
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

    public User getById(int id) {
        return entityManager.find(User.class, id);
    }

    public List<User> getAll() {
        TypedQuery<User> query = entityManager.createQuery("SELECT p FROM User p", User.class);
        return query.getResultList();
    }

    public void updateUser(User user) {
        entityManager.getTransaction().begin();
        entityManager.merge(user);
        entityManager.getTransaction().commit();
    }

}
