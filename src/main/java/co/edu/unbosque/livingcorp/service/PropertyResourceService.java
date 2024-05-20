package co.edu.unbosque.livingcorp.service;

import co.edu.unbosque.livingcorp.model.dto.PropertyResourceDTO;
import co.edu.unbosque.livingcorp.model.dto.ResourceDTO;
import co.edu.unbosque.livingcorp.model.entity.PropertyResource;
import co.edu.unbosque.livingcorp.model.exception.ObjectNotFoundException;
import co.edu.unbosque.livingcorp.model.exception.PRInvalidRequirementsException;
import co.edu.unbosque.livingcorp.model.exception.RepeatedObjectException;
import co.edu.unbosque.livingcorp.model.persistence.PersistenceDAO;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class PropertyResourceService {

    @Inject
    private PersistenceDAO<PropertyResource, Integer> propResourceDAO;

    private ModelMapper mp;

    public PropertyResourceService(){
        mp = new ModelMapper();
    }

    public PropertyResourceDTO createPropertyResource(PropertyResourceDTO propertyResource, ResourceDTO resource) throws RepeatedObjectException, PRInvalidRequirementsException {

        if(resource.getResourceType().equals("Terraza BBQ") || resource.getResourceType().equals("Salon de Eventos")){
            if(propertyResource.getMinPrice() < 150000 && propertyResource.getMinTimeH() < 3)
                throw new PRInvalidRequirementsException("Este recurso no cumple con los requisitos minimos.");

        }
        boolean propResourceExists = showPropertyResources().stream().anyMatch(existingPropResource -> existingPropResource.getResId().getResourceId() == resource.getResourceId() &&
                existingPropResource.getProId().getPropertyId() == propertyResource.getProId().getPropertyId());
        if(propResourceExists)
            throw new RepeatedObjectException("Este Recurso ya fue asignado para esta propiedad.");
        propertyResource.setResId(resource);
        propertyResource.setAvailabily("true");
        return mp.map(propResourceDAO.save(mp.map(propertyResource, PropertyResource.class)), PropertyResourceDTO.class);
    }

    public PropertyResourceDTO searchPropertyResource(int id) throws ObjectNotFoundException {
        return mp.map(propResourceDAO.find(id), PropertyResourceDTO.class);
    }

    public List<PropertyResourceDTO> showPropertyResources(){
        return propResourceDAO.findAll().stream().map(propertyResource -> mp.map(propertyResource, PropertyResourceDTO.class)).collect(Collectors.toList());
    }

    public void updatePropertyResource(PropertyResourceDTO propertyResource) throws ObjectNotFoundException {
        propResourceDAO.update(mp.map(propertyResource, PropertyResource.class));
    }

    public int getSelecctedResourceId(String characters) throws ObjectNotFoundException {
        String chars[] = characters.split(" - ");
        if(!chars[0].isEmpty()){
            return Integer.parseInt(chars[0]);
        }
        throw new ObjectNotFoundException("El id no fue encontrado");
    }

}
