package controller.commands.commonCommands;

import controller.commands.Command;
import controller.commands.CommandFactory;
import model.entities.User;
import model.services.UserService;
import model.services.impl.UserServiceImpl;
import model.util.Pages;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Optional;

import static model.util.Constants.USER_ID_ATTRIBUTE;

public class SignOutCommand implements Command {
    private static final Logger LOGGER = Logger.getLogger(SignOutCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        UserService userService = new UserServiceImpl();
        Optional<User> user = userService.getUserFromSessionById(request);
        if (user.get().getRole().equals(model.entities.Role.ADMIN)) {
            LOGGER.info("Admin " + user.get().getId() + " sign out");
        } else {
            LOGGER.info("Client " + user.get().getId() + " sign out");
        }
        request.getSession().invalidate();
        return SIGN_IN_JSP;
    }
}
