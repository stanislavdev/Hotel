package controller.commands;


import controller.commands.adminCommands.AdminHomePageCommand;
import controller.commands.clientCommands.ClientPageCommand;
import controller.commands.clientCommands.CreateOrderCommand;
import controller.commands.commonCommands.*;

public class CommandFactory {
    public static final String SIGN_IN = "/sign_in";
    public static final String LOGIN_PAGE = "/login_page";
    public static final String REGISTRATION_PAGE = "/registration_page";
    public static final String REGISTRATION = "/registration";
    public static final String SIGN_OUT = "/sign_out";
    public static final String ADMIN_HOME_PAGE = "/admin_home_page";
    public static final String CLIENT_HOME_PAGE = "/client_home_page";
    public static final String CREATE_ORDER = "/create_order";

    public static Command createCommand(String command) {

        if (command == null) {
            command = LOGIN_PAGE;
        }

        switch (command) {
            case SIGN_IN:
                return new SignInCommand();
            case LOGIN_PAGE:
                return new LoginPageCommand();
            case REGISTRATION_PAGE:
                return new RegistrationPageCommand();
            case REGISTRATION:
                return new RegistrationCommand();
            case SIGN_OUT:
                return new SignOutCommand();
            case ADMIN_HOME_PAGE:
                return new AdminHomePageCommand();
            case CLIENT_HOME_PAGE:
                return new ClientPageCommand();
            case CREATE_ORDER:
                return new CreateOrderCommand();
            default:
                return new SignInCommand();
        }
    }
}
