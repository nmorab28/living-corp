package co.edu.unbosque.livingcorp.model.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "SERVICE_RFQ")
public class ServiceQuotation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RFQ_ID")
    private int rfqId;

    @Column(name = "RFQ_DATETIME")
    private LocalDateTime rfqDateTime;

    @ManyToOne
    @JoinColumn(name = "USER_NAME")
    private User userName;

    @ManyToOne
    @JoinColumn(name = "PROPERTY_ID")
    private Property propertyId;

    @ManyToOne
    @JoinColumn(name = "SVC_PROVIDER_ID")
    private ServiceProvider svcProviderId;

    @Column(name = "REQUEST_DESCRIPTION")
    private String requestDescription;

    public ServiceQuotation(int rfqId, LocalDateTime rfqDateTime, User userName, Property propertyId, ServiceProvider svcProviderId, String requestDescription) {
        this.rfqId = rfqId;
        this.rfqDateTime = rfqDateTime;
        this.userName = userName;
        this.propertyId = propertyId;
        this.svcProviderId = svcProviderId;
        this.requestDescription = requestDescription;
    }

    public ServiceQuotation() {
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

    public User getUserName() {
        return userName;
    }

    public void setUserName(User userName) {
        this.userName = userName;
    }

    public Property getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(Property propertyId) {
        this.propertyId = propertyId;
    }

    public ServiceProvider getSvcProviderId() {
        return svcProviderId;
    }

    public void setSvcProviderId(ServiceProvider svcProviderId) {
        this.svcProviderId = svcProviderId;
    }

    public String getRequestDescription() {
        return requestDescription;
    }

    public void setRequestDescription(String requestDescription) {
        this.requestDescription = requestDescription;
    }
}
