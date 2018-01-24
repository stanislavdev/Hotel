package controller.commands.adminCommands;

import controller.commands.Command;
import controller.commands.CommandFactory;
import model.services.BillService;
import model.services.impl.BillServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static model.util.Constants.*;

public class SendBillCommand implements Command {
    private BillService billService = BillServiceImpl.getInstance();

    private String sApartmentId;
    private String sOrderId;
    private int userId;

    private static final Logger LOGGER = Logger.getLogger(SendBillCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        init(request);
        if (sApartmentId == null) {
            request.setAttribute(EXCEPTION_ATTRIBUTE, EXCEPTION_ATTRIBUTE);
            return APARTMENTS_FOR_ORDER;
        }

        int apartmentId = Integer.parseInt(sApartmentId);
        int orderId = Integer.parseInt(sOrderId);

        billService.createNewBill(orderId, apartmentId, userId);
        LOGGER.info("Admin " + request.getSession().getAttribute(USER_ID_ATTRIBUTE) +
                " send bill for order " + sOrderId);
        return REDIRECT_TO + CommandFactory.ADMIN_HOME_PAGE;
    }

    private void init(HttpServletRequest request) {
        sApartmentId = request.getParameter(APARTMENT_ID_ATTRIBUTE);
        sOrderId = String.valueOf(request.getSession().getAttribute(ORDER_ID_ATTRIBUTE));
        userId = (int) request.getSession().getAttribute(USER_ID_ATTRIBUTE);
    }
}
