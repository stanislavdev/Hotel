package controller.commands.clientCommands;

import controller.commands.Command;
import model.util.Pagination;
import model.entities.Order;
import model.services.OrderService;
import model.services.impl.OrderServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static model.util.Constants.COUNT_OF_ORDERS_ATTRIBUTE;
import static model.util.Constants.ORDERS_ATTRIBUTE;
import static model.util.Constants.USER_ID_ATTRIBUTE;

public class ClientHomePageCommand implements Command, Pagination {
    private OrderService orderService = OrderServiceImpl.getInstance();

    private int clientId;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        clientId = (int) request.getSession().getAttribute(USER_ID_ATTRIBUTE);
        setAttributeForPagination(request);
        createOrderList(request);
        return CLIENT_HOME_JSP;
    }

    private void setAttributeForPagination(HttpServletRequest request) {
        int size = orderService.getNumberOfOrdersByClientId(clientId);
        if (size % NUMBER_OF_ENTRIES_FOR_ONE_PAGE == 0) {
            request.setAttribute(COUNT_OF_ORDERS_ATTRIBUTE, (size / NUMBER_OF_ENTRIES_FOR_ONE_PAGE));
        } else {
            request.setAttribute(COUNT_OF_ORDERS_ATTRIBUTE, (size / NUMBER_OF_ENTRIES_FOR_ONE_PAGE) + 1);
        }
    }

    private void createOrderList(HttpServletRequest request) {
        List<Order> orders = orderService.getAllLimitedOrdersByUserId(Pagination.getPageId(request),
                NUMBER_OF_ENTRIES_FOR_ONE_PAGE, clientId);
        request.getSession().setAttribute(ORDERS_ATTRIBUTE, orders);
    }
}
