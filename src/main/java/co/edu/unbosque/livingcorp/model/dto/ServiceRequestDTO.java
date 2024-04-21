package co.edu.unbosque.livingcorp.model.dto;

import java.time.LocalDateTime;

public class ServiceRequestDTO {

    private int rqstId;
    private LocalDateTime rqstDateTime;
    private UserDTO userName;
    private PropertyDTO propertyId;
    private ServiceProviderDTO svcProviderId;
    private String requestDescription;
    private LocalDateTime svcDateTime;

    public ServiceRequestDTO(int rqstId, LocalDateTime rqstDateTime, UserDTO userName, PropertyDTO propertyId, ServiceProviderDTO svcProviderId, String requestDescription, LocalDateTime svcDateTime) {
        this.rqstId = rqstId;
        this.rqstDateTime = rqstDateTime;
        this.userName = userName;
        this.propertyId = propertyId;
        this.svcProviderId = svcProviderId;
        this.requestDescription = requestDescription;
        this.svcDateTime = svcDateTime;
    }

    public ServiceRequestDTO() {
    }

    public int getRqstId() {
        return rqstId;
    }

    public void setRqstId(int rqstId) {
        this.rqstId = rqstId;
    }

    public LocalDateTime getRqstDateTime() {
        return rqstDateTime;
    }

    public void setRqstDateTime(LocalDateTime rqstDateTime) {
        this.rqstDateTime = rqstDateTime;
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

    public LocalDateTime getSvcDateTime() {
        return svcDateTime;
    }

    public void setSvcDateTime(LocalDateTime svcDateTime) {
        this.svcDateTime = svcDateTime;
    }
}
