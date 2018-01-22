package model.dao;

import model.entities.Order;
import model.entities.User;

import java.util.List;

public interface OrderDAO extends GenericDAO<Order> {
    List<Order> findByUser(int userId);
    List<Order> getAllLimitedOrdersByUserId(int start, int total, int userId);
    List<Order> getAllLimitedOrders(int start, int total);
    boolean insertIntoOrdersHasApartments(int orderId, int apartmentId);
    int getNumberOfOrders();
    int getNumberOfOrdersByClientId(int id);
    void rejectOrderById(int id);
}
