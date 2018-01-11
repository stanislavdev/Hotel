package controller.commands.commonCommands;

import controller.commands.Command;
import model.entities.User;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SignOutCommand implements Command {
    private static final Logger LOGGER = Logger.getLogger(SignOutCommand.class);

    @Override
    public String execute(HttpServletRequest httpRequest, HttpServletResponse httpResponse) {
        User user = (User) httpRequest.getSession().getAttribute("user");
        if (user.getRole().equals(model.entities.Role.ADMIN)) {
            LOGGER.info("Admin " + user.getId() + " sign out");
        } else {
            LOGGER.info("Client " + user.getId() + " sign out");
        }
        httpRequest.getSession().invalidate();
        return SIGN_IN_PAGE;
    }
}
