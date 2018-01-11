package controller.commands.adminCommands;

import controller.commands.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdminHomePageCommand implements Command {
    @Override
    public String execute(HttpServletRequest httpRequest, HttpServletResponse httpResponse) {
        return ADMIN_HOME_PAGE;
    }
}
