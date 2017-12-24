package model.dao.impl;

import model.dao.*;

public class MySQLFactoryDAO extends FactoryDAO {
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
