package model.dao;


import model.dao.impl.MySQLFactoryDAO;

import java.sql.Connection;

public abstract class FactoryDAO {
    private static volatile FactoryDAO FACTORY_DAO_INSTANCE;

    public abstract UserDAO getUserDAO();

    public abstract ApartmentDAO getApartmentDAO();

    public abstract OrderDAO getOrderDAO();

    public abstract BillDAO getBillDAO();

    public abstract Connection getConnection();

    public static FactoryDAO getInstance() {
        if (FACTORY_DAO_INSTANCE == null) {
            synchronized (FactoryDAO.class) {
                if (FACTORY_DAO_INSTANCE == null) {
                    FACTORY_DAO_INSTANCE = new MySQLFactoryDAO();
                }
            }
        }
        return FACTORY_DAO_INSTANCE;
    }

}
