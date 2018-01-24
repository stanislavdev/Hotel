package model.services.impl;

import model.dao.*;
import model.entities.Apartment;
import model.entities.Bill;
import model.entities.Order;
import model.entities.User;
import model.services.BillService;
import model.services.OrderService;
import model.services.UserService;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class BillServiceImpl implements BillService {
    private FactoryDAO factoryDAO;

    private static Logger LOGGER = Logger.getLogger(BillServiceImpl.class);

    private static class Holder {
        private static BillServiceImpl INSTANCE = new BillServiceImpl();
    }

    public static BillServiceImpl getInstance() {
        return Holder.INSTANCE;
    }

    public BillServiceImpl() {
        factoryDAO = FactoryDAO.getInstance();
    }

    @Override
    public void createNewBill(int orderId, int apartmentId, int adminId) {
        try (Connection connection = factoryDAO.getConnection()) {

            connection.setAutoCommit(false);

            BillDAO billDAO = factoryDAO.getBillDAO();
            OrderDAO orderDAO = factoryDAO.getOrderDAO();
            UserDAO userDAO = factoryDAO.getUserDAO();
            ApartmentDAO apartmentDAO = factoryDAO.getApartmentDAO();

            OrderService orderService = OrderServiceImpl.getInstance();

            Order order = orderDAO.getById(orderId).get();
            User user = userDAO.getById(adminId).get();
            Apartment apartment = apartmentDAO.getById(apartmentId).get();

            int price = orderService.countOrderPrice(order, apartment);

            Bill bill = new Bill.BillBuilder()
                    .admin(user)
                    .order(order)
                    .price(price)
                    .build();

            orderService.updateToAccepted(order);
            orderService.insertIntoOrderHasApartments(order.getId(), apartment.getId());
            if (!billDAO.insert(bill)) {
                connection.rollback();
            } else {
                connection.commit();
            }
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new RuntimeException(e);
        }


    }

    @Override
    public List<Bill> getBillsByClientId(int start, int total, int id) {
        BillDAO billDAO = factoryDAO.getBillDAO();
        List<Bill> billList = billDAO.getBillsByClientId(start, total, id);
        billDAO.close();
        return billList;
    }

    @Override
    public boolean updateBillToPaid(int id) {
        BillDAO billDAO = factoryDAO.getBillDAO();
        boolean isUpdate = billDAO.updateBillToPaid(id);
        billDAO.close();
        return isUpdate;
    }

    @Override
    public int getNumberOfBillsByClientId(int id) {
        BillDAO billDAO = factoryDAO.getBillDAO();
        int number = billDAO.getNumberOfBillsByClientId(id);
        billDAO.close();
        return number;
    }

    public List<Bill> getFilledBillsList(int startPage, int totalPage, int userId) {
        OrderService orderService = OrderServiceImpl.getInstance();
        UserService userService = UserServiceImpl.getInstance();
        List<Bill> billList = getBillsByClientId(startPage, totalPage, userId);
        for (Bill bill : billList) {
            Order order = orderService.getById(bill.getOrderId()).get();
            User user = userService.getById(bill.getAdminId()).get();
            bill.setOrder(order);
            bill.setAdmin(user);
        }
        return billList;
    }
}
