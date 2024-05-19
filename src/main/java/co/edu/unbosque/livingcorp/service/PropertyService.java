package co.edu.unbosque.livingcorp.service;

import co.edu.unbosque.livingcorp.model.dto.PropertyDTO;
import co.edu.unbosque.livingcorp.model.dto.ResidentDTO;
import co.edu.unbosque.livingcorp.model.dto.UserDTO;
import co.edu.unbosque.livingcorp.model.entity.Property;
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
public class PropertyService {

    @Inject
    private PersistenceDAO<Property, Integer> propertyDAO;

    private ModelMapper mp;

    public PropertyService(){
        mp = new ModelMapper();
    }

    public PropertyDTO createProperty(PropertyDTO property, UserDTO admin) throws RepeatedObjectException {
        property.setPropertyAdmin(admin);
        return mp.map(propertyDAO.save(mp.map(property, Property.class)), PropertyDTO.class);
    }

    public void addResidentToProperty(ResidentDTO resident) throws ObjectNotFoundException {
        PropertyDTO property = resident.getPropertyId();
        property.setAvailableForRent(false);
        property.setAvailableForSale(false);
        propertyDAO.update(mp.map(property, Property.class));
    }

    public PropertyDTO searchProperty(int id) throws ObjectNotFoundException {
        return mp.map(propertyDAO.find(id), PropertyDTO.class);
    }

    public List<PropertyDTO> showProperties(){
        return propertyDAO.findAll().stream().map(property -> mp.map(property, PropertyDTO.class)).collect(Collectors.toList());
    }

    public List<PropertyDTO> showAvailableProperties(){
        return propertyDAO.findAll().stream().filter(property -> property.isAvailableForRent() || property.isAvailableForSale()).map(property -> mp.map(property, PropertyDTO.class)).collect(Collectors.toList());
    }

}
