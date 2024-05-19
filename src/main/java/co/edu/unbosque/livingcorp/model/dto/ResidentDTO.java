package co.edu.unbosque.livingcorp.model.dto;

public class ResidentDTO {

    private int residentId;
    private PropertyDTO propertyId;
    private UserDTO userName;
    private boolean isOwner;

    public ResidentDTO() {
        propertyId = new PropertyDTO();
        userName = new UserDTO();
    }

    public ResidentDTO(int residentId, PropertyDTO propertyId, UserDTO userName, boolean isOwner) {
        this.residentId = residentId;
        this.propertyId = propertyId;
        this.userName = userName;
        this.isOwner = isOwner;
    }

    public ResidentDTO(PropertyDTO propertyId, UserDTO userName, boolean isOwner) {
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

    public PropertyDTO getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(PropertyDTO propertyId) {
        this.propertyId = propertyId;
    }

    public UserDTO getUserName() {
        return userName;
    }

    public void setUserName(UserDTO userName) {
        this.userName = userName;
    }

    public boolean isOwner() {
        return isOwner;
    }

    public void setOwner(boolean owner) {
        isOwner = owner;
    }
}
