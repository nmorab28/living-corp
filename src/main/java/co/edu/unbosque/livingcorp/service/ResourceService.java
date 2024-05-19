package co.edu.unbosque.livingcorp.service;

import co.edu.unbosque.livingcorp.model.dto.ResourceDTO;
import co.edu.unbosque.livingcorp.model.entity.Resource;
import co.edu.unbosque.livingcorp.model.exception.ObjectNotFoundException;
import co.edu.unbosque.livingcorp.model.exception.RepeatedObjectException;
import co.edu.unbosque.livingcorp.model.persistence.PersistenceDAO;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class ResourceService {

    @Inject
    private PersistenceDAO<Resource, Integer> resourceDAO;

    private ModelMapper mp;

    public ResourceService(){
        mp = new ModelMapper();
    }

    public ResourceDTO createResource(ResourceDTO resource) throws RepeatedObjectException {
        return mp.map(resourceDAO.save(mp.map(resource, Resource.class)), ResourceDTO.class);
    }

    public ResourceDTO searchResource(int id) throws ObjectNotFoundException {
        return mp.map(resourceDAO.find(id), ResourceDTO.class);
    }

    public List<ResourceDTO> showResources(){
        return resourceDAO.findAll().stream().map(resource -> mp.map(resource, ResourceDTO.class)).collect(Collectors.toList());
    }
}
