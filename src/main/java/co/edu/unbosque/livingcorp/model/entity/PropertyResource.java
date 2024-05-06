package co.edu.unbosque.livingcorp.model.entity;

import jakarta.persistence.*;

@Entity
@Table(name="PROPERTY_RESOURCES")
public class PropertyResource {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PROP_RES_ID")
    private int proResId;

    @ManyToOne
    @JoinColumn(name = "RESOURCE_ID")
    private Resource resId;

    @ManyToOne
    @JoinColumn(name = "PROPERTY_ID")
    private Property proId;

    @Column(name = "RESOURCE_MIN_PRICE")
    private double minPrice;

    @Column(name = "RESOURCE_MIN_TIME_HRS")
    private int minTimeH;

    @Column(name = "RESOURCE_AVAILABILITY")
    private String availabily;

    @Column(name = "RESOURCE_CAPACITY")
    private int capacity;

    @Column(name = "BOOKING_EMAIL")
    private String bookEmail;

    public PropertyResource(int proResId, Resource resId, Property proId, double minPrice, int minTimeH, String availabily, int capacity, String bookEmail) {
        this.proResId = proResId;
        this.resId = resId;
        this.proId = proId;
        this.minPrice = minPrice;
        this.minTimeH = minTimeH;
        this.availabily = availabily;
        this.capacity = capacity;
        this.bookEmail = bookEmail;
    }

    public PropertyResource() {}

    public int getProResId() {
        return proResId;
    }

    public void setProResId(int proResId) {
        this.proResId = proResId;
    }

    public Resource getResId() {
        return resId;
    }

    public void setResId(Resource resId) {
        this.resId = resId;
    }

    public Property getProId() {
        return proId;
    }

    public void setProId(Property proId) {
        this.proId = proId;
    }

    public double getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(double minPrice) {
        this.minPrice = minPrice;
    }

    public int getMinTimeH() {
        return minTimeH;
    }

    public void setMinTimeH(int minTimeH) {
        this.minTimeH = minTimeH;
    }

    public String getAvailabily() {
        return availabily;
    }

    public void setAvailabily(String availabily) {
        this.availabily = availabily;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getBookEmail() {
        return bookEmail;
    }

    public void setBookEmail(String bookEmail) {
        this.bookEmail = bookEmail;
    }
}
