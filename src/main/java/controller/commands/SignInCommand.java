package controller.commands;

import model.entities.Role;
import model.entities.User;
import model.services.AccountService;
import model.services.impl.AccountServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;


public class SignInCommand implements Command {

    private static final String EMAIL = "email";
    private static final String PASSWORD = "password";

    private static final Logger LOGGER = Logger.getLogger(SignInCommand.class);

    @Override
    public String execute(HttpServletRequest httpRequest, HttpServletResponse httpResponse) {
        String email = httpRequest.getParameter(EMAIL);
        String password = httpRequest.getParameter(PASSWORD);
        AccountService accountService = AccountServiceImpl.getInstance();
        Optional<User> user = accountService.signIn(email, password);
        if (user.isPresent()) {
            if (user.get().getRole().equals(Role.ADMIN)) {
                LOGGER.info("Admin " + user.get().getId() + "sign in");
                return ADMIN_HOME;
            } else {
                LOGGER.info("Client " + user.get().getId() + "sign in");
                return CLIENT_HOME;
            }
        } else {
            return SIGN_IN_PAGE;
        }
    }
}
