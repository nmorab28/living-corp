package co.edu.unbosque.livingcorp.model.entity;


import jakarta.persistence.*;

@Entity
@Table
public class Resource {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RESOURCE_ID")
    private int resourceId;

    @Column(name = "RESOURCE_DESCRIPCION")
    private String resourceDescription;

    @Column(name = "RESOURCE_TYPE")
    private String resourceType;

    public Resource() {}

    public Resource(int resourceId, String resourceDescription, String resourceType) {
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
