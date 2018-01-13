package controller.commands.adminCommands;

import controller.commands.Command;
import controller.commands.CommandFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SendBillCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String apartmentId = request.getParameter("chosenApartment");
        String orderId = String.valueOf(request.getSession().getAttribute("orderId"));

        return "redirect:" + CommandFactory.ADMIN_HOME_PAGE;
    }
}
