package model.dao.impl;

import model.dao.OrderDAO;
import model.entities.Order;
import model.entities.User;
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
            "(numberOfRooms, dateFrom, dateTo, accepted, apartmentType,client_id ) " +
            "VALUES (?,?,?,?,?,?)";
    private static final String SELECT_ORDER_BY_USER = "SELECT * FROM orders " +
            "WHERE orders.client_id = ?";
    public static final String SELECT_ALL_ORDERS = "SELECT * FROM orders";




    MySQLOrderDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Order> findByUser(User user) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ORDER_BY_USER);
            preparedStatement.setInt(1, user.getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            return parseOrderList(resultSet);
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
        return Optional.empty();
    }

    @Override
    public boolean insert(Order object) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ORDER);
            preparedStatement.setInt(1, object.getNumberOfRooms());
            preparedStatement.setDate(2, object.getDateFrom());
            preparedStatement.setDate(3, object.getDateTo());
            preparedStatement.setInt(4, object.getAccepted());
            preparedStatement.setString(5, String.valueOf(object.getApartmentType()));
            preparedStatement.setInt(6, object.getClient().getId());
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean update(Order object) {
        return false;
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
                    .numberOfRooms(resultSet.getInt(NUMBER_OF_ROOMS))
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
