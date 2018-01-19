package model.dao.impl;

import model.dao.UserDAO;
import model.entities.User;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MySQLUserDAO implements UserDAO {

    private Connection connection;

    private static String ID = "users.id";
    private static String EMAIL = "users.email";
    private static String PASSWORD = "users.password";
    private static String ROLE = "users.role";

    private static String SELECT_ALL_USERS = "SELECT * FROM users";
    private static String SELECT_USER_BY_ID = "SELECT * FROM users WHERE id = ?";
    private static String INSERT_USER = "INSERT INTO users (email, password, role) VALUES (?, ?, ?)";
    private static String UPDATE_USER = "UPDATE users SET password = ? WHERE id = ?";
    private static String SELECT_USER_BY_EMAIL_AND_PASSWORD = "SELECT * FROM users WHERE email = ? AND password = ?";
    private static String SELECT_USER_BY_EMAIL = "SELECT * FROM users WHERE email = ?";
    private static final Logger LOGGER = Logger.getLogger(MySQLUserDAO.class);

    MySQLUserDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Optional<User> findUser(String email, String password) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_EMAIL_AND_PASSWORD);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(parseUser(resultSet));
            } else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_EMAIL);
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(parseUser(resultSet));
            } else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<User> getAll() {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS);
            ResultSet resultSet = preparedStatement.executeQuery();
            return parseUserList(resultSet);
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<User> getById(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(parseUser(resultSet));
            } else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean insert(User object) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER);
            preparedStatement.setString(1, object.getEmail());
            preparedStatement.setString(2, object.getPassword());
            preparedStatement.setString(3, String.valueOf(object.getRole()));
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean update(User object) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER);
            preparedStatement.setString(1, object.getPassword());
            preparedStatement.setInt(2, object.getId());
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new RuntimeException(e);
        }
    }

    private User parseUser(ResultSet resultSet) {
        try {
            return new User.UserBuilder()
                    .id(resultSet.getInt(ID))
                    .email(resultSet.getString(EMAIL))
                    .password(resultSet.getString(PASSWORD))
                    .role(resultSet.getString(ROLE).toUpperCase())
                    .build();
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new RuntimeException(e);
        }
    }

    private List<User> parseUserList(ResultSet resultSet) {
        List<User> userList = new ArrayList<>();
        try {
            while (resultSet.next()) {
                userList.add(parseUser(resultSet));
            }
            return userList;
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
