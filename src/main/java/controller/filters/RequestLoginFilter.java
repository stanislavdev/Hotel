package controller.filters;

import model.entities.Role;
import model.entities.User;
import model.services.UserService;
import model.services.impl.UserServiceImpl;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.*;

import static controller.commands.CommandFactory.*;
import static model.util.Constants.USER_ID_ATTRIBUTE;

@WebFilter("/hotel/*")
public class RequestLoginFilter implements Filter {

    private List<String> commonCommands = new ArrayList<>();

    private List<String> clientCommands = new ArrayList<>();

    private List<String> adminCommands = new ArrayList<>();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

        commonCommands.add(LOGIN_PAGE);
        commonCommands.add(REGISTRATION);
        commonCommands.add(SIGN_IN);
        commonCommands.add(REGISTRATION_PAGE);
        commonCommands.add(CHANGE_LOCALE);

        clientCommands.add(CLIENT_HOME_PAGE);
        clientCommands.add(CLIENT_BILLS_PAGE);
        clientCommands.add(SIGN_OUT);
        clientCommands.add(CREATE_ORDER);

        adminCommands.add(ADMIN_HOME_PAGE);
        adminCommands.add(SIGN_OUT);
        adminCommands.add(CHOSE_APARTMENT_BY_ADMIN);
        adminCommands.add(SEND_BILL_TO_CLIENT);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        UserService userService = new UserServiceImpl();
        String command = String.valueOf(request.getParameter("command"));
        String url = String.valueOf(request.getRequestURL());
        String page = request.getParameter("page");
        Optional<User> user = userService.getUserFromSessionById(request);

        boolean isPage = (page != null);

        boolean isGuest = (!user.isPresent()) && commonCommands.contains(command);
        boolean isAdmin = (user.isPresent()) && user.get().getRole().equals(Role.ADMIN) &&
                adminCommands.contains(command) || adminCommands.contains(url);
        boolean isClient = (user.isPresent()) && user.get().getRole().equals(Role.CLIENT) &&
                clientCommands.contains(command);

        boolean isSignedIn = (user.isPresent()) && commonCommands.contains(command);
        boolean needToSignIn = (!user.isPresent()) &&
                (clientCommands.contains(command) || adminCommands.contains(command));

        if (isAdmin || isClient || isGuest) {
            request.setAttribute("command", command);
        }

        if (isSignedIn || isPage) {
            if (user.get().getRole() == Role.ADMIN) {
                request.setAttribute("command", ADMIN_HOME_PAGE);
            } else {
                request.setAttribute("command", CLIENT_HOME_PAGE);

            }
        }

        if (needToSignIn) {
            request.setAttribute("command", LOGIN_PAGE);
        }

        filterChain.doFilter(request, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
