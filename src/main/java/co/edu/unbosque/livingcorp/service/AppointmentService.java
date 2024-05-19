package co.edu.unbosque.livingcorp.service;

import co.edu.unbosque.livingcorp.model.dto.PropertyDTO;
import co.edu.unbosque.livingcorp.model.dto.VisitorDTO;
import co.edu.unbosque.livingcorp.model.entity.Visitor;
import co.edu.unbosque.livingcorp.model.exception.RepeatedObjectException;
import co.edu.unbosque.livingcorp.model.persistence.PersistenceDAO;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class AppointmentService {

    @Inject
    private PersistenceDAO<Visitor, Integer> visitorDAO;

    private ModelMapper mp;

    public AppointmentService() {
        mp = new ModelMapper();
    }

    public VisitorDTO createAppointment(VisitorDTO visitor) throws RepeatedObjectException {
        boolean appointmentExists = visitorDAO.findAll().stream()
                .anyMatch(existingVisit -> existingVisit.getPropertyId().getPropertyId()== visitor.getPropertyId().getPropertyId() &&
                        existingVisit.getAppointmentDateTime().isEqual(visitor.getAppointmentDateTime()) &&
                        existingVisit.getAdvisorName().equals(visitor.getAdvisorName()));

        if (appointmentExists) {
            throw new RepeatedObjectException("Ya hay una cita agendada para esta propiedad en este horario.");
        }

        return mp.map(visitorDAO.save(mp.map(visitor, Visitor.class)), VisitorDTO.class);
    }

}
