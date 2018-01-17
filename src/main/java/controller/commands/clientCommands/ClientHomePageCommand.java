package controller.commands.clientCommands;

import controller.commands.Command;
import model.entities.Order;
import model.services.OrderService;
import model.services.impl.OrderServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static model.util.Constants.ORDERS_ATTRIBUTE;
import static model.util.Constants.USER_ID_ATTRIBUTE;

public class ClientHomePageCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String userId = String.valueOf(request.getSession().getAttribute(USER_ID_ATTRIBUTE));
        OrderService orderService = new OrderServiceImpl();
        List<Order> orders = orderService.getAllUserOrders(Integer.parseInt(userId));
        request.getSession().setAttribute(ORDERS_ATTRIBUTE, orders);
        return CLIENT_HOME_JSP;
    }
}
