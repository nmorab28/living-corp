package co.edu.unbosque.livingcorp.model.dto;

import java.time.LocalDateTime;

public class VisitorDTO {

    private int appointmentId;
    private String visitorName;
    private String advisorName;
    private LocalDateTime appointmentDateTime;
    private PropertyDTO propertyId;

    public VisitorDTO() {
        propertyId = new PropertyDTO();
    }

    public VisitorDTO(int appointmentId, String visitorName, String advisorName, LocalDateTime appointmentDateTime, PropertyDTO propertyId) {
        this.appointmentId = appointmentId;
        this.visitorName = visitorName;
        this.advisorName = advisorName;
        this.appointmentDateTime = appointmentDateTime;
        this.propertyId = propertyId;
    }

    public int getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    public String getVisitorName() {
        return visitorName;
    }

    public void setVisitorName(String visitorName) {
        this.visitorName = visitorName;
    }

    public String getAdvisorName() {
        return advisorName;
    }

    public void setAdvisorName(String advisorName) {
        this.advisorName = advisorName;
    }

    public LocalDateTime getAppointmentDateTime() {
        return appointmentDateTime;
    }

    public void setAppointmentDateTime(LocalDateTime appointmentDateTime) {
        this.appointmentDateTime = appointmentDateTime;
    }

    public PropertyDTO getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(PropertyDTO propertyId) {
        this.propertyId = propertyId;
    }
}
