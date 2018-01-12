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

    private static final String SELECT_ALL_APARTMENT = "SELECT * FROM apartments";

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
        return false;
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
