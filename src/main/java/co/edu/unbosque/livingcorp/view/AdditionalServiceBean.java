package co.edu.unbosque.livingcorp.view;

import co.edu.unbosque.livingcorp.model.dto.*;
import co.edu.unbosque.livingcorp.model.exception.IncompleteNotificationException;
import co.edu.unbosque.livingcorp.model.exception.ObjectNotFoundException;
import co.edu.unbosque.livingcorp.model.exception.RepeatedObjectException;
import co.edu.unbosque.livingcorp.service.*;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpSession;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named(value = "additionalServiceBean")
@ViewScoped
public class AdditionalServiceBean implements Serializable {
    private static final long serialVersionUID = 1L;

    @Inject
    private UserService userService;
    @Inject
    private PropertyService propertyService;
    @Inject
    private ProviderApiService providerApiService;
    @Inject
    private CalculateQuotationService calculateQuotationService;
    @Inject
    private CalculateRequestService calculateRequestService;
    @Inject
    private EmailService emailService;

    private UserDTO user;

    private List<ServiceProviderDTO> providers;

    private ServiceQuotationDTO serviceQuotation;
    private ServiceRequestDTO serviceRequest;

    private List<PropertyDTO> properties;
    private List<String> residentProperties;
    private String selectedProperty;

    @PostConstruct
    public void init(){
        loadSession();
        providers = providerApiService.loadProviders();
        serviceQuotation = new ServiceQuotationDTO();
        serviceRequest = new ServiceRequestDTO();
        loadProperties();
    }

    private void loadSession(){
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        String userId = (String) session.getAttribute("userName");
        try {
            if(!userId.isEmpty()){
                user = userService.findUser(userId);
            }else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "El Usuario no fue encontrado"));
            }
        } catch (ObjectNotFoundException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage()));
        }
    }

    private void loadProperties(){
        properties = new ArrayList<PropertyDTO>();
        user.getResidents().forEach(resident -> properties.add(resident.getPropertyId()));

        residentProperties = new ArrayList<String>();
        properties.forEach(property -> residentProperties.add(property.getPropertyId() + " - " + property.getPropertyName()));
    }

    public void sendQouteService(){
        try {
            serviceQuotation = calculateQuotationService.createServiceQuotation(serviceQuotation, user, propertyService.searchProperty(propertyService.getSelecctedPropertyId(selectedProperty)));
            emailService.sendQuotationNotificationToProvider(user, serviceQuotation);
            emailService.sendQuotationNotificationToResident(user, serviceQuotation);
        } catch (RepeatedObjectException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage()));
        } catch (ObjectNotFoundException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage()));
        } catch (IncompleteNotificationException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage()));
        }
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Servicio Cotizado"));
    }

    public void sendRequestService(){
        try {
            serviceRequest = calculateRequestService.createServiceRequest(serviceRequest, user, propertyService.searchProperty(propertyService.getSelecctedPropertyId(selectedProperty)));
            emailService.sendServiceConfirmationToResident(user, serviceRequest);
        } catch (RepeatedObjectException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage()));
        } catch (ObjectNotFoundException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage()));
        } catch (IncompleteNotificationException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage()));
        }
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Servicio Solicitado"));
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public List<ServiceProviderDTO> getProviders() {
        return providers;
    }

    public void setProviders(List<ServiceProviderDTO> providers) {
        this.providers = providers;
    }

    public ServiceQuotationDTO getServiceQuotation() {
        return serviceQuotation;
    }

    public void setServiceQuotation(ServiceQuotationDTO serviceQuotation) {
        this.serviceQuotation = serviceQuotation;
    }

    public ServiceRequestDTO getServiceRequest() {
        return serviceRequest;
    }

    public void setServiceRequest(ServiceRequestDTO serviceRequest) {
        this.serviceRequest = serviceRequest;
    }

    public List<PropertyDTO> getProperties() {
        return properties;
    }

    public void setProperties(List<PropertyDTO> properties) {
        this.properties = properties;
    }

    public List<String> getResidentProperties() {
        return residentProperties;
    }

    public void setResidentProperties(List<String> residentProperties) {
        this.residentProperties = residentProperties;
    }

    public String getSelectedProperty() {
        return selectedProperty;
    }

    public void setSelectedProperty(String selectedProperty) {
        this.selectedProperty = selectedProperty;
    }
}
