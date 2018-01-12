package model.dao;

import model.entities.Apartment;
import model.entities.Order;

import java.util.List;


public interface ApartmentDAO extends GenericDAO<Apartment> {
    List<Apartment> findByType(String type);

    List<Apartment> findByNumberOfRooms(int numberOfRooms);

    List<Apartment> showAvailable(Order apartmentInOrder);
}
