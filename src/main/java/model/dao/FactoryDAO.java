package model.dao;


import model.dao.impl.MySQLFactoryDAO;

public abstract class FactoryDAO {
    private static FactoryDAO FACTORY_DAO_INSTANCE;

    public abstract UserDAO getUserDAO();
    public abstract ApartmentDAO getApartmentDAO();
    public abstract OrderDAO getOrderDAO();
    public abstract BillDAO getBillDAO();

    public static FactoryDAO getInstance(){
        if (FACTORY_DAO_INSTANCE == null){
            synchronized (FactoryDAO.class){
                if (FACTORY_DAO_INSTANCE == null){
                    FACTORY_DAO_INSTANCE = new MySQLFactoryDAO();
                }
            }
        }
        return FACTORY_DAO_INSTANCE;
    }

}
