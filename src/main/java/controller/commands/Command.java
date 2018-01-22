package controller.commands;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Command {
    String SIGN_IN_JSP = "/sign_in_page.jsp";
    String CLIENT_HOME_JSP = "/client_home_page.jsp";
    String ADMIN_HOME_JSP = "/admin_home_page.jsp";
    String REGISTRATION_PAGE_JSP = "/registration_page.jsp";
    String APARTMENTS_FOR_ORDER = "/apartments_selection.jsp";
    String CLIENT_BILLS_JSP = "/bills_page.jsp";

    String execute(HttpServletRequest request, HttpServletResponse response);
}
