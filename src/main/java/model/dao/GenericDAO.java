package model.dao;

import java.util.List;
import java.util.Optional;

public interface GenericDAO<T> {
    List<T> getAll();

    Optional<T> getById(int id);

    boolean insert(T item);

    boolean update(T item);

    void close();
}
