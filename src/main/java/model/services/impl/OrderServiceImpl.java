package model.services.impl;

import model.dao.FactoryDAO;
import model.dao.OrderDAO;
import model.dao.impl.MySQLFactoryDAO;
import model.dao.impl.MySQLOrderDAO;
import model.entities.ApartmentType;
import model.entities.Order;
import model.entities.User;
import model.services.OrderService;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class OrderServiceImpl implements OrderService {
    private FactoryDAO factoryDAO;

    private static class Holder {
        private static OrderServiceImpl INSTANCE = new OrderServiceImpl();
    }

    public static OrderServiceImpl getInstance() {
        return Holder.INSTANCE;
    }

    public OrderServiceImpl() {
        factoryDAO = FactoryDAO.getInstance();
    }

    @Override
    public void createOrder(int userId, Date dateFrom, Date dateTo, ApartmentType apartmentType, String numberOfRooms) {
        OrderDAO orderDAO = factoryDAO.getOrderDAO();
        Order order = new Order.OrderBuilder()
                .id(0)
                .dateFrom(dateFrom)
                .dateTo(dateTo)
                .apartmentType(apartmentType.name())
                .accepted(0)
                .numberOfRooms(numberOfRooms)
                .clintId(userId)
                .build();
        orderDAO.insert(order);
        orderDAO.close();
    }

    @Override
    public List<Order> getAllUserOrders(int userId) {
        OrderDAO orderDAO = factoryDAO.getOrderDAO();
        List<Order> orderList = orderDAO.findByUser(userId);
        orderDAO.close();
        return orderList;
    }

    @Override
    public List<Order> getAllOrders() {
        OrderDAO orderDAO = factoryDAO.getOrderDAO();
        List<Order> orderList = orderDAO.getAll();
        orderDAO.close();
        return orderList;
    }

    @Override
    public List<Order> getAllLimitedOrders(int start, int total) {
        OrderDAO orderDAO = factoryDAO.getOrderDAO();
        List<Order> orderList = orderDAO.getAllLimit(start, total);
        orderDAO.close();
        return orderList;
    }

    @Override
    public Optional<Order> getById(int id) {
        OrderDAO orderDAO = factoryDAO.getOrderDAO();
        Optional<Order> order = orderDAO.getById(id);
        orderDAO.close();
        return order;
    }

    @Override
    public void updateToAccepted(Order order) {
        OrderDAO orderDAO = factoryDAO.getOrderDAO();
        orderDAO.update(order);
        orderDAO.close();
    }

    @Override
    public void insertIntoOrderHasApartments(int orderId, int apartmentId) throws SQLException {
        try (Connection connection = factoryDAO.getConnection()) {
            connection.setAutoCommit(false);
            OrderDAO orderDAO = factoryDAO.getOrderDAO();
            if (orderDAO.insertIntoOrdersHasApartments(orderId, apartmentId)) {
                connection.commit();
            } else {
                connection.rollback();
            }
        }
    }
}
