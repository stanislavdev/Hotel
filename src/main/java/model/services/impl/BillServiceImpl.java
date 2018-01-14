package model.services.impl;

import model.dao.BillDAO;
import model.dao.impl.MySQLFactoryDAO;
import model.entities.Bill;
import model.services.BillService;

public class BillServiceImpl implements BillService {
    MySQLFactoryDAO factoryDAO;
    public BillServiceImpl(){
        factoryDAO = new MySQLFactoryDAO();
    }
    @Override
    public void createNewBill(Bill bill) {
        BillDAO billDAO = factoryDAO.getBillDAO();
        billDAO.insert(bill);
    }
}
