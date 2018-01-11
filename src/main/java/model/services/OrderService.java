package model.services;

import model.entities.ApartmentType;
import model.entities.Order;
import model.entities.User;

import java.sql.Date;
import java.util.List;

public interface OrderService {
    void createOrder(User user, Date dateFrom, Date dateTo, ApartmentType apartmentType, int numberOfRooms);
    List<Order> showUserOrders(User user);
}
