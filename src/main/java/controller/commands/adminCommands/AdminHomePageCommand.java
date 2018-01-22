package controller.commands.adminCommands;

import controller.commands.Command;
import model.util.Pagination;
import model.entities.Order;
import model.services.UserService;
import model.services.OrderService;
import model.services.impl.UserServiceImpl;
import model.services.impl.OrderServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static model.util.Constants.COUNT_OF_ORDERS_ATTRIBUTE;
import static model.util.Constants.ORDERS_ATTRIBUTE;

public class AdminHomePageCommand implements Command, Pagination {
    private OrderService orderService = OrderServiceImpl.getInstance();
    private UserService userService = UserServiceImpl.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        setAttributeForPagination(request);
        createOrdersList(request);
        return ADMIN_HOME_JSP;
    }

    private void setAttributeForPagination(HttpServletRequest request) {
        int size = orderService.getTotalNumberOfOrders();
        if (size % NUMBER_OF_ENTRIES_FOR_ONE_PAGE == 0) {
            request.setAttribute(COUNT_OF_ORDERS_ATTRIBUTE, (size / NUMBER_OF_ENTRIES_FOR_ONE_PAGE));
        } else {
            request.setAttribute(COUNT_OF_ORDERS_ATTRIBUTE, (size / NUMBER_OF_ENTRIES_FOR_ONE_PAGE) + 1);
        }
    }

    private void createOrdersList(HttpServletRequest request) {
        int startPosition = Pagination.getPageId(request);
        List<Order> orders = orderService.getAllLimitedOrders(startPosition, NUMBER_OF_ENTRIES_FOR_ONE_PAGE);
        for (Order order : orders) {
            order.setClient(userService.getById(order.getClientId()).get());
        }
        request.getSession().setAttribute(ORDERS_ATTRIBUTE, orders);
    }
}
