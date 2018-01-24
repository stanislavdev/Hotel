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

import static model.util.Constants.*;


public class SignInCommand implements Command {
    private UserService userService;
    private String email;
    private String password;

    private static final Logger LOGGER = Logger.getLogger(SignInCommand.class);

    public SignInCommand(UserService userService){
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        init(request);
        Optional<User> user = userService.signIn(email, password);
        if (user.isPresent()) {
            request.getSession().setAttribute(USER_ID_ATTRIBUTE, user.get().getId());
            if (user.get().getRole().equals(Role.ADMIN)) {
                LOGGER.info("Admin " + user.get().getId() + " sign in");
                return REDIRECT_TO + CommandFactory.ADMIN_HOME_PAGE;
            } else {
                LOGGER.info("Client " + user.get().getId() + " sign in");
                return REDIRECT_TO + CommandFactory.CLIENT_HOME_PAGE;
            }
        } else {
            request.setAttribute(EXCEPTION_ATTRIBUTE, EXCEPTION_ATTRIBUTE);
            return SIGN_IN_JSP;
        }
    }

    private void init(HttpServletRequest request) {
        email = request.getParameter(EMAIL_ATTRIBUTE);
        password = request.getParameter(PASSWORD_ATTRIBUTE);
    }
}
