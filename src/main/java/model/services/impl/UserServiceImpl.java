package model.services.impl;

import model.dao.FactoryDAO;
import model.dao.UserDAO;
import model.dao.impl.MySQLFactoryDAO;
import model.entities.User;
import model.exeptions.EmailExistsException;
import model.services.UserService;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

import static model.util.Constants.USER_ID_ATTRIBUTE;

public class UserServiceImpl implements UserService {
    private FactoryDAO factoryDAO;

    public UserServiceImpl() {
        factoryDAO = FactoryDAO.getInstance();
    }

    private static class Holder {
        private static UserServiceImpl INSTANCE = new UserServiceImpl();
    }

    public static UserServiceImpl getInstance() {
        return Holder.INSTANCE;
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
    public Optional<User> getById(int id) {
        UserDAO userDAO = factoryDAO.getUserDAO();
        Optional<User> user = userDAO.getById(id);
        userDAO.close();
        return user;
    }

    @Override
    public Optional<User> getUserFromSessionById(HttpServletRequest request) {
        if (request.getSession().getAttribute(USER_ID_ATTRIBUTE) != null) {
            String sUserId = String.valueOf(request.getSession().getAttribute(USER_ID_ATTRIBUTE));
            int userId = Integer.parseInt(sUserId);
            return getById(userId);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public void signUp(User user) throws EmailExistsException, SQLException {
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


}
