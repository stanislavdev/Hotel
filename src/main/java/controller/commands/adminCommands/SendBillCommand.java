package controller.commands.adminCommands;

import controller.commands.Command;
import controller.commands.CommandFactory;
import model.entities.Apartment;
import model.entities.Bill;
import model.entities.Order;
import model.entities.User;
import model.services.ApartmentService;
import model.services.BillService;
import model.services.OrderService;
import model.services.UserService;
import model.services.impl.ApartmentServiceImpl;
import model.services.impl.BillServiceImpl;
import model.services.impl.OrderServiceImpl;
import model.services.impl.UserServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import static model.util.Constants.*;

public class SendBillCommand implements Command {
    private OrderService orderService = OrderServiceImpl.getInstance();
    private ApartmentService apartmentService = ApartmentServiceImpl.getInstance();
    private BillService billService = BillServiceImpl.getInstance();
    private UserService userService = UserServiceImpl.getInstance();

    private String apartmentId;
    private String orderId;
    private Order order;
    private Bill bill;
    private int totalPrice;

    private static final Logger LOGGER = Logger.getLogger(SendBillCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        init(request);
        countOrderPrice();
        createBill(request);
        orderService.updateToAccepted(order);
        orderService.insertIntoOrderHasApartments(Integer.parseInt(orderId), Integer.parseInt(apartmentId));
        billService.createNewBill(bill);
        LOGGER.info("Admin " + request.getSession().getAttribute(USER_ID_ATTRIBUTE) +
                " send bill for order " + orderId);
        return REDIRECT_TO + CommandFactory.ADMIN_HOME_PAGE;
    }

    private void init(HttpServletRequest request) {
        apartmentId = request.getParameter(APARTMENT_ID_ATTRIBUTE);
        orderId = String.valueOf(request.getSession().getAttribute(ORDER_ID_ATTRIBUTE));
        order = orderService.getById(Integer.parseInt(orderId)).get();
    }

    private void countOrderPrice() {
        Apartment apartment = apartmentService.getById(Integer.parseInt(apartmentId)).get();
        Date dateFrom = order.getDateFrom();
        Date dateTo = order.getDateTo();
        long days = dateTo.getTime() - dateFrom.getTime();
        long convertDays = TimeUnit.DAYS.convert(days, TimeUnit.MILLISECONDS);
        int pricePerDay = (apartment.getPrice());
        totalPrice = Math.toIntExact(convertDays * pricePerDay);
    }

    private void createBill(HttpServletRequest request) {
        int userId = (int) request.getSession().getAttribute(USER_ID_ATTRIBUTE);
        User user = userService.getById(userId).get();
        bill = new Bill.BillBuilder()
                .admin(user)
                .order(order)
                .price(totalPrice)
                .build();
    }
}
