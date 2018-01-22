package model.dao;

import model.entities.Bill;

import java.util.List;

public interface BillDAO extends GenericDAO<Bill> {
    List<Bill> getBillsByClientId(int start, int total,int id);
    boolean updateBillToPaid(int id);
    int getNumberOfBillsByClientId(int id);
}
