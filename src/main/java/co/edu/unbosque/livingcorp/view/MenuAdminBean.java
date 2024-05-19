package co.edu.unbosque.livingcorp.view;

import co.edu.unbosque.livingcorp.model.dto.PropertyDTO;
import co.edu.unbosque.livingcorp.model.dto.PropertyResourceDTO;
import co.edu.unbosque.livingcorp.model.dto.ResidentDTO;
import co.edu.unbosque.livingcorp.model.dto.UserDTO;
import co.edu.unbosque.livingcorp.model.entity.User;
import co.edu.unbosque.livingcorp.model.exception.IncompleteNotificationException;
import co.edu.unbosque.livingcorp.model.exception.ObjectNotFoundException;
import co.edu.unbosque.livingcorp.model.exception.PRInvalidRequirementsException;
import co.edu.unbosque.livingcorp.model.exception.RepeatedObjectException;
import co.edu.unbosque.livingcorp.service.*;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named(value = "menuAdminBean")
@ViewScoped
public class MenuAdminBean implements Serializable {
    private static final long serialVersionUID = 1L;

    @Inject
    private UserService userService;
    @Inject
    private PropertyService propertyService;
    @Inject
    private ResidenceService residenceService;
    @Inject
    private ResourceService resourceService;
    @Inject
    private PropertyResourceService propResourceService;
    @Inject
    private EmailService emailService;

    private ResidentDTO resident;
    private PropertyResourceDTO propResource;

    private List<PropertyDTO> properties;
    private List<String> availableUsers;
    private List<String> availableResources;
    private List<String> adminsEmail;

    private String resourceSelected;

    @PostConstruct
    public void init(){
        resident = new ResidentDTO();
        propResource = new PropertyResourceDTO();
        properties = propertyService.showProperties();
        loadAvailableUsers();
        loadAvailableResources();
        loadAdminsEmanil();
    }

    private void loadAvailableUsers(){
        availableUsers = new ArrayList<String>();
        userService.showUsers().stream().filter(user -> !user.isPropertyAdmin()).forEach(user -> availableUsers.add(user.getUserName()));
    }

    private void loadAdminsEmanil(){
        adminsEmail = new ArrayList<String>();
        userService.showUsers().stream().filter(UserDTO::isPropertyAdmin).forEach(user -> adminsEmail.add(user.getUserEmail()));
    }

    public void assingResidence(){
        try {
            resident = residenceService.createResidence(resident, userService.findUser(resident.getUserName().getUserName()));
            propertyService.addResidentToProperty(resident);
        } catch (RepeatedObjectException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Este usuario ya es residente de esta propiedad"));
        } catch (ObjectNotFoundException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Este usuario no existe"));
        }
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Residencia Asignada"));

    }

    private void loadAvailableResources(){
        availableResources = new ArrayList<String>();
        resourceService.showResources().forEach(resource -> availableResources.add(resource.getResourceId() + " - " + resource.getResourceType()));
    }

    public void assingResourceToProperty(){
        try {
            propResource = propResourceService.createPropertyResource(propResource, resourceService
                    .searchResource(propResourceService.getResourceId(resourceSelected)));
            emailService.sendResidentsNotification(propResource);
        } catch (RepeatedObjectException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage()));
        } catch (ObjectNotFoundException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage()));
        } catch (PRInvalidRequirementsException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage()));
        } catch (IncompleteNotificationException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage()));
        }
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Recurso Asignado"));
    }

    public ResidentDTO getResident() {
        return resident;
    }

    public void setResident(ResidentDTO resident) {
        this.resident = resident;
    }

    public List<PropertyDTO> getProperties() {
        return properties;
    }

    public void setProperties(List<PropertyDTO> properties) {
        this.properties = properties;
    }

    public List<String> getAvailableUsers() {
        return availableUsers;
    }

    public void setAvailableUsers(List<String> availableUsers) {
        this.availableUsers = availableUsers;
    }

    public PropertyResourceDTO getPropResource() {
        return propResource;
    }

    public void setPropResource(PropertyResourceDTO propResource) {
        this.propResource = propResource;
    }

    public List<String> getAvailableResources() {
        return availableResources;
    }

    public void setAvailableResources(List<String> availableResources) {
        this.availableResources = availableResources;
    }

    public List<String> getAdminsEmail() {
        return adminsEmail;
    }

    public void setAdminsEmail(List<String> adminsEmail) {
        this.adminsEmail = adminsEmail;
    }

    public String getResourceSelected() {
        return resourceSelected;
    }

    public void setResourceSelected(String resourceSelected) {
        this.resourceSelected = resourceSelected;
    }
}
