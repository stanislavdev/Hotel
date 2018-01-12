package model.dao.impl;

import model.dao.ApartmentDAO;
import model.entities.Apartment;
import model.entities.Order;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MySQLApartmentDAO implements ApartmentDAO {
    private Connection connection;

    private static final Logger LOGGER = Logger.getLogger(MySQLApartmentDAO.class);

    private static final String ID = "apartments.id";
    private static final String NUMBERS_OF_ROOMS = "apartments.numberOfRooms";
    private static final String PRICE = "apartments.price";
    private static final String TYPE = "apartments.type";

    private static final String SELECT_AVAILABLE_APARTMENTS = "SELECT * FROM apartments LEFT JOIN " +
            "  orders_has_apartments o ON apartments.id = o.apartments_id " +
            "LEFT JOIN orders o2 ON o.orders_id = o2.id " +
            "WHERE apartments.type = ? AND apartments.numberOfRooms = ? " +
            "AND (dateTo < ? OR dateTo IS NULL)";

    MySQLApartmentDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Apartment> findByType(String type) {
        return null;
    }

    @Override
    public List<Apartment> findByNumberOfRooms(int numberOfRooms) {
        return null;
    }

    @Override
    public List<Apartment> showAvailable(Order apartmentInOrder) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_AVAILABLE_APARTMENTS);
            preparedStatement.setString(1, String.valueOf(apartmentInOrder.getApartmentType()));
            preparedStatement.setInt(2, apartmentInOrder.getNumberOfRooms());
            preparedStatement.setDate(3, apartmentInOrder.getDateFrom());
            ResultSet resultSet = preparedStatement.executeQuery();
            return parseApartmentList(resultSet);
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Apartment> getAll() {
        return null;
    }

    @Override
    public Optional<Apartment> getById(int id) {
        return Optional.empty();
    }

    @Override
    public boolean insert(Apartment object) {
        return false;
    }

    @Override
    public boolean update(Apartment object) {
        return false;
    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new RuntimeException(e);
        }
    }

    private Apartment parseApartment(ResultSet resultSet) {
        try {
            return new Apartment.ApartmentBuilder()
                    .id(resultSet.getInt(ID))
                    .numberOfRooms(resultSet.getInt(NUMBERS_OF_ROOMS))
                    .apartmentType(resultSet.getString(TYPE))
                    .price(resultSet.getInt(PRICE))
                    .build();
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new RuntimeException(e);
        }
    }

    private List<Apartment> parseApartmentList(ResultSet resultSet) {
        List<Apartment> apartmentList = new ArrayList<>();
        try {
            while (resultSet.next()) {
                apartmentList.add(parseApartment(resultSet));
            }
            return apartmentList;
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new RuntimeException(e);
        }
    }
}
