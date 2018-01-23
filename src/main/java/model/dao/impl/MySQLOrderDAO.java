package model.dao.impl;

import model.dao.OrderDAO;
import model.entities.Order;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MySQLOrderDAO implements OrderDAO {
    private Connection connection;

    private static final Logger LOGGER = Logger.getLogger(MySQLOrderDAO.class);

    private static final String ID = "orders.id";
    private static final String NUMBER_OF_ROOMS = "orders.numberOfRooms";
    private static final String DATE_FROM = "orders.dateFrom";
    private static final String DATE_TO = "orders.dateTo";
    private static final String ACCEPTED = "orders.accepted";
    private static final String APARTMENT_TYPE = "orders.apartmentType";
    private static final String CLIENT_ID = "orders.client_id";

    private static final String INSERT_ORDER = "INSERT INTO orders " +
            "(numberOfRooms, dateFrom, dateTo, accepted, apartmentType,client_id) " +
            "VALUES (?,?,?,?,?,?)";
    private static final String SELECT_ORDER_BY_USER = "SELECT * FROM orders " +
            "WHERE orders.client_id = ?";
    private static final String SELECT_ALL_ORDERS = "SELECT * FROM orders";
    private static final String SELECT_ALL_LIMIT_ORDERS_BY_USER_ID = "SELECT * FROM orders " +
            "WHERE orders.client_id = ? AND (orders.accepted = 0 OR orders.accepted = -1) LIMIT ?,?";
    private static final String SELECT_BY_ID = "SELECT * FROM orders WHERE orders.id = ?";
    private static final String UPDATE_TO_ACCEPTED = "UPDATE orders SET orders.accepted = 1 WHERE orders.id = ?";
    private static final String INSERT_INTO_ORDERS_HAS_APARTMENTS = "INSERT INTO orders_has_apartments " +
            "(orders_id, apartments_id) VALUES (?,?)";
    private static final String SELECT_ALL_LIMIT_ORDERS = "SELECT * FROM orders " +
            "WHERE orders.accepted = 0 LIMIT ?,?";
    private static final String SELECT_NUMBER_OF_ORDERS = "SELECT COUNT(*) FROM orders WHERE orders.accepted =0";
    private static final String SELECT_NUMBER_OF_ORDERS_BY_CLIENT_ID = "SELECT COUNT(*) FROM orders " +
            "WHERE orders.client_id = ? AND (orders.accepted = 0 OR orders.accepted = -1)";
    private static final String REJECT_ORDER_BY_ID = "UPDATE orders SET orders.accepted = -1 WHERE orders.id = ?";

    MySQLOrderDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Order> findByUser(int userId) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ORDER_BY_USER);
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            return parseOrderList(resultSet);
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Order> getAllLimitedOrdersByUserId(int start, int total, int userId) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_LIMIT_ORDERS_BY_USER_ID);
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, start - 1);
            preparedStatement.setInt(3, total);
            ResultSet resultSet = preparedStatement.executeQuery();
            return parseOrderList(resultSet);
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Order> getAllLimitedOrders(int start, int total) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_LIMIT_ORDERS);
            preparedStatement.setInt(1, start - 1);
            preparedStatement.setInt(2, total);
            ResultSet resultSet = preparedStatement.executeQuery();
            return parseOrderList(resultSet);
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean insertIntoOrdersHasApartments(int orderId, int apartmentId) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INTO_ORDERS_HAS_APARTMENTS);
            preparedStatement.setInt(1, orderId);
            preparedStatement.setInt(2, apartmentId);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public int getNumberOfOrders() {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_NUMBER_OF_ORDERS)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return resultSet.getInt(1);
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public int getNumberOfOrdersByClientId(int id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_NUMBER_OF_ORDERS_BY_CLIENT_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return resultSet.getInt(1);
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void rejectOrderById(int id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(REJECT_ORDER_BY_ID)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Order> getAll() {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_ORDERS);
            ResultSet resultSet = preparedStatement.executeQuery();
            return parseOrderList(resultSet);
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new RuntimeException(e);
        }

    }

    @Override
    public Optional<Order> getById(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(parseOrder(resultSet));
            } else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean insert(Order object) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ORDER);
            preparedStatement.setString(1, object.getNumberOfRooms());
            preparedStatement.setDate(2, object.getDateFrom());
            preparedStatement.setDate(3, object.getDateTo());
            preparedStatement.setInt(4, object.getAccepted());
            preparedStatement.setString(5, String.valueOf(object.getApartmentType()));
            preparedStatement.setInt(6, object.getClientId());
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean update(Order object) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_TO_ACCEPTED);
            preparedStatement.setInt(1, object.getId());
            return preparedStatement.executeUpdate() > 0;
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

    private Order parseOrder(ResultSet resultSet) {
        try {
            return new Order.OrderBuilder()
                    .id(resultSet.getInt(ID))
                    .numberOfRooms(resultSet.getString(NUMBER_OF_ROOMS))
                    .dateFrom(resultSet.getDate(DATE_FROM))
                    .dateTo(resultSet.getDate(DATE_TO))
                    .accepted(resultSet.getInt(ACCEPTED))
                    .apartmentType(resultSet.getString(APARTMENT_TYPE).toLowerCase())
                    .clintId(resultSet.getInt(CLIENT_ID))
                    .build();
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new RuntimeException(e);
        }
    }

    private List<Order> parseOrderList(ResultSet resultSet) {
        List<Order> orderList = new ArrayList<>();
        try {
            while (resultSet.next()) {
                orderList.add(parseOrder(resultSet));
            }
            return orderList;
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new RuntimeException(e);
        }
    }
}
