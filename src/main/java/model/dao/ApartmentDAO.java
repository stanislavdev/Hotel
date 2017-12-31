package model.dao;

import model.entities.Apartment;

import java.util.List;


public interface ApartmentDAO extends GenericDAO<Apartment> {
    List<Apartment> findByType(String type);

    List<Apartment> findByNumberOfRooms(int numberOfRooms);
}
