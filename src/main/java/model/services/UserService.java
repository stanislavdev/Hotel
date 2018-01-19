package model.services;

import model.entities.User;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.Optional;

public interface UserService {
    Optional<User> signIn(String email, String password);
    Optional<User> getById(int id);
    Optional<User> getUserByEmail(String email);
    Optional<User> getUserFromSessionById(HttpServletRequest request);
    void signUp(User user) throws SQLException;
}
