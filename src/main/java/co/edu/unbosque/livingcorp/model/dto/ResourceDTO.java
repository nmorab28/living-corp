package co.edu.unbosque.livingcorp.model.dto;

public class ResourceDTO {

    private int resourceId;
    private String resourceDescription;
    private String resourceType;

    public ResourceDTO() {}

    public ResourceDTO(int resourceId, String resourceDescription, String resourceType) {
        this.resourceId = resourceId;
        this.resourceDescription = resourceDescription;
        this.resourceType = resourceType;
    }

    public int getResourceId() {
        return resourceId;
    }

    public void setResourceId(int resourceId) {
        this.resourceId = resourceId;
    }

    public String getResourceDescription() {
        return resourceDescription;
    }

    public void setResourceDescription(String resourceDescription) {
        this.resourceDescription = resourceDescription;
    }

    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }
}
