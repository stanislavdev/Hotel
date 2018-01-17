package controller.commands.commonCommands;

import controller.commands.Command;
import controller.commands.CommandFactory;
import model.entities.Role;
import model.entities.User;
import model.exeptions.WrongInputEmailException;
import model.exeptions.WrongPasswordException;
import model.services.UserService;
import model.services.impl.UserServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

import static model.util.Constants.*;

public class RegistrationCommand implements Command {

    private static Logger LOGGER = Logger.getLogger(RegistrationCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String email = request.getParameter(EMAIL_ATTRIBUTE);
        String password = request.getParameter(PASSWORD_ATTRIBUTE);
        UserService userService = new UserServiceImpl();
        User user = new User.UserBuilder()
                .email(email)
                .password(password)
                .role(Role.CLIENT.name())
                .build();
        try {
            validate(user);
            userService.signUp(user);
            request.getSession().setAttribute(USER_ID_ATTRIBUTE, user.getId());
            return "redirect:" + CommandFactory.CLIENT_HOME_PAGE;
        } catch (WrongInputEmailException | WrongPasswordException | SQLException e) {
            request.setAttribute(EXCEPTION_ATTRIBUTE, INPUT_DATA_EXCEPTION);
            LOGGER.error(e);
            return REGISTRATION_PAGE_JSP;
        }
    }

    private void validate(User user) throws WrongInputEmailException, WrongPasswordException {
        if (!user.getEmail().matches(EMAIL_REGEX))
            throw new WrongInputEmailException();
        if (!user.getPassword().matches(PASSWORD_REGEX))
            throw new WrongPasswordException();
    }
}
