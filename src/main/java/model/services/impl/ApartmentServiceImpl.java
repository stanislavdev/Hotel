package model.services.impl;

import model.dao.ApartmentDAO;
import model.dao.FactoryDAO;
import model.dao.OrderDAO;
import model.dao.impl.MySQLFactoryDAO;
import model.entities.Apartment;
import model.entities.Order;
import model.services.ApartmentService;

import java.util.List;
import java.util.Optional;

public class ApartmentServiceImpl implements ApartmentService {
    private FactoryDAO factoryDAO;

    private static class Holder {
        private static ApartmentServiceImpl INSTANCE = new ApartmentServiceImpl();
    }

    public static ApartmentServiceImpl getInstance() {
        return Holder.INSTANCE;
    }

    public ApartmentServiceImpl() {
        factoryDAO = FactoryDAO.getInstance();
    }

    @Override
    public List<Apartment> showAvailableApartments(int orderId) {
        ApartmentDAO apartmentDAO = factoryDAO.getApartmentDAO();
        OrderDAO orderDAO = factoryDAO.getOrderDAO();
        Order order = orderDAO.getById(orderId).get();
        List<Apartment> apartmentList = apartmentDAO.showAvailable(order);
        apartmentDAO.close();
        return apartmentList;
    }

    @Override
    public List<Apartment> getAll() {
        ApartmentDAO apartmentDAO = factoryDAO.getApartmentDAO();
        List<Apartment> apartmentList = apartmentDAO.getAll();
        apartmentDAO.close();
        return apartmentList;
    }

    @Override
    public Optional<Apartment> getById(int id) {
        ApartmentDAO apartmentDAO = factoryDAO.getApartmentDAO();
        Optional<Apartment> apartment = apartmentDAO.getById(id);
        apartmentDAO.close();
        return apartment;

    }

    @Override
    public Apartment getByOrderId(int orderId) {
        ApartmentDAO apartmentDAO = factoryDAO.getApartmentDAO();
        Apartment apartment = apartmentDAO.getByOrderID(orderId);
        apartmentDAO.close();
        return apartment;
    }
}
