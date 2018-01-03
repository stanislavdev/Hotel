package controller.commands;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Command {
    String SIGN_IN_PAGE = "/sign_in.jsp";
    String CLIENT_HOME = "/client/home";
    String ADMIN_HOME = "/admin/home";

    String execute(HttpServletRequest httpRequest, HttpServletResponse httpResponse);
}
