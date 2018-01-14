package controller.commands.adminCommands;

import controller.commands.Command;
import controller.commands.CommandFactory;
import model.entities.Bill;
import model.entities.Order;
import model.entities.User;
import model.services.ApartmentService;
import model.services.BillService;
import model.services.OrderService;
import model.services.impl.ApartmentServiceImpl;
import model.services.impl.BillServiceImpl;
import model.services.impl.OrderServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

public class SendBillCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String apartmentId = request.getParameter("chosenApartment");
        String orderId = String.valueOf(request.getSession().getAttribute("orderId"));
        OrderService orderService = new OrderServiceImpl();
        Optional<Order> order = orderService.getById(Integer.parseInt(orderId));
        Date dateFrom = order.get().getDateFrom();
        Date dateTo = order.get().getDateTo();
        ApartmentService apartmentService = new ApartmentServiceImpl();
        long days = dateTo.getTime() - dateFrom.getTime();
        long convertDays = TimeUnit.DAYS.convert(days, TimeUnit.MILLISECONDS);
        int price = Math.toIntExact(convertDays * (apartmentService.getById(Integer.parseInt(apartmentId)).get().getPrice()));
        BillService billService = new BillServiceImpl();
        Bill bill = new Bill.BillBuilder()
                .admin((User) request.getSession().getAttribute("user"))
                .order(order.get())
                .price(price)
                .build();
        orderService.updateToAccepted(order.get());
        orderService.insertIntoOrderHasApartments(Integer.parseInt(orderId), Integer.parseInt(apartmentId));
        billService.createNewBill(bill);
        return "redirect:" + CommandFactory.ADMIN_HOME_PAGE;
    }
}
