package model.services;

import model.entities.ApartmentType;
import model.entities.Order;
import model.entities.User;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

public interface OrderService {
    void createOrder(User user, Date dateFrom, Date dateTo, ApartmentType apartmentType, int numberOfRooms);
    List<Order> showUserOrders(User user);
    List<Order> getAllOrders();
    List<Order> getAllLimitedOrders(int start, int total);
    Optional<Order> getById(int id);
    void updateToAccepted(Order order);
    void insertIntoOrderHasApartments(int orderId, int apartmentId);
}
