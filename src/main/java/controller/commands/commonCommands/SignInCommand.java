package controller.commands.commonCommands;

import controller.commands.Command;
import controller.commands.CommandFactory;
import model.entities.Role;
import model.entities.User;
import model.services.AccountService;
import model.services.impl.AccountServiceImpl;
import org.apache.log4j.Logger;
import org.omg.CORBA.COMM_FAILURE;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;


public class SignInCommand implements Command {

    private static final String EMAIL = "email";
    private static final String PASSWORD = "password";
    private static final String USER_ATTRIBUTE = "user";

    private static final Logger LOGGER = Logger.getLogger(SignInCommand.class);


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String email = request.getParameter(EMAIL);
        String password = request.getParameter(PASSWORD);
//        AccountService accountService = AccountServiceImpl.getInstance();
        AccountService accountService = new AccountServiceImpl();
        Optional<User> user = accountService.signIn(email, password);
        if (user.isPresent()) {
            request.getSession().setAttribute(USER_ATTRIBUTE, user.get());
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
