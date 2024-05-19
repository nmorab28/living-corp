package co.edu.unbosque.livingcorp.model.persistence;

import co.edu.unbosque.livingcorp.model.entity.PropertyResource;
import co.edu.unbosque.livingcorp.model.exception.ObjectNotFoundException;
import co.edu.unbosque.livingcorp.model.exception.RepeatedObjectException;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TransactionRequiredException;

import java.util.List;

@Stateless
public class PropertyResourceDAOImpl implements PersistenceDAO<PropertyResource, Integer> {

    @PersistenceContext(unitName = "livingCorpPU")
    private EntityManager em;

    @Override
    public PropertyResource save(PropertyResource entity) throws RepeatedObjectException {
        try {
            em.persist(entity);
            return entity;
        } catch (EntityExistsException e) {
            throw new RepeatedObjectException("El recurso de propiedad ya está registrado");
        }
    }

    @Override
    public PropertyResource find(Integer idEntity) throws ObjectNotFoundException {
        try {
            return em.find(PropertyResource.class, idEntity);
        } catch (EntityNotFoundException e) {
            throw new ObjectNotFoundException("Recurso de propiedad no encontrado");
        }
    }

    @Override
    public List<PropertyResource> findAll() {
        return em.createQuery("SELECT resProp FROM PropertyResource resProp", PropertyResource.class).getResultList();
    }

    @Override
    public boolean update(PropertyResource entity) throws ObjectNotFoundException {
        try {
            em.merge(entity);
            return true;
        } catch (IllegalArgumentException | TransactionRequiredException e) {
            throw new ObjectNotFoundException("Recurso de propiedad no actualizado");
        }
    }

    @Override
    public boolean delete(PropertyResource entity) throws ObjectNotFoundException {
        try {
            em.remove(em.contains(entity) ? entity : em.merge(entity));
            return true;
        } catch (IllegalArgumentException | TransactionRequiredException e) {
            throw new ObjectNotFoundException("Recurso de propiedad no eliminado");
        }
    }
}