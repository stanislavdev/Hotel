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
import static model.util.Constants.COMMAND_ATTRIBUTE;
import static model.util.Constants.PAGE_ATTRIBUTE;

@WebFilter("/hotel/*")
public class RequestLoginFilter implements Filter {

    private List<String> commonCommands = new ArrayList<>();
    private List<String> clientCommands = new ArrayList<>();
    private List<String> adminCommands = new ArrayList<>();

    private Optional<User> user;
    private String command;
    private String uri;

    private UserService userService = UserServiceImpl.getInstance();

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
        clientCommands.add(BILL_PAYMENT);

        adminCommands.add(ADMIN_HOME_PAGE);
        adminCommands.add(SIGN_OUT);
        adminCommands.add(CHOSE_APARTMENT_BY_ADMIN);
        adminCommands.add(SEND_BILL_TO_CLIENT);
        adminCommands.add(REJECT_ORDER);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String page = request.getParameter(PAGE_ATTRIBUTE);
        uri = request.getRequestURI();
        user = userService.getUserFromSessionById(request);
        command = request.getParameter(COMMAND_ATTRIBUTE);
        if (hasAccessToCommon() || hasAdminAccess() || hasClientAccess()) {
            request.setAttribute(COMMAND_ATTRIBUTE, command);
        } else {
            request.setAttribute(COMMAND_ATTRIBUTE, LOGIN_PAGE);
        }
        if (isRequireNotClientCommands())
            request.setAttribute(COMMAND_ATTRIBUTE, CLIENT_HOME_PAGE);
        if (isRequireNotAdminCommands())
            request.setAttribute(COMMAND_ATTRIBUTE, ADMIN_HOME_PAGE);
        if (page != null)
            createCommandForPagination(request);
        filterChain.doFilter(request, servletResponse);
    }

    private void createCommandForPagination(HttpServletRequest request) {
        if (uri.matches("/hotel/home/")) {
            if (user.get().getRole().equals(Role.ADMIN)) {
                request.setAttribute(COMMAND_ATTRIBUTE, ADMIN_HOME_PAGE);
            } else {
                request.setAttribute(COMMAND_ATTRIBUTE, CLIENT_HOME_PAGE);
            }
        }
        if (uri.matches("/hotel/bills/")) {
            request.setAttribute(COMMAND_ATTRIBUTE, CLIENT_BILLS_PAGE);
        }
    }

    private boolean hasAccessToCommon() {
        return !user.isPresent() && commonCommands.contains(command);
    }

    private boolean hasClientAccess() {
        return (user.isPresent()) && user.get().getRole().equals(Role.CLIENT)
                && clientCommands.contains(command);
    }

    private boolean hasAdminAccess() {
        return (user.isPresent()) && user.get().getRole().equals(Role.ADMIN)
                && adminCommands.contains(command);
    }

    private boolean isRequireNotClientCommands() {
        return user.isPresent() && user.get().getRole().equals(Role.CLIENT)
                && !clientCommands.contains(command);
    }

    private boolean isRequireNotAdminCommands() {
        return user.isPresent() && user.get().getRole().equals(Role.ADMIN)
                && !adminCommands.contains(command);
    }

    @Override
    public void destroy() {
    }
}
