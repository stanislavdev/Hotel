package controller.commands.adminCommands;

import controller.commands.Command;
import controller.commands.CommandFactory;
import model.entities.Apartment;
import model.entities.Order;
import model.entities.User;
import model.services.UserService;
import model.services.ApartmentService;
import model.services.OrderService;
import model.services.impl.UserServiceImpl;
import model.services.impl.ApartmentServiceImpl;
import model.services.impl.OrderServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

import static model.util.Constants.*;

public class ChoseApartmentForOrderCommand implements Command {
    private ApartmentService apartmentService = ApartmentServiceImpl.getInstance();
    private OrderService orderService = OrderServiceImpl.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String orderId = request.getParameter(APARTMENT_RADIO_ATTRIBUTE);
        if (orderId == null) {
            request.setAttribute(EXCEPTION_ATTRIBUTE, EXCEPTION_ATTRIBUTE);
            return "redirect:" + CommandFactory.ADMIN_HOME_PAGE;
        }
        request.getSession().setAttribute(ORDER_ID_ATTRIBUTE, orderId);
        Optional<Order> order = orderService.getById(Integer.parseInt(orderId));
        List<Apartment> apartmentList = apartmentService.showAvailableApartments(order.get());
        request.getSession().setAttribute(APARTMENTS_ATTRIBUTE, apartmentList);
        return APARTMENTS_FOR_ORDER;
    }
}
