package model.dao.impl;

import model.dao.*;
import org.apache.log4j.Logger;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;


public class MySQLFactoryDAO extends FactoryDAO {
    private static final Logger LOGGER = Logger.getLogger(MySQLFactoryDAO.class);
    private DataSource dataSource;

    public MySQLFactoryDAO() {
        try {
            InitialContext initialContext = new InitialContext();
            dataSource = (DataSource) initialContext.lookup("java:/comp/env/jdbc/hotel");
        } catch (NamingException e) {
            LOGGER.error("Error of looking up the data source: ", e);
            throw new RuntimeException(e);
        }
    }

    public Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            LOGGER.error("Error of getting connection from connection pool: ", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public UserDAO getUserDAO() {
        return new MySQLUserDAO(getConnection());
    }

    @Override
    public ApartmentDAO getApartmentDAO() {
        return new MySQLApartmentDAO(getConnection());
    }

    @Override
    public OrderDAO getOrderDAO() {
        return new MySQLOrderDAO(getConnection());
    }

    @Override
    public BillDAO getBillDAO() {
        return new MySQLBillDAO(getConnection());
    }
}
