package controller.commands.clientCommands;

import controller.commands.Command;
import controller.commands.CommandFactory;
import model.entities.Order;
import model.entities.User;
import model.services.OrderService;
import model.services.UserService;
import model.services.impl.OrderServiceImpl;
import model.services.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static model.util.Constants.ORDERS_ATTRIBUTE;
import static model.util.Constants.USER_ID_ATTRIBUTE;

public class ClientHomePageCommand implements Command {
    private OrderService orderService = OrderServiceImpl.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        pagination(request, 3);
        return CLIENT_HOME_JSP;
    }

    private void pagination(HttpServletRequest request, int total) {
        String sPage = request.getParameter("page");
        int pageId;
        int size = 0;
        if (sPage != null){
            request.getSession().setAttribute("page", sPage);
            pageId = Integer.parseInt(sPage);
        } else {
            request.getSession().setAttribute("page", 1);
            pageId = 1;
        }


        if (pageId != 1) {
            pageId = (pageId - 1) * total + 1;
        }


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
//        UserService userService = new UserServiceImpl();
//        for (Order order : orders) {
//            order.setClient((User) userService.getById(order.getClientId()).get());
//        }
        request.getSession().setAttribute("orders", orders);
    }
}
