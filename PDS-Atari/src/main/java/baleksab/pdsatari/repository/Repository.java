package baleksab.pdsatari.repository;

import jakarta.transaction.Transactional;

import java.util.List;

public interface Repository<T> {

    @Transactional
    void add(T entity);

    T getById(int id);

    List<T> getAll();

    @Transactional
    void update(T entity);

    @Transactional
    void delete(T entity);

}
