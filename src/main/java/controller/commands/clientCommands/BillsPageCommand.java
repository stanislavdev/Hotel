package controller.commands.clientCommands;

import controller.commands.Command;
import model.entities.Bill;
import model.entities.Order;
import model.entities.User;
import model.services.BillService;
import model.services.OrderService;
import model.services.impl.BillServiceImpl;
import model.services.impl.OrderServiceImpl;
import model.services.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static model.util.Constants.USER_ID_ATTRIBUTE;

public class BillsPageCommand implements Command {
    private BillServiceImpl billService = BillServiceImpl.getInstance();
    private OrderServiceImpl orderService = OrderServiceImpl.getInstance();
    private UserServiceImpl userService = UserServiceImpl.getInstance();
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String sUserId = String.valueOf(request.getSession().getAttribute(USER_ID_ATTRIBUTE));
        int userId = Integer.parseInt(sUserId);
        List<Bill> billList = billService.getBillsByClientId(userId);
        request.getSession().setAttribute("bills", billList);
        for (Bill bill : billList) {
            Order order = orderService.getById(bill.getOrderId()).get();
            bill.setOrder(order);
            bill.setAdmin(userService.getById(userId).get());
        }
        return CLIENT_BILLS_JSP;
    }
}
