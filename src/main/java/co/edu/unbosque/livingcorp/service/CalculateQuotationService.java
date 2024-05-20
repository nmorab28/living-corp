package co.edu.unbosque.livingcorp.service;

import co.edu.unbosque.livingcorp.model.dto.PropertyDTO;
import co.edu.unbosque.livingcorp.model.dto.ServiceQuotationDTO;
import co.edu.unbosque.livingcorp.model.dto.UserDTO;
import co.edu.unbosque.livingcorp.model.entity.ServiceQuotation;
import co.edu.unbosque.livingcorp.model.exception.RepeatedObjectException;
import co.edu.unbosque.livingcorp.model.persistence.PersistenceDAO;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class CalculateQuotationService {

    @Inject
    private PersistenceDAO<ServiceQuotation, Integer> serviceQuotationDAO;

    private ModelMapper mp;

    public CalculateQuotationService(){
        mp = new ModelMapper();
    }

    public ServiceQuotationDTO createServiceQuotation(ServiceQuotationDTO serviceQuotation, UserDTO user, PropertyDTO property) throws RepeatedObjectException {
        serviceQuotation.setUserName(user);
        serviceQuotation.setPropertyId(property);
        serviceQuotation.setRfqDateTime(LocalDateTime.now());
        return mp.map(serviceQuotationDAO.save(mp.map(serviceQuotation, ServiceQuotation.class)), ServiceQuotationDTO.class);
    }

    public List<ServiceQuotationDTO> showQuotations(){
        return serviceQuotationDAO.findAll().stream().map(serviceQuotation -> mp.map(serviceQuotation, ServiceQuotationDTO.class)).collect(Collectors.toList());
    }
}
