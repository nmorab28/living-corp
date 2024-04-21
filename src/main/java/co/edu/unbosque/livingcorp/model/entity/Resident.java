package co.edu.unbosque.livingcorp.model.entity;

import jakarta.persistence.*;

@Entity
@Table (name = "PROPERTY_RESIDENTS")
public class Resident {

    @Id
    @Column(name = "PROPERTY_RESIDENT_ID")
    private int residentId;

    @Column(name = "PROPERTY_ID")
    private Property propertyId;

    @Column(name = "USER_NAME")
    private User userName;

    @Column(name = "IS_OWNER")
    private boolean isOwner;

    public Resident() {}

    public Resident(int residentId, Property propertyId, User userName, boolean isOwner) {
        this.residentId = residentId;
        this.propertyId = propertyId;
        this.userName = userName;
        this.isOwner = isOwner;
    }

    public int getResidentId() {
        return residentId;
    }

    public void setResidentId(int residentId) {
        this.residentId = residentId;
    }

    public Property getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(Property propertyId) {
        this.propertyId = propertyId;
    }

    public User getUserName() {
        return userName;
    }

    public void setUserName(User userName) {
        this.userName = userName;
    }

    public boolean isOwner() {
        return isOwner;
    }

    public void setOwner(boolean owner) {
        isOwner = owner;
    }
}
