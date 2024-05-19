package co.edu.unbosque.livingcorp.view;

import co.edu.unbosque.livingcorp.model.dto.PropertyDTO;
import co.edu.unbosque.livingcorp.model.dto.PropertyResourceDTO;
import co.edu.unbosque.livingcorp.model.dto.ResidentDTO;
import co.edu.unbosque.livingcorp.model.dto.ResourceBookingDTO;
import co.edu.unbosque.livingcorp.service.PropertyResourceService;
import co.edu.unbosque.livingcorp.service.PropertyService;
import co.edu.unbosque.livingcorp.service.ResidenceService;
import co.edu.unbosque.livingcorp.service.ResourceBookingService;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.List;

@Named(value = "statisticsBean")
@RequestScoped
public class StatisticsBean implements Serializable {
    private static final long serialVersionUID = 1L;

    @Inject
    private PropertyService propertyService;
    @Inject
    private ResidenceService residenceService;
    @Inject
    private PropertyResourceService propertyResourceService;
    @Inject
    private ResourceBookingService resourceBookingService;

    private List<PropertyDTO> properties;
    private List<ResidentDTO> residents;
    private List<PropertyResourceDTO> propResources;
    private List<ResourceBookingDTO> reservedResources;
    private int reservationNum;

    @PostConstruct
    public void init() {
        properties = propertyService.showProperties();
        residents = residenceService.showResidents();
        propResources = propertyResourceService.showPropertyResources();
        reservedResources = resourceBookingService.showReservedResources();
        reservationNum = reservedResources.size();

    }

    public int getOwnerResidentsCountByProperty(PropertyDTO property) {
        int count = 0;
        for (ResidentDTO resident : residents) {
            if (resident.getPropertyId().getPropertyId() == property.getPropertyId() && resident.isOwner()) {
                count++;
            }
        }
        return count;
    }

    public int getTenantResidentsCountByProperty(PropertyDTO property) {
        int count = 0;
        for (ResidentDTO resident : residents) {
            if (resident.getPropertyId().getPropertyId() == property.getPropertyId() && !resident.isOwner()) {
                count++;
            }
        }
        return count;
    }

    public int getReservationCountForResource(PropertyResourceDTO propResource) {
        int count = 0;
        for (ResourceBookingDTO booking : reservedResources) {
            if (booking.getPropertyResourceId().getProResId() == propResource.getProResId()) {
                count++;
            }
        }
        return count;
    }

    public int getTotalReservationTimeForResource(PropertyResourceDTO propResource) {
        int totalDuration = 0;
        for (ResourceBookingDTO booking : reservedResources) {
            if (booking.getPropertyResourceId().getProResId() == propResource.getProResId()) {
                totalDuration += resourceBookingService.getDuration(booking);
            }
        }
        return totalDuration;
    }

    public List<PropertyDTO> getProperties() {
        return properties;
    }

    public void setProperties(List<PropertyDTO> properties) {
        this.properties = properties;
    }

    public List<ResidentDTO> getResidents() {
        return residents;
    }

    public void setResidents(List<ResidentDTO> residents) {
        this.residents = residents;
    }

    public List<PropertyResourceDTO> getPropResources() {
        return propResources;
    }

    public void setPropResources(List<PropertyResourceDTO> propResources) {
        this.propResources = propResources;
    }

    public List<ResourceBookingDTO> getReservedResources() {
        return reservedResources;
    }

    public void setReservedResources(List<ResourceBookingDTO> reservedResources) {
        this.reservedResources = reservedResources;
    }

    public int getReservationNum() {
        return reservationNum;
    }

    public void setReservationNum(int reservationNum) {
        this.reservationNum = reservationNum;
    }
}