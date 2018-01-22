package model.dao.impl;

import model.dao.BillDAO;
import model.entities.Bill;
import model.entities.Order;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MySQLBillDAO implements BillDAO {
    private Connection connection;
    private static final Logger LOGGER = Logger.getLogger(MySQLBillDAO.class);

    private static final String ID = "bills.id";
    private static final String PRICE = "bills.price";
    private static final String ADMIN_ID = "bills.admin_id";
    private static final String ORDER_ID = "bills.order_id";
    private static final String IS_PAID = "bills.isPaid";

    private static final String SELECT_ALL_APARTMENT = "SELECT * FROM bills";
    private static final String INSERT = "INSERT INTO bills (admin_id,order_id,isPaid,price) " +
            "VALUE (?,?,?,?)";
    private static final String SELECT_BILLS_FOR_CLIENT = "SELECT * FROM bills JOIN orders " +
            "ON bills.order_id = orders.id " +
            "JOIN users ON orders.client_id = users.id WHERE client_id = ? LIMIT ?,?";
    private static final String UPDATE_TO_PAID = "UPDATE bills SET isPaid = 1 WHERE id = ?";
    private static final String SELECT_BILLS_COUNT_BY_CLIENT_ID = "SELECT COUNT(*) FROM bills " +
            "INNER  JOIN orders o ON bills.order_id = o.id WHERE o.client_id = ?";

    MySQLBillDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Bill> getAll() {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_APARTMENT);
            ResultSet resultSet = preparedStatement.executeQuery();
            return parseBillList(resultSet);
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Bill> getById(int id) {
        return Optional.empty();
    }

    @Override
    public boolean insert(Bill object) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT);
            preparedStatement.setInt(1, object.getAdmin().getId());
            preparedStatement.setInt(2, object.getOrder().getId());
            preparedStatement.setInt(3, object.getIsPaid());
            preparedStatement.setInt(4, object.getPrice());
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new RuntimeException(e);
        }
    }


    @Override
    public List<Bill> getBillsByClientId(int start, int total,int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BILLS_FOR_CLIENT);
            preparedStatement.setInt(1, id);
            preparedStatement.setInt(2, start-1);
            preparedStatement.setInt(3, total);
            ResultSet resultSet = preparedStatement.executeQuery();
            return parseBillList(resultSet);
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean updateBillToPaid(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_TO_PAID);
            preparedStatement.setInt(1, id);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public int getNumberOfBillsByClientId(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BILLS_COUNT_BY_CLIENT_ID);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return resultSet.getInt(1);
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean update(Bill object) {
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

    private Bill parseBill(ResultSet resultSet) {
        try {
            return new Bill.BillBuilder()
                    .id(resultSet.getInt(ID))
                    .orderId(resultSet.getInt(ORDER_ID))
                    .price(resultSet.getInt(PRICE))
                    .adminId(resultSet.getInt(ADMIN_ID))
                    .isPaid(resultSet.getInt(IS_PAID))
                    .build();
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new RuntimeException(e);
        }
    }

    private List<Bill> parseBillList(ResultSet resultSet) {
        List<Bill> billList = new ArrayList<>();
        try {
            while (resultSet.next()) {
                billList.add(parseBill(resultSet));
            }
            return billList;
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new RuntimeException(e);
        }
    }

}
