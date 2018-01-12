package controller.filters;


import controller.commands.Command;
import controller.commands.CommandFactory;
import model.entities.Role;
import model.entities.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.*;

import static controller.commands.CommandFactory.*;

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

        clientCommands.add(CLIENT_HOME_PAGE);
        clientCommands.add(SIGN_OUT);
        clientCommands.add(CREATE_ORDER);

        adminCommands.add(ADMIN_HOME_PAGE);
        adminCommands.add(SIGN_OUT);
        adminCommands.add(CHOSE_APARTMENT_BY_ADMIN);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String command = request.getRequestURI();
        User user = (User) request.getSession().getAttribute("user");

        boolean isGuest = (user == null) && commonCommands.contains(command);
        boolean isAdmin = (user != null) && user.getRole().equals(Role.ADMIN) && adminCommands.contains(command);
        boolean isClient = (user != null) && user.getRole().equals(Role.CLIENT) && clientCommands.contains(command);

        boolean isSignedIn = (user != null) && commonCommands.contains(command);

        boolean needToSignIn = (user == null) && (clientCommands.contains(command) || adminCommands.contains(command));

        if (isAdmin || isClient || isGuest) {
            request.setAttribute("command", command);
        }

        if (isSignedIn) {
            if (user.getRole() == Role.ADMIN) {
                request.setAttribute("command", ADMIN_HOME_PAGE);
            } else {
                request.setAttribute("command", CLIENT_HOME_PAGE);

            }
        }

        if (needToSignIn) {
            request.setAttribute("command" , LOGIN_PAGE);
        }

        filterChain.doFilter(request, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
