package controller.commands.clientCommands;

import controller.commands.Command;
import model.entities.Order;
import model.entities.User;
import model.services.OrderService;
import model.services.impl.OrderServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ClientHomePageCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        User user = (User) request.getSession().getAttribute("user");
        OrderService orderService = new OrderServiceImpl();
        List<Order> orders = orderService.showUserOrders(user);
        request.getSession().setAttribute("orders", orders);
        return CLIENT_HOME_PAGE;
    }
}
