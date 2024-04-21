package co.edu.unbosque.livingcorp.model.dto;

import java.time.LocalDateTime;

public class ServiceRFQDTO {

    private int rfqId;
    private LocalDateTime rfqDateTime;
    private UserDTO userName;
    private PropertyDTO propertyId;
    private ServiceProviderDTO svcProviderId;
    private String requestDescription;

    public ServiceRFQDTO(int rfqId, LocalDateTime rfqDateTime, UserDTO userName, PropertyDTO propertyId, ServiceProviderDTO svcProviderId, String requestDescription) {
        this.rfqId = rfqId;
        this.rfqDateTime = rfqDateTime;
        this.userName = userName;
        this.propertyId = propertyId;
        this.svcProviderId = svcProviderId;
        this.requestDescription = requestDescription;
    }

    public ServiceRFQDTO() {
    }

    public int getRfqId() {
        return rfqId;
    }

    public void setRfqId(int rfqId) {
        this.rfqId = rfqId;
    }

    public LocalDateTime getRfqDateTime() {
        return rfqDateTime;
    }

    public void setRfqDateTime(LocalDateTime rfqDateTime) {
        this.rfqDateTime = rfqDateTime;
    }

    public UserDTO getUserName() {
        return userName;
    }

    public void setUserName(UserDTO userName) {
        this.userName = userName;
    }

    public PropertyDTO getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(PropertyDTO propertyId) {
        this.propertyId = propertyId;
    }

    public ServiceProviderDTO getSvcProviderId() {
        return svcProviderId;
    }

    public void setSvcProviderId(ServiceProviderDTO svcProviderId) {
        this.svcProviderId = svcProviderId;
    }

    public String getRequestDescription() {
        return requestDescription;
    }

    public void setRequestDescription(String requestDescription) {
        this.requestDescription = requestDescription;
    }
}