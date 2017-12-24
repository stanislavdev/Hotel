package model;

import java.util.Objects;

public class Apartment {
    private int id;
    private int numberOfRooms;
    private ApartmentType apartmentType;
    private int price;

    private Apartment(ApartmentBuilder apartmentBuilder) {
        this.id = apartmentBuilder.id;
        this.numberOfRooms = apartmentBuilder.numberOfRooms;
        this.apartmentType = apartmentBuilder.apartmentType;
        this.price = apartmentBuilder.price;
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Apartment apartment = (Apartment) o;
        return id == apartment.id &&
                numberOfRooms == apartment.numberOfRooms &&
                price == apartment.price &&
                apartmentType == apartment.apartmentType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, numberOfRooms, apartmentType, price);
    }

    public static class ApartmentBuilder{
        private int id;
        private int numberOfRooms;
        private ApartmentType apartmentType;
        private int price;

        public ApartmentBuilder id(int id){
            this.id = id;
            return this;
        }

        public ApartmentBuilder numberOfRooms(int numberOfRooms){
            this.numberOfRooms = numberOfRooms;
            return this;
        }

        public ApartmentBuilder apartmentType(String apartmentType){
            this.apartmentType = ApartmentType.valueOf(apartmentType);
            return this;
        }

        public ApartmentBuilder price(int price){
            this.price = price;
            return this;
        }

        public Apartment build(){
            return new Apartment(this);
        }
    }
}
