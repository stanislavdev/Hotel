package controller.commands;


import controller.commands.adminCommands.AdminHomePageCommand;
import controller.commands.adminCommands.ChoseApartmentForOrderCommand;
import controller.commands.adminCommands.SendBillCommand;
import controller.commands.clientCommands.ClientHomePageCommand;
import controller.commands.clientCommands.CreateOrderCommand;
import controller.commands.commonCommands.*;

public class CommandFactory {
    public static final String SIGN_IN = "/hotel/sign_in";
    public static final String LOGIN_PAGE = "/hotel/login_page";
    public static final String REGISTRATION_PAGE = "/hotel/registration_page";
    public static final String REGISTRATION = "/hotel/registration";
    public static final String SIGN_OUT = "/hotel/sign_out";
    public static final String ADMIN_HOME_PAGE = "/hotel/admin_home_page";
    public static final String CLIENT_HOME_PAGE = "/hotel/client_home_page";
    public static final String CREATE_ORDER = "/hotel/create_order";
    public static final String CHOSE_APARTMENT_BY_ADMIN = "/hotel/chose-apartment";
    public static final String SEND_BILL_TO_CLIENT = "/hotel/create-bill";

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
                return new ClientHomePageCommand();
            case CREATE_ORDER:
                return new CreateOrderCommand();
            case CHOSE_APARTMENT_BY_ADMIN:
                return new ChoseApartmentForOrderCommand();
            case SEND_BILL_TO_CLIENT:
                return new SendBillCommand();
            default:
                return new SignInCommand();
        }
    }
}
