package model.services;

import model.entities.Bill;

import java.util.List;

public interface BillService {
    void createNewBill(int orderId, int apartmentId, int adminId);

    List<Bill> getBillsByClientId(int start, int total, int id);

    void updateBillToPaid(int id);

    int getNumberOfBillsByClientId(int id);

    List<Bill>  getFilledBillsList(int startPage, int totalPage, int userId);
}
