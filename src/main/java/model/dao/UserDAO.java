package model.dao;

import model.entities.User;

import java.util.Optional;

public interface UserDAO extends GenericDAO<User> {
    Optional<User> findUser(String email, String password);
}
