package co.edu.unbosque.livingcorp.model.dto;

import java.time.LocalDateTime;

public class ResourceBookingDTO {

    private int bookingId;
    private UserDTO userName;
    private PropertyResourceDTO propertyResourceId;
    private LocalDateTime bookingDateTime;
    private LocalDateTime bookingStartDate;
    private LocalDateTime bookingEndDate;
    private double bookingCost;
    private boolean paymentComplete;

    public ResourceBookingDTO(int bookingId, UserDTO userName, PropertyResourceDTO propertyResourceId, LocalDateTime bookingDateTime, LocalDateTime bookingStartDate, LocalDateTime bookingEndDate, double bookingCost, boolean paymentComplete) {
        this.bookingId = bookingId;
        this.userName = userName;
        this.propertyResourceId = propertyResourceId;
        this.bookingDateTime = bookingDateTime;
        this.bookingStartDate = bookingStartDate;
        this.bookingEndDate = bookingEndDate;
        this.bookingCost = bookingCost;
        this.paymentComplete = paymentComplete;
    }

    public ResourceBookingDTO() {
        userName = new UserDTO();
        propertyResourceId = new PropertyResourceDTO();
    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public UserDTO getUserName() {
        return userName;
    }

    public void setUserName(UserDTO userName) {
        this.userName = userName;
    }

    public PropertyResourceDTO getPropertyResourceId() {
        return propertyResourceId;
    }

    public void setPropertyResourceId(PropertyResourceDTO propertyResourceId) {
        this.propertyResourceId = propertyResourceId;
    }

    public LocalDateTime getBookingDateTime() {
        return bookingDateTime;
    }

    public void setBookingDateTime(LocalDateTime bookingDateTime) {
        this.bookingDateTime = bookingDateTime;
    }

    public LocalDateTime getBookingStartDate() {
        return bookingStartDate;
    }

    public void setBookingStartDate(LocalDateTime bookingStartDate) {
        this.bookingStartDate = bookingStartDate;
    }

    public LocalDateTime getBookingEndDate() {
        return bookingEndDate;
    }

    public void setBookingEndDate(LocalDateTime bookingEndDate) {
        this.bookingEndDate = bookingEndDate;
    }

    public double getBookingCost() {
        return bookingCost;
    }

    public void setBookingCost(double bookingCost) {
        this.bookingCost = bookingCost;
    }

    public boolean isPaymentComplete() {
        return paymentComplete;
    }

    public void setPaymentComplete(boolean paymentComplete) {
        this.paymentComplete = paymentComplete;
    }
}
