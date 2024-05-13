package co.edu.unbosque.livingcorp.service;

import co.edu.unbosque.livingcorp.model.dto.PropertyDTO;
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
public class ResidentService {

    @Inject
    private PersistenceDAO<Resident, Integer> residentDAO;

    private ModelMapper mp;

    public ResidentService(){
        mp = new ModelMapper();
    }

    public ResidentDTO createResident(PropertyDTO property, UserDTO user) throws RepeatedObjectException {
        ResidentDTO resident = new ResidentDTO(property, user, user.isResidentPropertyOwner());
        return mp.map(residentDAO.save(mp.map(resident, Resident.class)), ResidentDTO.class);
    }

    public ResidentDTO searchResident(int id) throws ObjectNotFoundException {
        return mp.map(residentDAO.find(id), ResidentDTO.class);
    }

    public List<ResidentDTO> showResidents(){
        return residentDAO.findAll().stream().map(resident -> mp.map(resident, ResidentDTO.class)).collect(Collectors.toList());
    }

}
