package model.entities;

import java.sql.Date;
import java.util.List;

public class Order {
    private int id;
    private int numberOfRooms;
    private ApartmentType apartmentType;
    private Date dateFrom;
    private Date dateTo;
    private int accepted;
    private User client;
    private List<Apartment> apartments;

    private Order(OrderBuilder orderBuilder) {
        this.id = orderBuilder.id;
        this.numberOfRooms = orderBuilder.numberOfRooms;
        this.apartmentType = orderBuilder.apartmentType;
        this.apartmentType = orderBuilder.apartmentType;
        this.dateFrom = orderBuilder.dateFrom;
        this.dateTo = orderBuilder.dateTo;
        this.accepted = orderBuilder.accepted;
        this.client = orderBuilder.client;
        this.apartments = orderBuilder.apartments;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumberOfRooms() {
        return numberOfRooms;
    }

    public void setNumberOfRooms(int numberOfRooms) {
        this.numberOfRooms = numberOfRooms;
    }

    public ApartmentType getApartmentType() {
        return apartmentType;
    }

    public void setApartmentType(ApartmentType apartmentType) {
        this.apartmentType = apartmentType;
    }

    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public Date getDateTo() {
        return dateTo;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }

    public int getAccepted() {
        return accepted;
    }

    public void setAccepted(int accepted) {
        this.accepted = accepted;
    }

    public User getClient() {
        return client;
    }

    public void setClient(User client) {
        this.client = client;
    }

    public List<Apartment> getApartments() {
        return apartments;
    }

    public void setApartments(List<Apartment> apartments) {
        this.apartments = apartments;
    }

    public static class OrderBuilder{
        private int id;
        private int numberOfRooms;
        private ApartmentType apartmentType;
        private Date dateFrom;
        private Date dateTo;
        private int accepted;
        private User client;
        private List<Apartment> apartments;

        public OrderBuilder id(int id){
            this.id = id;
            return this;
        }

        public OrderBuilder numberOfRooms(int numberOfRooms){
            this.numberOfRooms = numberOfRooms;
            return this;
        }

        public OrderBuilder apartmentType(String apartmentType){
            this.apartmentType = ApartmentType.valueOf(apartmentType);
            return this;
        }

        public OrderBuilder dateFrom(Date dateFrom){
            this.dateFrom = dateFrom;
            return this;
        }

        public OrderBuilder dateTo(Date dateTo){
            this.dateTo = dateTo;
            return this;
        }

        public OrderBuilder accepted(int accepted){
            this.accepted = accepted;
            return this;
        }

        public OrderBuilder client(User client){
            this.client = client;
            return this;
        }

        public OrderBuilder apartments(List<Apartment> apartments){
            this.apartments = apartments;
            return this;
        }

        public Order build(){
            return new Order(this);
        }

    }
}
