package model.dao.impl;

import model.dao.*;

import java.sql.Connection;

public class MySQLFactoryDAO extends FactoryDAO {
    private Connection connection;

    @Override
    public UserDAO getUserDAO() {
        return null;
    }

    @Override
    public ApartmentDAO getApartmentDAO() {
        return null;
    }

    @Override
    public OrderDAO getOrderDAO() {
        return null;
    }

    @Override
    public BillDAO getBillDAO() {
        return null;
    }
}
