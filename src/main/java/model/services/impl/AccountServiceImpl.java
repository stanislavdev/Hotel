package model.services.impl;

import model.dao.UserDAO;
import model.dao.impl.MySQLFactoryDAO;
import model.entities.User;
import model.exeptions.EmailExistException;
import model.services.AccountService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

public class AccountServiceImpl implements AccountService {
    private MySQLFactoryDAO factoryDAO;

    public AccountServiceImpl() {
        factoryDAO = new MySQLFactoryDAO();
    }

    @Override
    public Optional<User> signIn(String email, String password) {
        UserDAO userDAO = factoryDAO.getUserDAO();
        Optional<User> user = userDAO.findUser(email, password);
        if (user.isPresent()) {
            userDAO.close();
            return user;
        }
        userDAO.close();
        return Optional.empty();
    }

    @Override
    public void signUp(User user) throws EmailExistException, SQLException {
        try (Connection connection = factoryDAO.getConnection()) {
            connection.setAutoCommit(false);
            UserDAO userDAO = factoryDAO.getUserDAO();
            if (userDAO.insert(user)) {
                connection.commit();
            } else {
                connection.rollback();
            }
        }
    }

    @Override
    public Optional<User> getById(int id) {
        UserDAO userDAO = factoryDAO.getUserDAO();
        return userDAO.getById(id);
    }
}
