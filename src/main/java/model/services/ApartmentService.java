package model.services;

import model.entities.Apartment;
import model.entities.Order;

import java.util.List;

public interface ApartmentService {
    List<Apartment> showAvailableApartments(Order order);
}
