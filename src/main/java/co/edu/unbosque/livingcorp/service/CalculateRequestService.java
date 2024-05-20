package co.edu.unbosque.livingcorp.service;

import co.edu.unbosque.livingcorp.model.dto.PropertyDTO;
import co.edu.unbosque.livingcorp.model.dto.ServiceRequestDTO;
import co.edu.unbosque.livingcorp.model.dto.UserDTO;
import co.edu.unbosque.livingcorp.model.entity.ServiceRequest;
import co.edu.unbosque.livingcorp.model.exception.RepeatedObjectException;
import co.edu.unbosque.livingcorp.model.persistence.PersistenceDAO;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class CalculateRequestService {

    @Inject
    private PersistenceDAO<ServiceRequest, Integer> serviceRequestDAO;

    private ModelMapper mp;

    public CalculateRequestService(){
        mp = new ModelMapper();
    }

    public ServiceRequestDTO createServiceRequest(ServiceRequestDTO serviceRequest, UserDTO user, PropertyDTO property) throws RepeatedObjectException {
        serviceRequest.setRqstDateTime(LocalDateTime.now());
        serviceRequest.setSvcDateTime(serviceRequest.getRqstDateTime().plusDays(showRequests().size() + 1));
        boolean requestExists = showRequests().stream().anyMatch(existingRequest -> existingRequest.getUserName().getUserName().equals(user.getUserName()) &&
                existingRequest.getPropertyId().getPropertyId() == property.getPropertyId() &&
                existingRequest.getSvcProviderId().getProviderId() == serviceRequest.getSvcProviderId().getProviderId() &&
                existingRequest.getSvcDateTime().isEqual(serviceRequest.getSvcDateTime()));
        if (requestExists){
            throw new RepeatedObjectException("Ya contrataste este servicio en esta propiedad.");
        }
        serviceRequest.setUserName(user);
        serviceRequest.setPropertyId(property);
        return mp.map(serviceRequestDAO.save(mp.map(serviceRequest, ServiceRequest.class)), ServiceRequestDTO.class);
    }

    public List<ServiceRequestDTO> showRequests(){
        return serviceRequestDAO.findAll().stream().map(serviceRequest -> mp.map(serviceRequest, ServiceRequestDTO.class)).collect(Collectors.toList());
    }
}
