package model.dao;

import model.entities.Bill;

import java.util.List;

public interface BillDAO extends GenericDAO<Bill> {
    List<Bill> getBillsByClientId(int id);
}
