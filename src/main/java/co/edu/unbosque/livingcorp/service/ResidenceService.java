package co.edu.unbosque.livingcorp.service;

import co.edu.unbosque.livingcorp.model.dto.ResidentDTO;
import co.edu.unbosque.livingcorp.model.dto.UserDTO;
import co.edu.unbosque.livingcorp.model.entity.Resident;
import co.edu.unbosque.livingcorp.model.exception.ObjectNotFoundException;
import co.edu.unbosque.livingcorp.model.exception.RepeatedObjectException;
import co.edu.unbosque.livingcorp.model.persistence.PersistenceDAO;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class ResidenceService {

    @Inject
    private PersistenceDAO<Resident, Integer> residentDAO;

    private ModelMapper mp;

    public ResidenceService(){
        mp = new ModelMapper();
    }

    public ResidentDTO createResidence(ResidentDTO resident, UserDTO user) throws RepeatedObjectException {
        boolean residenceExists = residentDAO.findAll().stream().anyMatch(existingResident -> existingResident.getPropertyId().getPropertyId() == resident.getPropertyId().getPropertyId() &&
                existingResident.getUserName().getUserName().equals(user.getUserName()));
        if(residenceExists)
            throw new RepeatedObjectException("Este usuario ya es residente de esta propiedad.");
        resident.setUserName(user);
        return mp.map(residentDAO.save(mp.map(resident, Resident.class)), ResidentDTO.class);
    }

    public ResidentDTO searchResident(int id) throws ObjectNotFoundException {
        return mp.map(residentDAO.find(id), ResidentDTO.class);
    }

    public List<ResidentDTO> showResidents(){
        return residentDAO.findAll().stream().map(resident -> mp.map(resident, ResidentDTO.class)).collect(Collectors.toList());
    }
}
