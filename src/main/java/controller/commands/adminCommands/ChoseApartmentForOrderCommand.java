package controller.commands.adminCommands;

import controller.commands.Command;
import controller.commands.CommandFactory;
import model.entities.Apartment;
import model.entities.Order;
import model.entities.User;
import model.services.AccountService;
import model.services.ApartmentService;
import model.services.OrderService;
import model.services.impl.AccountServiceImpl;
import model.services.impl.ApartmentServiceImpl;
import model.services.impl.OrderServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

public class ChoseApartmentForOrderCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String orderId = request.getParameter("chosenRadio");
        request.getSession().setAttribute("orderId", orderId);
        ApartmentService apartmentService = new ApartmentServiceImpl();
        OrderService orderService = new OrderServiceImpl();
        Optional<Order> order = orderService.getById(Integer.parseInt(orderId));
        List<Apartment> apartmentList = apartmentService.showAvailableApartments(order.get());
        request.getSession().setAttribute("apartments", apartmentList);

        AccountService accountService = new AccountServiceImpl();
        User user = accountService.getById(order.get().getClientId()).get();
        request.setAttribute("client", user);
        return APARTMENTS_FOR_ORDER;
    }
}
