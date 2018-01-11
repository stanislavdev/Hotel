package controller.commands;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Command {
    String SIGN_IN_PAGE = "/sign_in.jsp";
    String CLIENT_HOME_PAGE = "/client_home.jsp";
    String ADMIN_HOME_PAGE = "/admin_home.jsp";
    String REGISTRATION_PAGE = "/registration_page.jsp";

    String execute(HttpServletRequest request, HttpServletResponse response);
}
