package controller.commands.commonCommands;

import controller.commands.Command;
import controller.commands.CommandFactory;
import model.entities.Role;
import model.entities.User;
import model.services.UserService;
import model.services.impl.UserServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;


public class SignInCommand implements Command {

    private static final String EMAIL = "email";
    private static final String PASSWORD = "password";
    private static final String USER_ATTRIBUTE = "userId";

    private static final Logger LOGGER = Logger.getLogger(SignInCommand.class);


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String email = request.getParameter(EMAIL);
        String password = request.getParameter(PASSWORD);
        UserService userService = new UserServiceImpl();
        Optional<User> user = userService.signIn(email, password);
        if (user.isPresent()) {
            request.getSession().setAttribute(USER_ATTRIBUTE, user.get().getId());
            if (user.get().getRole().equals(Role.ADMIN)) {
                LOGGER.info("Admin " + user.get().getId() + " sign in");
                return "redirect:" +  CommandFactory.ADMIN_HOME_PAGE;
            } else {
                LOGGER.info("Client " + user.get().getId() + " sign in");
                return "redirect:" + CommandFactory.CLIENT_HOME_PAGE;
            }
        } else {
            return SIGN_IN_JSP;
        }
    }
}
