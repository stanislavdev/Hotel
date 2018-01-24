package controller.commands;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Command {
    String SIGN_IN_JSP = "/common/sign_in_page.jsp";
    String CLIENT_HOME_JSP = "/client/client_home_page.jsp";
    String ADMIN_HOME_JSP = "/admin/admin_home_page.jsp";
    String REGISTRATION_PAGE_JSP = "/common/registration_page.jsp";
    String APARTMENTS_FOR_ORDER = "/admin/apartments_selection.jsp";
    String CLIENT_BILLS_JSP = "/client/bills_page.jsp";
    String ERROR_PAGE = "/common/error_page.jsp";

    String execute(HttpServletRequest request, HttpServletResponse response);
}
