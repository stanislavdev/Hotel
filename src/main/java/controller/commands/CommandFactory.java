package controller.commands;


import controller.commands.adminCommands.AdminHomePageCommand;
import controller.commands.adminCommands.ChoseApartmentForOrderCommand;
import controller.commands.adminCommands.SendBillCommand;
import controller.commands.clientCommands.BillsPageCommand;
import controller.commands.clientCommands.ClientHomePageCommand;
import controller.commands.clientCommands.CreateOrderCommand;
import controller.commands.commonCommands.*;

public class CommandFactory {
    public static final String SIGN_IN = "sign_in";
    public static final String LOGIN_PAGE = "login_page";
    public static final String REGISTRATION_PAGE = "registration_page";
    public static final String REGISTRATION = "registration";
    public static final String SIGN_OUT = "sign_out";
    public static final String ADMIN_HOME_PAGE = "admin_home_page";
    public static final String CLIENT_HOME_PAGE = "client_home_page";
    public static final String CREATE_ORDER = "create_order";
    public static final String CHOSE_APARTMENT_BY_ADMIN = "chose_apartment";
    public static final String SEND_BILL_TO_CLIENT = "create_bill";
    public static final String CLIENT_BILLS_PAGE = "bills-page";
    public static final String CHANGE_LOCALE = "change-locale";

    public static Command createCommand(String command) {

        if (command == null) {
            command = LOGIN_PAGE;
        }

        switch (command) {
            case SIGN_IN:
                return new SignInCommand();
            case CHANGE_LOCALE:
                return new LocaleCommand();
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
            case CLIENT_BILLS_PAGE:
                return new BillsPageCommand();
            default:
                return new SignInCommand();
        }
    }
}
