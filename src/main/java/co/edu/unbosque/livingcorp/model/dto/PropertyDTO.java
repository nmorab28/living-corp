package co.edu.unbosque.livingcorp.model.dto;

import java.util.List;

public class PropertyDTO {

    private int propertyId;
    private String propertyName;
    private String propertyCity;
    private String propertyAddress;
    private int propertyArea;
    private double propertyPrice;
    private int propertyRooms;
    private int propertyBathrooms;
    private String propertyDescription;
    private UserDTO propertyAdmin;
    private boolean isAvailableForRent;
    private boolean isAvailableForSale;
    private List<ResidentDTO> residents;

    public PropertyDTO() {
        propertyAdmin = new UserDTO();
    }

    public PropertyDTO(int propertyId, String propertyName, String propertyCity, String propertyAddress, int propertyArea, double propertyPrice, int propertyRooms, int propertyBathrooms, String propertyDescription, UserDTO propertyAdmin, boolean isAvailableForRent, boolean isAvailableForSale) {
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

    public PropertyDTO(int propertyId, String propertyName, String propertyCity, String propertyAddress, int propertyArea, double propertyPrice, int propertyRooms, int propertyBathrooms, String propertyDescription, UserDTO propertyAdmin, boolean isAvailableForRent, boolean isAvailableForSale, List<ResidentDTO> residents) {
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

    public UserDTO getPropertyAdmin() {
        return propertyAdmin;
    }

    public void setPropertyAdmin(UserDTO propertyAdmin) {
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

    public List<ResidentDTO> getResidents() {
        return residents;
    }

    public void setResidents(List<ResidentDTO> residents) {
        this.residents = residents;
    }

    @Override
    public String toString() {
        return "PropertyDTO{" +
                "propertyId=" + propertyId +
                ", propertyName='" + propertyName + '\'' +
                ", propertyCity='" + propertyCity + '\'' +
                ", propertyAddress='" + propertyAddress + '\'' +
                ", propertyArea=" + propertyArea +
                ", propertyPrice=" + propertyPrice +
                ", propertyRooms=" + propertyRooms +
                ", propertyBathrooms=" + propertyBathrooms +
                ", propertyDescription='" + propertyDescription + '\'' +
                ", propertyAdmin=" + propertyAdmin +
                ", isAvailableForRent=" + isAvailableForRent +
                ", isAvailableForSale=" + isAvailableForSale +
                '}';
    }
}
