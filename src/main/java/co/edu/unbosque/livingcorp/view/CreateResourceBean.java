package co.edu.unbosque.livingcorp.view;

import co.edu.unbosque.livingcorp.model.dto.ResourceDTO;
import co.edu.unbosque.livingcorp.model.exception.RepeatedObjectException;
import co.edu.unbosque.livingcorp.service.ResourceService;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.apache.log4j.Logger;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named(value = "createResourceBean")
@RequestScoped
public class CreateResourceBean implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(CreateResourceBean.class);

    @Inject
    private ResourceService resourceService;

    private ResourceDTO resource;

    private List<String> resourceType;

    @PostConstruct
    public void init(){
        resource = new ResourceDTO();
        loadResourceType();
    }

    private void loadResourceType(){
        resourceType = new ArrayList<String>();
        resourceType.add("Gimnasio");
        resourceType.add("Terraza BBQ");
        resourceType.add("Salon de Eventos");
        resourceType.add("Piscina");
    }

    public void createResource(){
        try {
            resourceService.createResource(resource);
        } catch (RepeatedObjectException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Este Recurso ya existe"));
        }
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Recurso Creado"));
    }

    public ResourceDTO getResource() {
        return resource;
    }

    public void setResource(ResourceDTO resource) {
        this.resource = resource;
    }

    public List<String> getResourceType() {
        return resourceType;
    }

    public void setResourceType(List<String> resourceType) {
        this.resourceType = resourceType;
    }
}
