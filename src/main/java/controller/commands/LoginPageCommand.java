package controller.commands;

import com.sun.deploy.net.HttpRequest;
import com.sun.deploy.net.HttpResponse;

public class LoginPageCommand implements Command {
    @Override
    public String execute(HttpRequest httpRequest, HttpResponse httpResponse) {
        return SIGN_IN_PAGE;
    }
}
