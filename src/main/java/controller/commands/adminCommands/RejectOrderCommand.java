package controller.commands.adminCommands;

import controller.commands.Command;
import controller.commands.CommandFactory;
import model.services.OrderService;
import model.services.impl.OrderServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static model.util.Constants.ORDER_ID_ATTRIBUTE;
import static model.util.Constants.REDIRECT_TO;
import static model.util.Constants.USER_ID_ATTRIBUTE;

public class RejectOrderCommand implements Command {
    private OrderService orderService = OrderServiceImpl.getInstance();

    private static final Logger LOGGER = Logger.getLogger(RejectOrderCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String orderId = String.valueOf(request.getSession().getAttribute(ORDER_ID_ATTRIBUTE));
        orderService.rejectOrderById(Integer.parseInt(orderId));
        LOGGER.info("Admin " + request.getSession().getAttribute(USER_ID_ATTRIBUTE) + " reject order " + orderId);
        return REDIRECT_TO + CommandFactory.ADMIN_HOME_PAGE;
    }
}
