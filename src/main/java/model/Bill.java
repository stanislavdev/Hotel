package model;

public class Bill {
    private int id;
    private User admin;
    private Order order;
    private int price;

    private Bill(BillBuilder billBuilder) {
        this.id = billBuilder.id;
        this.admin = billBuilder.admin;
        this.order = billBuilder.order;
        this.price = billBuilder.price;
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

    public static class BillBuilder {
        private int id;
        private User admin;
        private Order order;
        private int price;

        public BillBuilder id(int id) {
            this.id = id;
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
