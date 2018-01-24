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

    private String sOrderId;
    private List<Apartment> apartmentList;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        sOrderId = request.getParameter(ORDER_ID_ATTRIBUTE);
        if (sOrderId == null) {
            request.setAttribute(EXCEPTION_ATTRIBUTE, EXCEPTION_ATTRIBUTE);
            return REDIRECT_TO + CommandFactory.ADMIN_HOME_PAGE;
        }
        int orderId = Integer.parseInt(sOrderId);
        apartmentList = apartmentService.showAvailableApartments(orderId);
        setApartmentAttributes(request);
        return APARTMENTS_FOR_ORDER;
    }

    private void setApartmentAttributes(HttpServletRequest request) {
        request.getSession().setAttribute(ORDER_ID_ATTRIBUTE, sOrderId);
        request.getSession().setAttribute(APARTMENTS_ATTRIBUTE, apartmentList);
    }
}
