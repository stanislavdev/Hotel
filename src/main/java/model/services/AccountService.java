package model.services;

import model.entities.User;

import java.util.Optional;

public interface AccountService {
    Optional<User> signIn(String email, String password);

    void signUp(User user);
}
