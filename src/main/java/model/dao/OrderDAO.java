package model.dao;

import model.entities.Order;
import model.entities.User;

import java.util.List;

public interface OrderDAO extends GenericDAO<Order> {
    List<Order> findByUser(User user);
    List<Order> getAllLimit(int start, int total);
    void insertIntoOrdersHasApartments(int orderId, int apartmentId);


}
