package controller.commands.adminCommands;

import controller.commands.Command;
import model.entities.Order;
import model.entities.User;
import model.services.UserService;
import model.services.OrderService;
import model.services.impl.UserServiceImpl;
import model.services.impl.OrderServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class AdminHomePageCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String sPage = request.getParameter("page");
        int pageId;
        if (sPage == null) {
            pageId = 1;
        } else {
            pageId = Integer.parseInt(sPage);
        }
        int total = 3;
        if (pageId == 1) {
        } else {
            pageId = pageId - 1;
            pageId = pageId * total + 1;
        }

        int size = 0;
        OrderService orderService = new OrderServiceImpl();
        List<Order> ordersForCount = orderService.getAllOrders();
        for (Order order : ordersForCount) {
            if (order.getAccepted() == 0) {
                size++;
            }
        }
        if (size % total == 0) {
            request.setAttribute("countOfOrders", (size / total));
        } else {
            request.setAttribute("countOfOrders", (size / total)+1);
        }

        List<Order> orders = orderService.getAllLimitedOrders(pageId, total);
        UserService userService = new UserServiceImpl();
        for (Order order : orders) {
            order.setClient((User) userService.getById(order.getClientId()).get());
        }
        request.getSession().setAttribute("orders", orders);
        return ADMIN_HOME_JSP;
    }
}
