package controller.commands.clientCommands;

import controller.commands.Command;
import controller.commands.CommandFactory;
import model.entities.ApartmentType;
import model.services.OrderService;
import model.services.impl.OrderServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.util.Calendar;

import static model.util.Constants.*;

public class CreateOrderCommand implements Command {
    private OrderService orderService = OrderServiceImpl.getInstance();

    private static final Logger LOGGER = Logger.getLogger(CreateOrderCommand.class);

    private String numberOfRooms;
    private String apartmentType;
    private String dateFrom;
    private String dateTo;
    private int clientId;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        init(request);
        if (!isValidDate(dateFrom, dateTo)) {
            request.setAttribute(EXCEPTION_ATTRIBUTE, "wrongDateException");
            return REDIRECT_TO + CommandFactory.CLIENT_HOME_PAGE;
        }
        createOrdersList(request);
        LOGGER.info("Client " + clientId + " create new order");
        return REDIRECT_TO + CommandFactory.CLIENT_HOME_PAGE;
    }

    private boolean isValidDate(String dateFrom, String dateTo) {
        if (dateFrom.equals("") || dateTo.equals("")) {
            return false;
        }
        Date currentDate = new Date(Calendar.getInstance().getTime().getTime());
        Date startDate = Date.valueOf(dateFrom);
        Date finishDate = Date.valueOf(dateTo);
        return !startDate.after(finishDate)
                && !startDate.before(currentDate)
                && !finishDate.before(currentDate);
    }

    private void init(HttpServletRequest request) {
        clientId = (int) request.getSession().getAttribute(USER_ID_ATTRIBUTE);
        numberOfRooms = request.getParameter(NUMBER_OF_ROOMS_ATTRIBUTE);
        apartmentType = request.getParameter(APARTMENT_TYPE_ATTRIBUTE);
        dateFrom = request.getParameter(DATE_FROM_ATTRIBUTE);
        dateTo = request.getParameter(DATE_TO_ATTRIBUTE);
    }

    private void createOrdersList(HttpServletRequest request) {
        orderService.createOrder(clientId, Date.valueOf(dateFrom), Date.valueOf(dateTo),
                ApartmentType.valueOf(apartmentType.toLowerCase()), numberOfRooms);
        request.getSession().setAttribute(ORDERS_ATTRIBUTE, orderService.getAllUserOrders(clientId));
    }
}
