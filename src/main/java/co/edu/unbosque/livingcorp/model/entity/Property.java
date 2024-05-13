package co.edu.unbosque.livingcorp.model.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table (name = "PROPERTIES")
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PROPERTY_ID")
    private int propertyId;

    @Column (name = "PROPERTY_NAME")
    private String propertyName;

    @Column (name = "PROPERTY_CITY")
    private String propertyCity;

    @Column (name = "PROPERTY_ADDRESS")
    private String propertyAddress;

    @Column (name="PROPERTY_AREA")
    private int propertyArea;

    @Column (name="PROPERTY_PRICE")
    private double propertyPrice;

    @Column (name="PROPERTY_ROOMS")
    private int propertyRooms;

    @Column (name="PROPERTY_BATHROOMS")
    private int propertyBathrooms;

    @Column (name="PROPERTY_DESCRIPCION")
    private String propertyDescription;

    @ManyToOne
    @JoinColumn(name="PROPERTY_ADMIN")
    private User propertyAdmin;

    @Column (name="IS_AVAILABLE_FOR_RENT")
    private boolean isAvailableForRent;

    @Column (name="IS_AVAILABLE_FOR_SALE")
    private boolean isAvailableForSale;

    @OneToMany(mappedBy = "propertyId", fetch = FetchType.LAZY)
    private List<Resident> residents;

    public Property() {
    }

    public Property(int propertyId, String propertyName, String propertyCity, String propertyAddress, int propertyArea, double propertyPrice, int propertyRooms, int propertyBathrooms, String propertyDescription, User propertyAdmin, boolean isAvailableForRent, boolean isAvailableForSale) {
        this.propertyId = propertyId;
        this.propertyName = propertyName;
        this.propertyCity = propertyCity;
        this.propertyAddress = propertyAddress;
        this.propertyArea = propertyArea;
        this.propertyPrice = propertyPrice;
        this.propertyRooms = propertyRooms;
        this.propertyBathrooms = propertyBathrooms;
        this.propertyDescription = propertyDescription;
        this.propertyAdmin = propertyAdmin;
        this.isAvailableForRent = isAvailableForRent;
        this.isAvailableForSale = isAvailableForSale;
    }

    public Property(int propertyId, String propertyName, String propertyCity, String propertyAddress, int propertyArea, double propertyPrice, int propertyRooms, int propertyBathrooms, String propertyDescription, User propertyAdmin, boolean isAvailableForRent, boolean isAvailableForSale, List<Resident> residents) {
        this.propertyId = propertyId;
        this.propertyName = propertyName;
        this.propertyCity = propertyCity;
        this.propertyAddress = propertyAddress;
        this.propertyArea = propertyArea;
        this.propertyPrice = propertyPrice;
        this.propertyRooms = propertyRooms;
        this.propertyBathrooms = propertyBathrooms;
        this.propertyDescription = propertyDescription;
        this.propertyAdmin = propertyAdmin;
        this.isAvailableForRent = isAvailableForRent;
        this.isAvailableForSale = isAvailableForSale;
        this.residents = residents;
    }

    public int getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(int propertyId) {
        this.propertyId = propertyId;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public String getPropertyCity() {
        return propertyCity;
    }

    public void setPropertyCity(String propertyCity) {
        this.propertyCity = propertyCity;
    }

    public String getPropertyAddress() {
        return propertyAddress;
    }

    public void setPropertyAddress(String propertyAddress) {
        this.propertyAddress = propertyAddress;
    }

    public int getPropertyArea() {
        return propertyArea;
    }

    public void setPropertyArea(int propertyArea) {
        this.propertyArea = propertyArea;
    }

    public double getPropertyPrice() {
        return propertyPrice;
    }

    public void setPropertyPrice(double propertyPrice) {
        this.propertyPrice = propertyPrice;
    }

    public int getPropertyRooms() {
        return propertyRooms;
    }

    public void setPropertyRooms(int propertyRooms) {
        this.propertyRooms = propertyRooms;
    }

    public int getPropertyBathrooms() {
        return propertyBathrooms;
    }

    public void setPropertyBathrooms(int propertyBathrooms) {
        this.propertyBathrooms = propertyBathrooms;
    }

    public String getPropertyDescription() {
        return propertyDescription;
    }

    public void setPropertyDescription(String propertyDescription) {
        this.propertyDescription = propertyDescription;
    }

    public User getPropertyAdmin() {
        return propertyAdmin;
    }

    public void setPropertyAdmin(User propertyAdmin) {
        this.propertyAdmin = propertyAdmin;
    }

    public boolean isAvailableForRent() {
        return isAvailableForRent;
    }

    public void setAvailableForRent(boolean availableForRent) {
        isAvailableForRent = availableForRent;
    }

    public boolean isAvailableForSale() {
        return isAvailableForSale;
    }

    public void setAvailableForSale(boolean availableForSale) {
        isAvailableForSale = availableForSale;
    }

    public List<Resident> getResidents() {
        return residents;
    }

    public void setResidents(List<Resident> residents) {
        this.residents = residents;
    }
}
