package model.dao;

import java.util.List;
import java.util.Optional;

public interface GenericDAO<T> {
    public List<T> getAll();
    public Optional<T> getById(int id);
    public boolean insert(T item);
    public boolean update(T item);
}
