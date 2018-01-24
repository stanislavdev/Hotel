package controller.commands.clientCommands;

import controller.commands.Command;
import model.entities.Bill;
import model.entities.Order;
import model.entities.User;
import model.services.impl.BillServiceImpl;
import model.services.impl.OrderServiceImpl;
import model.services.impl.UserServiceImpl;
import model.util.Pagination;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static model.util.Constants.*;

public class BillsPageCommand implements Command, Pagination {
    private BillServiceImpl billService = BillServiceImpl.getInstance();

    private int userId;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        userId = (int) request.getSession().getAttribute(USER_ID_ATTRIBUTE);
        setAttributeForPagination(request);
        List<Bill> billList = billService.getFilledBillsList(Pagination.getPageId(request),
                NUMBER_OF_ENTRIES_FOR_ONE_PAGE, userId);
        request.getSession().setAttribute(BILLS_ATTRIBUTE, billList);
        return CLIENT_BILLS_JSP;
    }

    private void setAttributeForPagination(HttpServletRequest request) {
        int size = billService.getNumberOfBillsByClientId(userId);
        if (size % NUMBER_OF_ENTRIES_FOR_ONE_PAGE == 0) {
            request.setAttribute(COUNT_OF_BILLS_ATTRIBUTE, (size / NUMBER_OF_ENTRIES_FOR_ONE_PAGE));
        } else {
            request.setAttribute(COUNT_OF_BILLS_ATTRIBUTE, (size / NUMBER_OF_ENTRIES_FOR_ONE_PAGE) + 1);
        }
    }
}
