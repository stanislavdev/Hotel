package model.services.impl;

import model.dao.OrderDAO;
import model.dao.impl.MySQLFactoryDAO;
import model.dao.impl.MySQLOrderDAO;
import model.entities.ApartmentType;
import model.entities.Order;
import model.entities.User;
import model.services.OrderService;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

public class OrderServiceImpl implements OrderService {
    private MySQLFactoryDAO factoryDAO;

    public OrderServiceImpl() {
        factoryDAO = new MySQLFactoryDAO();
    }

    @Override
    public void createOrder(User user, Date dateFrom, Date dateTo, ApartmentType apartmentType, int numberOfRooms) {
        OrderDAO orderDAO = factoryDAO.getOrderDAO();
        Order order = new Order.OrderBuilder()
                .id(0)
                .dateFrom(dateFrom)
                .dateTo(dateTo)
                .apartmentType(apartmentType.name())
                .accepted(0)
                .numberOfRooms(numberOfRooms)
                .client(user)
                .build();
        orderDAO.insert(order);
    }

    @Override
    public List<Order> showUserOrders(User user) {
        OrderDAO orderDAO = factoryDAO.getOrderDAO();
        return orderDAO.findByUser(user);
    }

    @Override
    public List<Order> getAllOrders() {
        OrderDAO orderDAO = factoryDAO.getOrderDAO();
        return orderDAO.getAll();
    }

    @Override
    public Optional<Order> getById(int id) {
        OrderDAO  orderDAO= factoryDAO.getOrderDAO();
        return orderDAO.getById(id);
    }

    @Override
    public void updateToAccepted(Order order) {
        OrderDAO orderDAO = factoryDAO.getOrderDAO();
        orderDAO.update(order);
    }

    @Override
    public void insertIntoOrderHasApartments(int orderId, int apartmentId) {
        OrderDAO orderDAO = factoryDAO.getOrderDAO();
        orderDAO.insertIntoOrdersHasApartments(orderId, apartmentId);
    }
}
