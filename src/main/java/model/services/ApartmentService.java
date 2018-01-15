package model.services;

import model.entities.Apartment;
import model.entities.Order;

import java.util.List;
import java.util.Optional;

public interface ApartmentService {
    List<Apartment> showAvailableApartments(Order order);
    List<Apartment> getAll();
    Optional<Apartment> getById(int id);
    Apartment getByOrderId(int orderId);
}
