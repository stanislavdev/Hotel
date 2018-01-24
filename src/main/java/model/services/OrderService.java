package model.services;

import model.entities.ApartmentType;
import model.entities.Order;
import model.entities.User;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface OrderService {
    void createOrder(int userId, Date dateFrom, Date dateTo, ApartmentType apartmentType, String numberOfRooms);
    List<Order> getAllUserOrders(int userId);
    List<Order> getAllOrders();
    List<Order> getAllLimitedOrdersByUserId(int start, int total, int userId);
    List<Order> getAllLimitedOrders(int start, int total);
    Optional<Order> getById(int id);
    void updateToAccepted(Order order);
    void insertIntoOrderHasApartments(int orderId, int apartmentId);
    int getTotalNumberOfOrders();
    int getNumberOfOrdersByClientId(int id);
    void rejectOrderById(int id);
}
