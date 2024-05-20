package co.edu.unbosque.livingcorp.view;

import co.edu.unbosque.livingcorp.model.dto.PropertyDTO;
import co.edu.unbosque.livingcorp.model.exception.ObjectNotFoundException;
import co.edu.unbosque.livingcorp.model.exception.RepeatedObjectException;
import co.edu.unbosque.livingcorp.service.PropertyService;
import co.edu.unbosque.livingcorp.service.UserService;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named(value = "propertyBean")
@RequestScoped
public class PropertyBean implements Serializable {
    private static final long serialVersionUID = 1L;

    private PropertyDTO property;
    private List<PropertyDTO> registredProperties;
    @Inject
    private PropertyService propertyService;

    @Inject
    private UserService userService;
    private List<String> selectedAdmins;

    @PostConstruct
    public void init(){
        property = new PropertyDTO();
        registredProperties = propertyService.showProperties();
        loadAdmins();
    }

    public void createProperty(){
        try {
            propertyService.createProperty(property, userService.findUser(property.getPropertyAdmin().getUserName()));
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Propiedad creada"));
            registredProperties = propertyService.showProperties();
        } catch (RepeatedObjectException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage()));
        } catch (ObjectNotFoundException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning", e.getMessage()));
        }
    }

    private void loadAdmins(){
        selectedAdmins = new ArrayList<String>();
        userService.showAdmins().forEach(admin -> selectedAdmins.add(admin.getUserName()));
    }

    public PropertyDTO getProperty() {
        return property;
    }

    public void setProperty(PropertyDTO property) {
        this.property = property;
    }

    public List<PropertyDTO> getRegistredProperties() {
        return registredProperties;
    }

    public void setRegistredProperties(List<PropertyDTO> registredProperties) {
        this.registredProperties = registredProperties;
    }

    public List<String> getSelectedAdmins() {
        return selectedAdmins;
    }

    public void setSelectedAdmins(List<String> selectedAdmins) {
        this.selectedAdmins = selectedAdmins;
    }
}
