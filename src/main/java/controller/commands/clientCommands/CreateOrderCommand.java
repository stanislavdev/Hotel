package controller.commands.clientCommands;

import controller.commands.Command;
import controller.commands.CommandFactory;
import model.entities.ApartmentType;
import model.services.OrderService;
import model.services.impl.OrderServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Date;

import static model.util.Constants.*;

public class CreateOrderCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        OrderService orderService = new OrderServiceImpl();

        String sClientId = String.valueOf(request.getSession().getAttribute(USER_ID_ATTRIBUTE));
        String numberOfRooms = request.getParameter(NUMBER_OF_ROOMS_ATTRIBUTE);
        String apartmentType = request.getParameter(APARTMENT_TYPE_ATTRIBUTE);
        String dateFrom = request.getParameter(DATE_FROM_ATTRIBUTE);
        String dateTo = request.getParameter(DATE_TO_ATTRIBUTE);

        int clientId = Integer.parseInt(sClientId);

        orderService.createOrder(clientId, Date.valueOf(dateFrom), Date.valueOf(dateTo),
                ApartmentType.valueOf(apartmentType.toLowerCase()), Integer.valueOf(numberOfRooms));
        request.getSession().setAttribute(ORDERS_ATTRIBUTE, orderService.getAllUserOrders(clientId));
        return "redirect:" + CommandFactory.CLIENT_HOME_PAGE;
    }
}
