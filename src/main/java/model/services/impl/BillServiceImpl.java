package model.services.impl;

import model.dao.BillDAO;
import model.dao.FactoryDAO;
import model.dao.impl.MySQLFactoryDAO;
import model.entities.Bill;
import model.services.BillService;

import java.util.List;

public class BillServiceImpl implements BillService {
    private FactoryDAO factoryDAO;

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
    public void createNewBill(Bill bill) {
        BillDAO billDAO = factoryDAO.getBillDAO();
        billDAO.insert(bill);
        billDAO.close();
    }

    @Override
    public List<Bill> getBillsByClientId(int start, int total,int id) {
        BillDAO billDAO = factoryDAO.getBillDAO();
        List<Bill> billList = billDAO.getBillsByClientId(start,total,id);
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
}
