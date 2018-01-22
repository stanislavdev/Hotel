package model.services;

import model.entities.Bill;

import java.util.List;

public interface BillService {
    void createNewBill(Bill bill);
    List<Bill> getBillsByClientId(int start, int total,int id);
    boolean updateBillToPaid(int id);
    int getNumberOfBillsByClientId(int id);
}
