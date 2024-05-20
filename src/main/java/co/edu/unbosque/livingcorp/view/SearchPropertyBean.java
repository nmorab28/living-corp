package co.edu.unbosque.livingcorp.view;

import co.edu.unbosque.livingcorp.model.dto.PropertyDTO;
import co.edu.unbosque.livingcorp.model.dto.VisitorDTO;
import co.edu.unbosque.livingcorp.model.exception.ObjectNotFoundException;
import co.edu.unbosque.livingcorp.model.exception.RepeatedObjectException;
import co.edu.unbosque.livingcorp.service.AppointmentService;
import co.edu.unbosque.livingcorp.service.PropertyService;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.apache.log4j.Logger;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named(value = "propertyTableBean")
@ViewScoped
public class SearchPropertyBean implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(SearchPropertyBean.class);

    @Inject
    private PropertyService propertyService;
    private List<PropertyDTO> properties;

    @Inject
    private AppointmentService appointmentService;
    private VisitorDTO visitor;

    private List<String> advisers;

    @PostConstruct
    public void init() {
        properties = propertyService.showAvailableProperties();
        loadAdvisers();
        visitor = new VisitorDTO();
    }

    private void loadAdvisers(){
        advisers = new ArrayList<String>();
        advisers.add("Carlos");
        advisers.add("Juan");
        advisers.add("Estefania");
    }

    public void createAppointment(){
        try {
            appointmentService.createAppointment(visitor);
        } catch (RepeatedObjectException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "La Cita ya existe"));
        }
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Cita agendada"));
    }

    public VisitorDTO getVisitor() {
        return visitor;
    }

    public void setVisitor(VisitorDTO visitor) {
        this.visitor = visitor;
    }

    public List<String> getAdvisers() {
        return advisers;
    }

    public void setAdvisers(List<String> advisers) {
        this.advisers = advisers;
    }

    public List<PropertyDTO> getProperties() {
        return properties;
    }

    public void setProperties(List<PropertyDTO> properties) {
        this.properties = properties;
    }
}