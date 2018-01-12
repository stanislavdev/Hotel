package controller.commands.commonCommands;

import controller.commands.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegistrationPageCommand implements Command {
    @Override
    public String execute(HttpServletRequest httpRequest, HttpServletResponse httpResponse) {
        return REGISTRATION_PAGE_JSP;
    }
}
