package model.util;

public interface Constants {
    String USER_ID_ATTRIBUTE = "userId";
    String EMAIL_ATTRIBUTE = "emailRegistration";
    String PASSWORD_ATTRIBUTE = "passwordRegistration";

    String EMAIL_REGEX = "^[a-zA-Z]+[a-zA-Z0-9.!/$#%*&?,_}{~-]?@[a-zA-Z]+[a-zA-Z0-9]?\\.[a-zA-Z]+$";
    String PASSWORD_REGEX = "^[a-zA-Z0-9а-яА-Я.!@#$%^&()*,{}\\/'`~ їєіЇЄІ]{6,}$";

    String EXCEPTION_ATTRIBUTE = "exception";
    String INPUT_DATA_EXCEPTION = "inputDataException";

    String ORDERS_ATTRIBUTE = "orders";
    String NUMBER_OF_ROOMS_ATTRIBUTE = "numberOfRooms";
    String APARTMENT_TYPE_ATTRIBUTE = "apartmentType";
    String DATE_FROM_ATTRIBUTE = "dateFrom";
    String DATE_TO_ATTRIBUTE = "dateTo";
}
