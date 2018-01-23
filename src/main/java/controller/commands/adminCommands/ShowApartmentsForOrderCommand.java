package controller.commands.adminCommands;

import controller.commands.Command;
import controller.commands.CommandFactory;
import model.entities.Apartment;
import model.entities.Order;
import model.services.ApartmentService;
import model.services.OrderService;
import model.services.impl.ApartmentServiceImpl;
import model.services.impl.OrderServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static model.util.Constants.*;

public class ShowApartmentsForOrderCommand implements Command {
    private ApartmentService apartmentService = ApartmentServiceImpl.getInstance();
    private OrderService orderService = OrderServiceImpl.getInstance();

    private String orderId;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        orderId = request.getParameter(ORDER_ID_ATTRIBUTE);
        if (orderId == null) {
            request.setAttribute(EXCEPTION_ATTRIBUTE, EXCEPTION_ATTRIBUTE);
            return REDIRECT_TO + CommandFactory.ADMIN_HOME_PAGE;
        }
        setApartmentAttributes(request);
        return APARTMENTS_FOR_ORDER;
    }

    private void setApartmentAttributes(HttpServletRequest request) {
        Order order = orderService.getById(Integer.parseInt(orderId)).get();
        List<Apartment> apartmentList = apartmentService.showAvailableApartments(order);
        request.getSession().setAttribute(ORDER_ID_ATTRIBUTE, orderId);
        request.getSession().setAttribute(APARTMENTS_ATTRIBUTE, apartmentList);
    }
}
