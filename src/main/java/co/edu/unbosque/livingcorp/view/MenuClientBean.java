package co.edu.unbosque.livingcorp.view;

import co.edu.unbosque.livingcorp.model.dto.*;
import co.edu.unbosque.livingcorp.model.exception.*;
import co.edu.unbosque.livingcorp.service.EmailService;
import co.edu.unbosque.livingcorp.service.ResourceBookingService;
import co.edu.unbosque.livingcorp.service.PropertyResourceService;
import co.edu.unbosque.livingcorp.service.UserService;
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

@Named(value = "menuClientBean")
@ViewScoped
public class MenuClientBean implements Serializable {
    private static final long serialVersionUID = 1L;

    @Inject
    private UserService userService;
    @Inject
    private PropertyResourceService propertyResourceService;
    @Inject
    private ResourceBookingService resourceBookingService;
    @Inject
    private EmailService emailService;

    private UserDTO user;
    private List<PropertyResourceDTO> residentPropertyResources;
    private ResourceBookingDTO resourceBooking;
    private String payment;

    @PostConstruct
    public void init(){
        resourceBooking = new ResourceBookingDTO();
        loadSession();
        loadPropertyResources();
    }

    private void loadPropertyResources(){
        try {
            user = userService.findUser(user.getUserName());
            List<PropertyDTO> userProperties = new ArrayList<>();
            for (ResidentDTO resident : user.getResidents()) {
                userProperties.add(resident.getPropertyId());
            }
            List<PropertyResourceDTO> allPropertyResources = propertyResourceService.showPropertyResources();
            residentPropertyResources = new ArrayList<>();
            for (PropertyDTO property : userProperties) {
                for (PropertyResourceDTO propertyResource : allPropertyResources) {
                    if (propertyResource.getProId().getPropertyId() == property.getPropertyId()) {
                        residentPropertyResources.add(propertyResource);
                    }
                }
            }
        } catch (ObjectNotFoundException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "El Usuario no fue encontrado"));
        }
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

    public String goToPay(){
        try {
            resourceBooking = resourceBookingService.calculatePayment(resourceBooking);
            HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
            session.setAttribute("resourceBooking", resourceBooking);
            return "payment.xhtml";
        } catch (PRInvalidRequirementsException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage()));
        }
        return "index_user.xhtml";
    }

    public String reserveResource(){
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        ResourceBookingDTO resourceBooking = (ResourceBookingDTO) session.getAttribute("resourceBooking");
        if (resourceBooking != null) {
            try {
                resourceBooking = resourceBookingService.payingForResource(resourceBooking);
                resourceBooking = resourceBookingService.createResourceBooking(resourceBooking, user);
                propertyResourceService.updatePropertyResource(resourceBooking.getPropertyResourceId());
                emailService.sendResourceBookingNotification(user, resourceBooking);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Recurso Reservado"));
            } catch (RepeatedObjectException | NonpaymentException | PRInvalidRequirementsException e) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage()));
            } catch (ObjectNotFoundException e) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage()));
            } catch (IncompleteNotificationException e) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage()));
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se encontró la reserva."));
        }
        return "index_user.xhtml";
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public List<PropertyResourceDTO> getResidentPropertyResources() {
        return residentPropertyResources;
    }

    public void setResidentPropertyResources(List<PropertyResourceDTO> residentPropertyResources) {
        this.residentPropertyResources = residentPropertyResources;
    }

    public ResourceBookingDTO getResourceBooking() {
        return resourceBooking;
    }

    public void setResourceBooking(ResourceBookingDTO resourceBooking) {
        this.resourceBooking = resourceBooking;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }
}
