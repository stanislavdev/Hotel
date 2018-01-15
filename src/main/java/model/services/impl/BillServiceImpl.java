package model.services.impl;

import model.dao.BillDAO;
import model.dao.impl.MySQLFactoryDAO;
import model.entities.Bill;
import model.services.BillService;

import java.util.List;

public class BillServiceImpl implements BillService {
    private MySQLFactoryDAO factoryDAO;

    public BillServiceImpl() {
        factoryDAO = new MySQLFactoryDAO();
    }

    @Override
    public void createNewBill(Bill bill) {
        BillDAO billDAO = factoryDAO.getBillDAO();
        billDAO.insert(bill);
    }

    @Override
    public List<Bill> getBillsByClientId(int id) {
        BillDAO billDAO = factoryDAO.getBillDAO();
        return billDAO.getBillsByClientId(id);
    }
}
