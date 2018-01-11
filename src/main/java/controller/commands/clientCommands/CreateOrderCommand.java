package controller.commands.clientCommands;

import controller.commands.Command;
import controller.commands.CommandFactory;
import model.entities.ApartmentType;
import model.entities.Order;
import model.entities.User;
import model.services.OrderService;
import model.services.impl.OrderServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.util.List;

public class CreateOrderCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        OrderService orderService = new OrderServiceImpl();
        User client = (User) request.getSession().getAttribute("user");
        String numberOfRooms =  request.getParameter("numberOfRooms");
        String apartmentType =  request.getParameter("apartmentType");
        String dateFrom =  request.getParameter("dateFrom");
        String dateTo =  request.getParameter("dateTo");
        orderService.createOrder(client, Date.valueOf(dateFrom), Date.valueOf(dateTo),
                ApartmentType.valueOf(apartmentType.toLowerCase()), Integer.valueOf(numberOfRooms));
        request.getSession().setAttribute("orders", orderService.showUserOrders(client));
        return "redirect:" + CommandFactory.CLIENT_HOME_PAGE;
    }
}
