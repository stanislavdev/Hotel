package model.entities;

public class Bill {
    private int id;
    private User admin;
    private Order order;
    private int price;
    private int isPaid;
    private int adminId;
    private int orderId;

    private Bill(BillBuilder billBuilder) {
        this.id = billBuilder.id;
        this.admin = billBuilder.admin;
        this.order = billBuilder.order;
        this.price = billBuilder.price;
        this.isPaid = billBuilder.isPaid;
        this.adminId = billBuilder.adminId;
        this.orderId = billBuilder.orderId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getAdmin() {
        return admin;
    }

    public void setAdmin(User admin) {
        this.admin = admin;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getIsPaid() {
        return isPaid;
    }

    public void setIsPaid(int isPaid) {
        this.isPaid = isPaid;
    }

    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public static class BillBuilder {
        private int id;
        private User admin;
        private Order order;
        private int price;
        private int isPaid;
        private int adminId;
        private int orderId;

        public BillBuilder id(int id) {
            this.id = id;
            return this;
        }

        public BillBuilder adminId(int adminId) {
            this.adminId = adminId;
            return this;
        }

        public BillBuilder orderId(int orderId) {
            this.orderId = orderId;
            return this;
        }

        public BillBuilder isPaid(int isPaid){
            this.isPaid = isPaid;
            return this;
        }

        public BillBuilder admin(User admin) {
            this.admin = admin;
            return this;
        }

        public BillBuilder order(Order order) {
            this.order = order;
            return this;
        }

        public BillBuilder price(int price) {
            this.price = price;
            return this;
        }

        public Bill build(){
            return new Bill(this);
        }
    }
}
