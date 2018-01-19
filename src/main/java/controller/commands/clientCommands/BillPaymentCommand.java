package controller.commands.clientCommands;

import controller.commands.Command;
import controller.commands.CommandFactory;
import model.services.BillService;
import model.services.impl.BillServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BillPaymentCommand implements Command {
    private static BillService billService = BillServiceImpl.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        int billId = Integer.parseInt(request.getParameter("billId"));
        billService.updateBillToPaid(billId);
        return "redirect:" + CommandFactory.CLIENT_BILLS_PAGE;
    }
}
