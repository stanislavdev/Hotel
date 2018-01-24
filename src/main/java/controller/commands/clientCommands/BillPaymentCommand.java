package controller.commands.clientCommands;

import controller.commands.Command;
import controller.commands.CommandFactory;
import model.services.BillService;
import model.services.impl.BillServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static model.util.Constants.BILL_ID_ATTRIBUTE;
import static model.util.Constants.REDIRECT_TO;
import static model.util.Constants.USER_ID_ATTRIBUTE;

public class BillPaymentCommand implements Command {
    private static BillService billService = BillServiceImpl.getInstance();

    private static final Logger LOGGER = Logger.getLogger(BillPaymentCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        int billId = Integer.parseInt(request.getParameter(BILL_ID_ATTRIBUTE));
        billService.updateBillToPaid(billId);
        LOGGER.info("Client " + request.getSession().getAttribute(USER_ID_ATTRIBUTE) + " pay bill" + billId);
        return REDIRECT_TO + CommandFactory.CLIENT_BILLS_PAGE;
    }
}
