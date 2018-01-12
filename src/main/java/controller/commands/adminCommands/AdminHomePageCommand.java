package controller.commands.adminCommands;

import controller.commands.Command;
import controller.commands.CommandFactory;
import model.entities.Order;
import model.services.OrderService;
import model.services.impl.OrderServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class AdminHomePageCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        OrderService orderService = new OrderServiceImpl();
        List<Order> orders = orderService.getAllOrders();
        request.getSession().setAttribute("orders", orders);
        return ADMIN_HOME_JSP;
    }
}
