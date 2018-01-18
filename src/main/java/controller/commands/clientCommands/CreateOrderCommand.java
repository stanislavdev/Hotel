package controller.commands.clientCommands;

import controller.commands.Command;
import controller.commands.CommandFactory;
import model.entities.ApartmentType;
import model.exeptions.WrongDateException;
import model.services.OrderService;
import model.services.impl.OrderServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import static model.util.Constants.*;

public class CreateOrderCommand implements Command {
    private OrderService orderService = OrderServiceImpl.getInstance();

    private static final Logger LOGGER = Logger.getLogger(CreateOrderCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String sClientId = String.valueOf(request.getSession().getAttribute(USER_ID_ATTRIBUTE));
        String numberOfRooms = request.getParameter(NUMBER_OF_ROOMS_ATTRIBUTE);
        String apartmentType = request.getParameter(APARTMENT_TYPE_ATTRIBUTE);
        String dateFrom = request.getParameter(DATE_FROM_ATTRIBUTE);
        String dateTo = request.getParameter(DATE_TO_ATTRIBUTE);
        try {
            validateDate(dateFrom, dateTo);
        } catch (WrongDateException e) {
            LOGGER.error(e);
            request.setAttribute(EXCEPTION_ATTRIBUTE, "wrongDateException");
            return "redirect:" + CommandFactory.CLIENT_HOME_PAGE;
        }

        int clientId = Integer.parseInt(sClientId);
        orderService.createOrder(clientId, Date.valueOf(dateFrom), Date.valueOf(dateTo),
                ApartmentType.valueOf(apartmentType.toLowerCase()), numberOfRooms);
        request.getSession().setAttribute(ORDERS_ATTRIBUTE, orderService.getAllUserOrders(clientId));
        return "redirect:" + CommandFactory.CLIENT_HOME_PAGE;
    }

    private void validateDate(String dateFrom, String dateTo) throws WrongDateException {
        if (dateFrom.equals("") || dateTo.equals("")) {
            throw new WrongDateException();
        }

        Date currentDate = new Date(Calendar.getInstance().getTime().getTime());
        Date startDate = Date.valueOf(dateFrom);
        Date finishDate = Date.valueOf(dateTo);

        if (startDate.after(finishDate) || startDate.before(currentDate) || finishDate.before(currentDate)) {
            throw new WrongDateException();
        }
    }
}
