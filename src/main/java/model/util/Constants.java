package model.util;

public interface Constants {
    String REDIRECT_TO = "redirect:";
    String EMAIL_REGEX = "^[a-zA-Z]+[a-zA-Z0-9.!/$#%*&?,_}{~-]?@[a-zA-Z]+[a-zA-Z0-9]?\\.[a-zA-Z]+$";
    String PASSWORD_REGEX = "^[a-zA-Z0-9а-яА-Я.!@#$%^&()*,{}\\/'`~ їєіЇЄІ]{6,}$";
    String USER_ID_ATTRIBUTE = "userId";
    String ORDER_ID_ATTRIBUTE = "orderId";
    String BILL_ID_ATTRIBUTE = "billId";
    String APARTMENT_ID_ATTRIBUTE = "apartmentId";
    String EMAIL_REGISTRATION_ATTRIBUTE = "emailRegistration";
    String PASSWORD_REGISTRATION_ATTRIBUTE = "passwordRegistration";
    String EXCEPTION_ATTRIBUTE = "exception";
    String INPUT_DATA_EXCEPTION = "inputDataException";
    String COUNT_OF_BILLS_ATTRIBUTE = "countOfBills";
    String ORDERS_ATTRIBUTE = "orders";
    String APARTMENTS_ATTRIBUTE = "apartments";
    String BILLS_ATTRIBUTE = "bills";
    String NUMBER_OF_ROOMS_ATTRIBUTE = "numberOfRooms";
    String APARTMENT_TYPE_ATTRIBUTE = "apartmentType";
    String DATE_FROM_ATTRIBUTE = "dateFrom";
    String DATE_TO_ATTRIBUTE = "dateTo";
    String COUNT_OF_ORDERS_ATTRIBUTE = "countOfOrders";
    String PAGE_ATTRIBUTE = "page";
    String EMAIL_ATTRIBUTE = "email";
    String PASSWORD_ATTRIBUTE = "password";
    String LOCALE_ATTRIBUTE = "locale";
    String COMMAND_ATTRIBUTE = "command";
}
