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

import static model.util.Constants.*;

public class SignUpCommand implements Command {

    private UserService userService;

    public SignUpCommand(UserService userService) {
        this.userService = userService;
    }

    private String email;
    private String password;
    private User user;

    private static Logger LOGGER = Logger.getLogger(SignUpCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        init(request);
        createUser();
        if (isValidData(user)) {
            userService.signUp(user);
            request.getSession().setAttribute(USER_ID_ATTRIBUTE, user.getId());
            LOGGER.info("Client " + user.getId() + " has registered");
            return REDIRECT_TO + CommandFactory.CLIENT_HOME_PAGE;
        } else {
            request.setAttribute(EXCEPTION_ATTRIBUTE, INPUT_DATA_EXCEPTION);
            return REGISTRATION_PAGE_JSP;
        }
    }

    private void init(HttpServletRequest request) {
        email = request.getParameter(EMAIL_REGISTRATION_ATTRIBUTE);
        password = request.getParameter(PASSWORD_REGISTRATION_ATTRIBUTE);
    }

    private void createUser() {
        user = new User.UserBuilder()
                .email(email)
                .password(password)
                .role(Role.CLIENT.name())
                .build();
    }

    private boolean isValidData(User user) {
        return user.getEmail().matches(EMAIL_REGEX) &&
                user.getPassword().matches(PASSWORD_REGEX) &&
                !userService.getUserByEmail(user.getEmail()).isPresent();
    }
}
