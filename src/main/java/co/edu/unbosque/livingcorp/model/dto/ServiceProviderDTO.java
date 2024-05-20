package co.edu.unbosque.livingcorp.model.dto;

import com.google.gson.annotations.SerializedName;

public class ServiceProviderDTO {

    @SerializedName("providerId")
    private int providerId;
    @SerializedName("providerEmail")
    private String providerEmail;
    @SerializedName("serviceDescription")
    private String serviceDescription;
    @SerializedName("serviceType")
    private String serviceType;
    @SerializedName("servicePrice")
    private double servicePrice;

    public ServiceProviderDTO(int providerId, String providerEmail, String serviceDescription, String serviceType, double servicePrice) {
        this.providerId = providerId;
        this.providerEmail = providerEmail;
        this.serviceDescription = serviceDescription;
        this.serviceType = serviceType;
        this.servicePrice = servicePrice;
    }

    public ServiceProviderDTO() {
    }

    public int getProviderId() {
        return providerId;
    }

    public void setProviderId(int providerId) {
        this.providerId = providerId;
    }

    public String getProviderEmail() {
        return providerEmail;
    }

    public void setProviderEmail(String providerEmail) {
        this.providerEmail = providerEmail;
    }

    public String getServiceDescription() {
        return serviceDescription;
    }

    public void setServiceDescription(String serviceDescription) {
        this.serviceDescription = serviceDescription;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public double getServicePrice() {
        return servicePrice;
    }

    public void setServicePrice(double servicePrice) {
        this.servicePrice = servicePrice;
    }
}
