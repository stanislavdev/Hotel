package controller.commands.clientCommands;

import controller.commands.Command;
import model.entities.Bill;
import model.entities.Order;
import model.entities.User;
import model.services.BillService;
import model.services.OrderService;
import model.services.impl.BillServiceImpl;
import model.services.impl.OrderServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class BillsPageCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        BillService billService = new BillServiceImpl();
        OrderService orderService = new OrderServiceImpl();
        User user = (User) request.getSession().getAttribute("user");
        List<Bill> billList = billService.getBillsByClientId(user.getId());
        request.getSession().setAttribute("bills", billList);
        for (Bill bill : billList) {
            Order order = orderService.getById(bill.getOrderId()).get();
            bill.setOrder(order);
            System.out.println(order.getNumberOfRooms());
            bill.setAdmin(user);
        }

        return CLIENT_BILLS_JSP;
    }
}
