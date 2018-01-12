package controller.commands.adminCommands;

import controller.commands.Command;
import controller.commands.CommandFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ChoseApartmentForOrderCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        return APARTMENTS_FOR_ORDER;
    }
}
