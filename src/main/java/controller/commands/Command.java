package controller.commands;

import com.sun.deploy.net.HttpRequest;
import com.sun.deploy.net.HttpResponse;

public interface Command {
    String SIGN_IN_PAGE = "/sign_in.jsp";

    String execute(HttpRequest httpRequest, HttpResponse httpResponse);
}
