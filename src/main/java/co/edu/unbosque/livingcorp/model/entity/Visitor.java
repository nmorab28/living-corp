package co.edu.unbosque.livingcorp.model.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table (name = "PROPERTY_VISITOR_APPOINTMENT")
public class Visitor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "APPOINTMENT_ID")
    private int appointmentId;

    @Column(name = "VISITOR_NAME")
    private String visitorName;

    @Column(name = "ADVISOR_NAME")
    private String advisorName;

    @Column(name = "APPOINTMENT_DATETIME")
    private LocalDateTime appointmentDateTime;

    @Column(name = "PROPERTY_ID")
    private Property propertyId;

    public Visitor() {}

    public Visitor(int appointmentId, String visitorName, String advisorName, LocalDateTime appointmentDateTime, Property propertyId) {
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

    public Property getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(Property propertyId) {
        this.propertyId = propertyId;
    }
}
