package controller.commands.commonCommands;

import controller.commands.Command;
import controller.commands.CommandFactory;
import model.entities.Role;
import model.entities.User;
import model.services.AccountService;
import model.services.impl.AccountServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class RegistrationCommand implements Command {
    private static final String EMAIL_ATTRIBUTE = "emailRegistration";
    private static final String PASSWORD_ATTRIBUTE = "passwordRegistration";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        AccountService accountService = new AccountServiceImpl();
        String email = request.getParameter(EMAIL_ATTRIBUTE);
        String password = request.getParameter(PASSWORD_ATTRIBUTE);
        User user = new User.UserBuilder()
                .email(email)
                .password(password)
                .role(Role.CLIENT.name())
                .build();
        try {
            accountService.signUp(user);
            request.getSession().setAttribute("user", user);
            return "redirect:" + CommandFactory.CLIENT_HOME_PAGE;
        } catch (SQLException e){
            return CommandFactory.REGISTRATION_PAGE;
        }
    }
}
